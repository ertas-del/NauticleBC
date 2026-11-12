package ca.nauticlebc.app.almanac

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test
import kotlin.random.Random

class AlmanacTest {
    // 10h GHA 32° 15.4′, Dec N 18° 24.6′, d 0.4′ northwards.
    private val page = AlmanacPage(hour = 10, ghaTenths = 32 * 600 + 154, decTenths = 18 * 600 + 246, dTenths = 4)

    @Test
    fun twentyFourMinutesAddsSixDegreesAndAFifthOfD() {
        val fix = Almanac.fix(page, minutes = 24, askGha = true)
        assertEquals(38 * 600 + 154, fix.ghaTenths)
        assertEquals(18 * 600 + 248, fix.decTenths)
        assertEquals("38° 15.4′", arc(fix.ghaTenths))
        assertEquals("N 18° 24.8′", "N " + arc(fix.decTenths))
    }

    @Test
    fun theNextHourIsFifteenDegreesOnAndTheFullD() {
        val next = Almanac.nextHour(page)
        assertEquals(11, next.hour)
        assertEquals(47 * 600 + 154, next.ghaTenths)
        assertEquals(18 * 600 + 250, next.decTenths)
    }

    @Test
    fun ghaWrapsPast360() {
        val late = page.copy(ghaTenths = 358 * 600)
        val fix = Almanac.fix(late, minutes = 12, askGha = true)
        assertEquals(1 * 600, fix.ghaTenths)
    }

    @Test
    fun aSouthwardsDIsSubtracted() {
        val south = page.copy(dTenths = -6)
        val fix = Almanac.fix(south, minutes = 30, askGha = false)
        assertEquals(page.decTenths - 3, fix.decTenths)
    }

    @Test
    fun parsesThePageStyle() {
        assertEquals(38 * 600 + 154, Almanac.parseGha("38 15.4"))
        assertEquals(38 * 600 + 154, Almanac.parseGha("38° 15.4′"))
        assertEquals(18 * 600 + 248, Almanac.parseDec("N 18 24.8"))
        assertEquals(-(2 * 600 + 31), Almanac.parseDec("S 2 03.1"))
        assertNull(Almanac.parseDec("18 24.8"))
        assertNull(Almanac.parseGha("nope"))
    }

    @Test
    fun theSameSeedAsksTheSameQuestion() {
        val a = Almanac.question(Random(7))
        val b = Almanac.question(Random(7))
        assertEquals(a, b)
    }

    private fun arc(tenths: Int): String {
        val parts = Almanac.arcParts(tenths)
        return "${parts.degrees}° ${parts.minutes}′"
    }
}
