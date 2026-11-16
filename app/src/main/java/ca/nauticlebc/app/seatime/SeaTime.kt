package ca.nauticlebc.app.seatime

import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import java.time.LocalDate
import java.time.temporal.ChronoUnit

/**
 * One spell on one vessel, from signing on to signing off: the learner's own copy of a discharge book entry,
 * holding what a deck sea service testimonial asks for (Marine Personnel Regulations, Schedule 4).
 * Dates are ISO-8601 (`2026-11-16`). A blank [signOff] means still on board.
 * Text fields stay text so the learner can copy exactly what the vessel's papers say.
 */
@Serializable
data class SeaPeriod(
    val id: String,
    val vessel: String = "",
    val officialNumber: String = "",
    val portOfRegistry: String = "",
    val vesselType: String = "",
    val grossTonnage: String = "",
    val voyageClass: String = "",
    val rank: String = "",
    val signOn: String = "",
    val signOff: String = "",
    val daysWorked: Int = 0,
    val hoursPerDay: Double = 8.0,
    val daysUnderway: Int = 0,
    val drills: Int = 0,
    val wheelHours: Double = 0.0,
    val master: String = "",
    val notes: String = "",
    val hasDischarge: Boolean = false,
    val hasTestimonial: Boolean = false,
) {
    val creditedDays: Double get() = SeaTime.creditedDays(daysWorked, hoursPerDay)
}

/** What a log book line is about. Stored by name, so do not rename. */
enum class LogKind { DEPARTURE, ARRIVAL, TRAFFIC, POSITION, WEATHER, DRILL, STEERING, INCIDENT, NOTE }

/** One line of the learner's own log book. [time] is four figures on the 24-hour clock (`1440`). */
@Serializable
data class LogEntry(
    val id: String,
    val date: String = "",
    val time: String = "",
    val kind: String = LogKind.NOTE.name,
    val vessel: String = "",
    val place: String = "",
    val channel: String = "",
    val text: String = "",
) {
    val logKind: LogKind get() = LogKind.entries.firstOrNull { it.name == kind } ?: LogKind.NOTE
}

/** Things wrong with a sea period that would make the sums or the dates untrustworthy. */
enum class PeriodProblem { NO_VESSEL, BAD_SIGN_ON, BAD_SIGN_OFF, OFF_BEFORE_ON, MORE_DAYS_THAN_CALENDAR, UNDERWAY_OVER_WORKED, BAD_HOURS }

/** Testimonial details still blank on a sea period. */
enum class MissingDetail { OFFICIAL_NUMBER, PORT_OF_REGISTRY, VESSEL_TYPE, GROSS_TONNAGE, VOYAGE_CLASS, RANK, SIGN_OFF, MASTER }

data class SeaTimeSummary(
    val periods: Int,
    val daysWorked: Int,
    val creditedDays: Double,
    val wheelHours: Double,
    val drills: Int,
    val needTestimonial: Int,
    val needDischarge: Int,
) {
    val months: Double get() = SeaTime.months(creditedDays)
}

/**
 * Sea service arithmetic as the Marine Personnel Regulations s. 115 give it:
 * 8 hours is one day, a 12-hour watch is one and a half days, nothing counts past 12 hours in a calendar day,
 * and months are credited days divided by 30. Watches of other lengths are credited on a straight line
 * (hours / 8), which is our reading of "prorated"; the examiner has the last word.
 */
object SeaTime {
    const val HOURS_PER_CREDITED_DAY = 8.0
    const val MAX_HOURS_PER_DAY = 12.0
    const val DAYS_PER_MONTH = 30.0

    fun creditedDays(daysWorked: Int, hoursPerDay: Double): Double {
        if (daysWorked <= 0 || hoursPerDay.isNaN()) return 0.0
        val hours = hoursPerDay.coerceIn(0.0, MAX_HOURS_PER_DAY)
        return daysWorked * hours / HOURS_PER_CREDITED_DAY
    }

