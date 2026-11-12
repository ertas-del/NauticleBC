package ca.nauticlebc.app.almanac

import java.util.Locale
import kotlin.math.abs
import kotlin.random.Random

/**
 * An invented Sun page in the shape of the Nautical Almanac. Figures are made up. On this page the
 * Sun's GHA gains exactly 15° each hour, so a minute of time is 15.0′ of GHA. Declination changes
 * by [AlmanacPage.dTenths] over the hour. Nothing here is for navigation.
 *
 * Angles are stored in tenths of an arcminute. North and eastwards GHA are positive.
 */
data class AlmanacPage(
    val hour: Int,
    val ghaTenths: Int,
    val decTenths: Int,
    val dTenths: Int,
)

data class SunFix(
    val page: AlmanacPage,
    val minutes: Int,
    val askGha: Boolean,
    val ghaTenths: Int,
    val decTenths: Int,
    val incrementTenths: Int,
    val correctionTenths: Int,
) {
    val answerTenths: Int get() = if (askGha) ghaTenths else decTenths
}

data class ArcParts(val degrees: Int, val minutes: String)

object Almanac {
    private const val TENTHS_PER_DEGREE = 600
    private const val CIRCLE = 360 * TENTHS_PER_DEGREE
    /** 15.0′ of arc for each minute of time. */
    private const val GHA_TENTHS_PER_MINUTE = 150

    fun page(random: Random): AlmanacPage {
        val hour = random.nextInt(0, 23)
        val gha = random.nextInt(0, CIRCLE)
        var dec = random.nextInt(-20 * TENTHS_PER_DEGREE, 20 * TENTHS_PER_DEGREE)
        var d = random.nextInt(-12, 13)
        if (d == 0) d = 4
        if (dec + d > 23 * TENTHS_PER_DEGREE || dec + d < -23 * TENTHS_PER_DEGREE) d = -d
        return AlmanacPage(hour, gha, dec, d)
    }

    fun question(random: Random): SunFix {
        val page = page(random)
        val minutes = random.nextInt(0, 60)
        return fix(page, minutes, random.nextBoolean())
    }

    fun fix(page: AlmanacPage, minutes: Int, askGha: Boolean): SunFix {
        val increment = minutes * GHA_TENTHS_PER_MINUTE
        val correction = correctionTenths(page.dTenths, minutes)
        return SunFix(
            page = page,
            minutes = minutes,
            askGha = askGha,
            ghaTenths = wrap(page.ghaTenths + increment),
            decTenths = page.decTenths + correction,
            incrementTenths = increment,
            correctionTenths = correction,
        )
    }

    fun nextHour(page: AlmanacPage): AlmanacPage = page.copy(
        hour = page.hour + 1,
        ghaTenths = wrap(page.ghaTenths + 15 * TENTHS_PER_DEGREE),
        decTenths = page.decTenths + page.dTenths,
        dTenths = 0,
    )

    /** "32 15.4" or "32° 15.4′". Returns GHA tenths, or null when the text is not an angle. */
    fun parseGha(text: String): Int? {
        val (deg, min) = splitArc(text) ?: return null
        if (deg !in 0..360 || min < 0 || min >= 60) return null
        return wrap(deg * TENTHS_PER_DEGREE + Math.round(min * 10.0).toInt())
    }

    /** "N 18 24.6" or "S 2 03.1". North is positive. */
    fun parseDec(text: String): Int? {
        val trimmed = text.trim()
        if (trimmed.isEmpty()) return null
        val north = when (trimmed[0].uppercaseChar()) {
            'N' -> true
            'S' -> false
            else -> return null
        }
        val (deg, min) = splitArc(trimmed.drop(1)) ?: return null
        if (deg !in 0..90 || min < 0 || min >= 60) return null
        val tenths = deg * TENTHS_PER_DEGREE + Math.round(min * 10.0).toInt()
        return if (north) tenths else -tenths
    }

    fun arcParts(tenths: Int): ArcParts {
        val safe = abs(tenths)
        val degrees = safe / TENTHS_PER_DEGREE
        val minutes = (safe % TENTHS_PER_DEGREE) / 10.0
        return ArcParts(degrees, "%04.1f".format(Locale.US, minutes))
    }

    /** d in arcminutes, one decimal, always positive. The sign is [AlmanacPage.dTenths]. */
    fun dText(dTenths: Int): String = "%0.1f".format(Locale.US, abs(dTenths) / 10.0)

    private fun correctionTenths(dTenths: Int, minutes: Int): Int {
        val product = dTenths * minutes
        val sign = if (product < 0) -1 else 1
        return sign * ((abs(product) + 30) / 60)
    }

    private fun wrap(tenths: Int): Int {
        var t = tenths % CIRCLE
        if (t < 0) t += CIRCLE
        return t
    }

    private fun splitArc(text: String): Pair<Int, Double>? {
        val cleaned = text.replace("°", " ").replace("'", " ").replace("′", " ").replace("\"", " ")
        val parts = cleaned.trim().split(Regex("\\s+")).filter { it.isNotEmpty() }
        if (parts.size != 2) return null
        val deg = parts[0].toIntOrNull() ?: return null
        val min = parts[1].toDoubleOrNull() ?: return null
        return deg to min
    }
}
