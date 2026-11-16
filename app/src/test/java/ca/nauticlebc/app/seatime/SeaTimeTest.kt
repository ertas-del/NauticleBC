package ca.nauticlebc.app.seatime

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

private class MemoryStore : SeaTimeStore {
    val values = mutableMapOf<String, String>()
    override fun read(key: String): String? = values[key]
    override fun write(key: String, value: String) { values[key] = value }
}

class SeaTimeTest {
    @Test
    fun eightHoursIsOneDayAndTwelveIsOneAndAHalf() {
        assertEquals(60.0, SeaTime.creditedDays(60, 8.0), 1e-9)
        assertEquals(60.0, SeaTime.creditedDays(40, 12.0), 1e-9)
        assertEquals(1.25, SeaTime.creditedDays(1, 10.0), 1e-9)
        assertEquals(0.5, SeaTime.creditedDays(1, 4.0), 1e-9)
    }

    @Test
    fun hoursPastTwelveEarnNothingExtra() {
        assertEquals(1.5, SeaTime.creditedDays(1, 14.0), 1e-9)
        assertEquals(1.5, SeaTime.creditedDays(1, 24.0), 1e-9)
    }

    @Test
    fun nonsenseInputCreditsNothing() {
        assertEquals(0.0, SeaTime.creditedDays(-3, 8.0), 1e-9)
        assertEquals(0.0, SeaTime.creditedDays(5, -2.0), 1e-9)
        assertEquals(0.0, SeaTime.creditedDays(5, Double.NaN), 1e-9)
    }

    @Test
    fun monthsAreCreditedDaysOverThirty() {
        assertEquals(2.0, SeaTime.months(60.0), 1e-9)
        assertEquals(1.3, SeaTime.months(39.0), 1e-9)
        assertEquals(21.0, SeaTime.remainingDays(2.0, 39.0), 1e-9)
        assertEquals(0.0, SeaTime.remainingDays(2.0, 70.0), 1e-9)
    }

    @Test
    fun calendarDaysCountBothEnds() {
        assertEquals(1, SeaTime.calendarDays("2026-05-02", "2026-05-02"))
        assertEquals(152, SeaTime.calendarDays("2026-05-02", "2026-09-30"))
        assertNull(SeaTime.calendarDays("2026-05-02", "2026-05-01"))
        assertNull(SeaTime.calendarDays("2 May", "2026-05-03"))
    }

    @Test
    fun timeIsFourFiguresOnTheTwentyFourHourClock() {
        assertTrue(SeaTime.isValidTime("0000"))
        assertTrue(SeaTime.isValidTime("2359"))
        assertFalse(SeaTime.isValidTime("2400"))
        assertFalse(SeaTime.isValidTime("1260"))
        assertFalse(SeaTime.isValidTime("940"))
        assertFalse(SeaTime.isValidTime("14:40"))
    }

    @Test
    fun problemsAreFound() {
        val good = SeaPeriod(id = "a", vessel = "Kelp Runner", signOn = "2026-05-02", signOff = "2026-05-11", daysWorked = 10, hoursPerDay = 12.0, daysUnderway = 8)
        assertTrue(SeaTime.problems(good).isEmpty())
        assertEquals(listOf(PeriodProblem.MORE_DAYS_THAN_CALENDAR), SeaTime.problems(good.copy(daysWorked = 11)))
        assertEquals(listOf(PeriodProblem.OFF_BEFORE_ON), SeaTime.problems(good.copy(signOff = "2026-05-01")))
        assertEquals(listOf(PeriodProblem.UNDERWAY_OVER_WORKED), SeaTime.problems(good.copy(daysUnderway = 11)))
        assertEquals(listOf(PeriodProblem.BAD_HOURS), SeaTime.problems(good.copy(hoursPerDay = 0.0)))
        assertEquals(listOf(PeriodProblem.NO_VESSEL, PeriodProblem.BAD_SIGN_ON), SeaTime.problems(good.copy(vessel = " ", signOn = "")))
        // Still on board: a blank sign-off is not a problem.
        assertTrue(SeaTime.problems(good.copy(signOff = "")).isEmpty())
        assertEquals(listOf(PeriodProblem.BAD_SIGN_OFF), SeaTime.problems(good.copy(signOff = "soon")))
    }