    fun months(creditedDays: Double): Double = creditedDays / DAYS_PER_MONTH

    /** Credited days still needed for [targetMonths]; never negative. */
    fun remainingDays(targetMonths: Double, creditedDays: Double): Double =
        (targetMonths * DAYS_PER_MONTH - creditedDays).coerceAtLeast(0.0)

    fun parseDate(text: String): LocalDate? = runCatching { LocalDate.parse(text.trim()) }.getOrNull()

    /** Calendar days from sign-on to sign-off, counting both; null when either date is missing or they are reversed. */
    fun calendarDays(signOn: String, signOff: String): Int? {
        val on = parseDate(signOn) ?: return null
        val off = parseDate(signOff) ?: return null
        if (off.isBefore(on)) return null
        return ChronoUnit.DAYS.between(on, off).toInt() + 1
    }

    /** True for four figures from 0000 to 2359. */
    fun isValidTime(text: String): Boolean {
        if (text.length != 4 || !text.all { it in '0'..'9' }) return false
        return text.substring(0, 2).toInt() <= 23 && text.substring(2).toInt() <= 59
    }

    fun problems(p: SeaPeriod): List<PeriodProblem> {
        val found = mutableListOf<PeriodProblem>()
        if (p.vessel.isBlank()) found += PeriodProblem.NO_VESSEL
        val on = parseDate(p.signOn)
        if (on == null) found += PeriodProblem.BAD_SIGN_ON
        val off = if (p.signOff.isBlank()) null else parseDate(p.signOff)
        if (p.signOff.isNotBlank() && off == null) found += PeriodProblem.BAD_SIGN_OFF
        if (on != null && off != null) {
            if (off.isBefore(on)) {
                found += PeriodProblem.OFF_BEFORE_ON
            } else if (p.daysWorked > ChronoUnit.DAYS.between(on, off).toInt() + 1) {
                found += PeriodProblem.MORE_DAYS_THAN_CALENDAR
            }
        }
        if (p.daysUnderway > p.daysWorked) found += PeriodProblem.UNDERWAY_OVER_WORKED
        if (p.hoursPerDay.isNaN() || p.hoursPerDay <= 0.0 || p.hoursPerDay > 24.0) found += PeriodProblem.BAD_HOURS
        return found
    }

    fun missing(p: SeaPeriod): List<MissingDetail> = buildList {
        if (p.officialNumber.isBlank()) add(MissingDetail.OFFICIAL_NUMBER)
        if (p.portOfRegistry.isBlank()) add(MissingDetail.PORT_OF_REGISTRY)
        if (p.vesselType.isBlank()) add(MissingDetail.VESSEL_TYPE)
        if (p.grossTonnage.isBlank()) add(MissingDetail.GROSS_TONNAGE)
        if (p.voyageClass.isBlank()) add(MissingDetail.VOYAGE_CLASS)
        if (p.rank.isBlank()) add(MissingDetail.RANK)
        if (p.signOff.isBlank()) add(MissingDetail.SIGN_OFF)
        if (p.master.isBlank()) add(MissingDetail.MASTER)
    }

    fun summarize(periods: List<SeaPeriod>): SeaTimeSummary = SeaTimeSummary(
        periods = periods.size,
        daysWorked = periods.sumOf { it.daysWorked.coerceAtLeast(0) },
        creditedDays = periods.sumOf { it.creditedDays },
        wheelHours = periods.sumOf { it.wheelHours.coerceAtLeast(0.0) },
        drills = periods.sumOf { it.drills.coerceAtLeast(0) },
        needTestimonial = periods.count { !it.hasTestimonial },
        needDischarge = periods.count { !it.hasDischarge },
    )

    /** `12.5` or `12` for whole numbers: at most one decimal place, always with a dot. */
    fun format(value: Double): String {
        val tenths = Math.round(value * 10.0)
        return if (tenths % 10L == 0L) (tenths / 10L).toString() else "${tenths / 10L}.${Math.abs(tenths % 10L)}"
    }

    /** Reads a number typed with a dot or a comma. */
    fun parseNumber(text: String): Double? = text.trim().replace(',', '.').toDoubleOrNull()
}

/** Where the sea time record is kept. Values are JSON text. */
interface SeaTimeStore {
    fun read(key: String): String?
    fun write(key: String, value: String)
}

object SeaTimeCodec {
    private val json = Json { ignoreUnknownKeys = true; encodeDefaults = true }

    fun encodePeriods(items: List<SeaPeriod>): String = json.encodeToString(ListSerializer(SeaPeriod.serializer()), items)
    fun decodePeriods(text: String?): List<SeaPeriod> =
        if (text.isNullOrBlank()) emptyList()
        else runCatching { json.decodeFromString(ListSerializer(SeaPeriod.serializer()), text) }.getOrDefault(emptyList())

    fun encodeLog(items: List<LogEntry>): String = json.encodeToString(ListSerializer(LogEntry.serializer()), items)
    fun decodeLog(text: String?): List<LogEntry> =
        if (text.isNullOrBlank()) emptyList()
        else runCatching { json.decodeFromString(ListSerializer(LogEntry.serializer()), text) }.getOrDefault(emptyList())

    fun encodePeriod(p: SeaPeriod): String = json.encodeToString(SeaPeriod.serializer(), p)
    fun decodePeriod(text: String): SeaPeriod? = runCatching { json.decodeFromString(SeaPeriod.serializer(), text) }.getOrNull()
    fun encodeEntry(e: LogEntry): String = json.encodeToString(LogEntry.serializer(), e)
    fun decodeEntry(text: String): LogEntry? = runCatching { json.decodeFromString(LogEntry.serializer(), text) }.getOrNull()
}

/** The learner's sea periods and log lines, newest first, saved after every change. */
class SeaTimeBook(private val store: SeaTimeStore) {
    var periods: List<SeaPeriod> = SeaTimeCodec.decodePeriods(store.read(KEY_PERIODS)).sortedPeriods()
        private set
    var log: List<LogEntry> = SeaTimeCodec.decodeLog(store.read(KEY_LOG)).sortedLog()
        private set
    var targetMonths: Double = store.read(KEY_TARGET)?.toDoubleOrNull() ?: 0.0
        private set

    fun save(period: SeaPeriod): List<SeaPeriod> {
        periods = (periods.filter { it.id != period.id } + period).sortedPeriods()
        store.write(KEY_PERIODS, SeaTimeCodec.encodePeriods(periods))
        return periods
    }

    fun deletePeriod(id: String): List<SeaPeriod> {
        periods = periods.filter { it.id != id }
        store.write(KEY_PERIODS, SeaTimeCodec.encodePeriods(periods))
        return periods
    }

    fun save(entry: LogEntry): List<LogEntry> {
        log = (log.filter { it.id != entry.id } + entry).sortedLog()
        store.write(KEY_LOG, SeaTimeCodec.encodeLog(log))
        return log
    }

    fun deleteEntry(id: String): List<LogEntry> {
        log = log.filter { it.id != id }
        store.write(KEY_LOG, SeaTimeCodec.encodeLog(log))
        return log
    }

    fun setTarget(months: Double) {
        targetMonths = if (months.isNaN()) 0.0 else months.coerceIn(0.0, 600.0)
        store.write(KEY_TARGET, targetMonths.toString())
    }

    private fun List<SeaPeriod>.sortedPeriods() = sortedWith(compareByDescending<SeaPeriod> { it.signOn }.thenBy { it.id })
    private fun List<LogEntry>.sortedLog() =
        sortedWith(compareByDescending<LogEntry> { it.date }.thenByDescending { it.time }.thenBy { it.id })

    private companion object {
        const val KEY_PERIODS = "periods"
        const val KEY_LOG = "log"
        const val KEY_TARGET = "target_months"
    }
}