    @Test
    fun missingTestimonialDetailsAreListed() {
        val bare = SeaPeriod(id = "a", vessel = "Kelp Runner", signOn = "2026-05-02")
        assertEquals(MissingDetail.entries.toList(), SeaTime.missing(bare))
        val full = bare.copy(
            officialNumber = "800000", portOfRegistry = "Vancouver", vesselType = "Tug", grossTonnage = "40",
            voyageClass = "Near coastal 2", rank = "Deckhand", signOff = "2026-09-30", master = "A. Master",
        )
        assertTrue(SeaTime.missing(full).isEmpty())
    }

    @Test
    fun summaryAddsUpAndCountsMissingPapers() {
        val periods = listOf(
            SeaPeriod(id = "a", daysWorked = 25, hoursPerDay = 8.0, drills = 2, wheelHours = 10.0, hasTestimonial = true, hasDischarge = true),
            SeaPeriod(id = "b", daysWorked = 30, hoursPerDay = 12.0, drills = 3, wheelHours = 5.5),
        )
        val s = SeaTime.summarize(periods)
        assertEquals(55, s.daysWorked)
        assertEquals(70.0, s.creditedDays, 1e-9)
        assertEquals(70.0 / 30.0, s.months, 1e-9)
        assertEquals(5, s.drills)
        assertEquals(15.5, s.wheelHours, 1e-9)
        assertEquals(1, s.needTestimonial)
        assertEquals(1, s.needDischarge)
    }

    @Test
    fun numbersFormatToOneDecimal() {
        assertEquals("70", SeaTime.format(70.0))
        assertEquals("2.3", SeaTime.format(70.0 / 30.0))
        assertEquals("37.5", SeaTime.format(37.5))
        assertEquals("0", SeaTime.format(0.04))
        assertEquals(1.5, SeaTime.parseNumber(" 1,5 ")!!, 1e-9)
        assertNull(SeaTime.parseNumber("twelve"))
    }

    @Test
    fun bookSavesSortsAndReloads() {
        val store = MemoryStore()
        val book = SeaTimeBook(store)
        book.save(SeaPeriod(id = "old", vessel = "A", signOn = "2025-01-01"))
        book.save(SeaPeriod(id = "new", vessel = "B", signOn = "2026-01-01"))
        book.save(SeaPeriod(id = "old", vessel = "A2", signOn = "2025-01-01"))
        assertEquals(listOf("new", "old"), book.periods.map { it.id })
        assertEquals("A2", book.periods.last().vessel)

        book.save(LogEntry(id = "1", date = "2026-11-16", time = "0900", kind = LogKind.DEPARTURE.name))
        book.save(LogEntry(id = "2", date = "2026-11-16", time = "1440", kind = LogKind.TRAFFIC.name, channel = "12"))
        book.setTarget(2.0)

        val again = SeaTimeBook(store)
        assertEquals(listOf("new", "old"), again.periods.map { it.id })
        assertEquals(listOf("2", "1"), again.log.map { it.id })
        assertEquals(LogKind.TRAFFIC, again.log.first().logKind)
        assertEquals(2.0, again.targetMonths, 1e-9)

        again.deletePeriod("new")
        again.deleteEntry("2")
        val third = SeaTimeBook(store)
        assertEquals(listOf("old"), third.periods.map { it.id })
        assertEquals(listOf("1"), third.log.map { it.id })
    }

    @Test
    fun damagedOrUnknownDataDoesNotCrash() {
        assertTrue(SeaTimeCodec.decodePeriods("not json").isEmpty())
        assertTrue(SeaTimeCodec.decodeLog(null).isEmpty())
        val entry = SeaTimeCodec.decodeLog("""[{"id":"x","kind":"SOMETHING_NEW","later_field":1}]""").single()
        assertEquals(LogKind.NOTE, entry.logKind)
        val p = SeaPeriod(id = "a", vessel = "Kelp Runner", hoursPerDay = 12.0)
        assertEquals(p, SeaTimeCodec.decodePeriod(SeaTimeCodec.encodePeriod(p)))
    }
}
