package ca.nauticlebc.app.content

import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.hypot
import kotlin.math.sin

/** What a plotted line is for. */
enum class LineKind {
    /** Laid between two taps with the parallel rule and dividers to read a bearing and distance. */
    Measured,

    /** Laid off from a start point on a bearing for a distance (a course, or a DR run). */
    Course,

    /** A bearing laid through a charted mark, drawn both ways. Two of them cut at a fix. */
    PositionLine,
}

data class PlotLine(val from: ChartPos, val to: ChartPos, val kind: LineKind)

/**
 * Everything the learner has put on the chart for one exercise.
 * [result] is the latest position the plot produced (a tapped point, the end of a DR run or a fix);
 * position answers are marked against it.
 */
data class Plot(
    val points: List<ChartPos> = emptyList(),
    val lines: List<PlotLine> = emptyList(),
    val fixes: List<ChartPos> = emptyList(),
    val pending: ChartPos? = null,
    val start: ChartPos? = null,
    val result: ChartPos? = null,
) {
    fun point(p: ChartPos): Plot = copy(points = (points + p).takeLast(MAX_POINTS), result = p, pending = null)

    /** First tap holds the point, second tap lays the line. */
    fun measureTap(p: ChartPos): Plot {
        val first = pending ?: return copy(pending = p)
        return copy(lines = lines + PlotLine(first, p, LineKind.Measured), pending = null)
    }

    fun withStart(p: ChartPos): Plot = copy(start = p, pending = null)

    /** The latest line a bearing or distance can be read from. */
    val lastMeasured: PlotLine?
        get() = lines.lastOrNull { it.kind != LineKind.PositionLine }

    companion object {
        const val MAX_POINTS = 6
    }
}

/** One line of a grade. Numbers are already rounded; the screen adds units. */
sealed class PlotCheck {
    data class Result(
        val label: String,
        val passed: Boolean,
        val measured: String,
        val expected: String,
        val tolerance: String,
        val unit: PlotUnit,
    ) : PlotCheck()

    /** A position answer: how far the learner's position is from the answer. */
    data class PositionResult(
        val label: String,
        val passed: Boolean,
        val offNm: String,
        val tolerance: String,
    ) : PlotCheck()

    /** The learner has not plotted enough to mark this answer. */
    data class NeedPlot(val label: String, val what: PlotNeed) : PlotCheck()

    /** The learner has not typed this answer, or it could not be read as a number. */
    data class NeedTyped(val label: String) : PlotCheck()
}

enum class PlotNeed { Position, Line }

enum class PlotUnit { Degrees, NauticalMiles, Metres, Time, None }

object ChartPlot {
    /** Exercises are set in this year; the variation on the rose is brought up to it. */
    const val CHART_YEAR = 2026

    /** Half-length of a drawn position line, each side of the mark. */
    private const val POSITION_LINE_HALF_NM = 6.0

    /** Variation for [year], east positive. */
    fun variation(v: ChartVariation, year: Int = CHART_YEAR): Double {
        val base = v.degrees + v.minutes / 60.0
        val change = v.annualChangeMinutes / 60.0 * (year - v.year)
        // The schema stores the yearly change as a signed number of minutes: negative means decreasing.
        val now = base + change
        return if (v.direction == "E") now else -now
    }

    fun magnetic(trueDeg: Double, variationEast: Double): Double = norm(trueDeg - variationEast)

    fun pointAt(geo: ChartGeometry, from: ChartPos, bearingTrue: Double, distanceNm: Double): ChartPos {
        val r = Math.toRadians(bearingTrue)
        return geo.position(geo.x(from) + distanceNm * sin(r), geo.y(from) + distanceNm * cos(r))
    }

    /**
     * Lay off [bearingTrue] from the plot's start. With a [distanceNm] it is a course and its end is the
     * new position. Without one it is a position line through the start; two position lines give a fix.
     */
    fun layOff(geo: ChartGeometry, plot: Plot, bearingTrue: Double, distanceNm: Double?): Plot {
        val start = plot.start ?: return plot
        if (distanceNm != null && distanceNm > 0.0) {
            val end = pointAt(geo, start, bearingTrue, distanceNm)
            return plot.copy(
                lines = plot.lines + PlotLine(start, end, LineKind.Course),
                result = end,
                start = end,
            )
        }
        val a = pointAt(geo, start, bearingTrue + 180.0, POSITION_LINE_HALF_NM)
        val b = pointAt(geo, start, bearingTrue, POSITION_LINE_HALF_NM)
        val lines = plot.lines + PlotLine(a, b, LineKind.PositionLine)
        val pls = lines.filter { it.kind == LineKind.PositionLine }
        if (pls.size < 2) return plot.copy(lines = lines, start = null)
        val fix = intersect(geo, pls[pls.size - 2], pls[pls.size - 1])
            ?: return plot.copy(lines = lines, start = null)
        return plot.copy(lines = lines, fixes = plot.fixes + fix, result = fix, start = null)
    }

    /** Where two lines cross, or null if they are too close to parallel to give a fix. */
    fun intersect(geo: ChartGeometry, l1: PlotLine, l2: PlotLine): ChartPos? {
        val x1 = geo.x(l1.from); val y1 = geo.y(l1.from)
        val dx1 = geo.x(l1.to) - x1; val dy1 = geo.y(l1.to) - y1
        val x2 = geo.x(l2.from); val y2 = geo.y(l2.from)
        val dx2 = geo.x(l2.to) - x2; val dy2 = geo.y(l2.to) - y2
        val den = dx1 * dy2 - dy1 * dx2
        val len = hypot(dx1, dy1) * hypot(dx2, dy2)
        if (len < 1e-9 || abs(den) / len < sin(Math.toRadians(5.0))) return null
        val t = ((x2 - x1) * dy2 - (y2 - y1) * dx2) / den
        return geo.position(x1 + t * dx1, y1 + t * dy1)
    }

    /** A copy of the line from [from] to [to], slid so it passes through [through]. Ends are [halfNm] each side. */
    fun parallelThrough(geo: ChartGeometry, from: ChartPos, to: ChartPos, through: ChartPos, halfNm: Double = 1.2): Pair<ChartPos, ChartPos>? {
        val dx = geo.x(to) - geo.x(from)
        val dy = geo.y(to) - geo.y(from)
        val len = hypot(dx, dy)
        if (len < 1e-6) return null
        val ux = dx / len
        val uy = dy / len
        val cx = geo.x(through)
        val cy = geo.y(through)
        return geo.position(cx - ux * halfNm, cy - uy * halfNm) to geo.position(cx + ux * halfNm, cy + uy * halfNm)
    }

    /**
     * True when the answer is worked out on paper and typed in, not measured off the plot:
     * heights and times always, compass conversions, and reading a position off the scales.
     */
    fun isTyped(exercise: ChartExercise, answer: ChartAnswer): Boolean =
        answer.kind == "height" || answer.kind == "time" ||
            exercise.kind == "compass-conversion" ||
            (exercise.kind == "read-position" && answer.kind == "position")

    /**
     * Mark every answer. Plotted answers use [plot]; typed answers use [typed], keyed by answer index
     * (and "<index>.lon" for the longitude of a typed position). [extent] gives the hemispheres.
     */
    fun grade(
        geo: ChartGeometry,
        exercise: ChartExercise,
        plot: Plot,
        typed: Map<String, String> = emptyMap(),
        extent: ChartExtent? = null,
    ): List<PlotCheck> = exercise.answers.mapIndexed { i, answer ->
        if (isTyped(exercise, answer)) checkTyped(geo, answer, i, typed, extent) else checkPlot(geo, answer, plot)
    }

    private fun checkPlot(geo: ChartGeometry, answer: ChartAnswer, plot: Plot): PlotCheck {
        val tol = answer.tolerance ?: defaultTolerance(answer.kind)
        return when (answer.kind) {
            "position" -> {
                val target = answer.position ?: return PlotCheck.NeedTyped(answer.label)
                val mine = plot.result ?: return PlotCheck.NeedPlot(answer.label, PlotNeed.Position)
                val off = geo.distanceNm(mine, target)
                PlotCheck.PositionResult(answer.label, off <= tol, format(off), format(tol))
            }
            "bearing" -> {
                val want = answer.number ?: return PlotCheck.NeedTyped(answer.label)
                val line = plot.lastMeasured ?: return PlotCheck.NeedPlot(answer.label, PlotNeed.Line)
                val got = geo.bearingTrue(line.from, line.to)
                PlotCheck.Result(answer.label, angleDiff(got, want) <= tol, format(Math.rint(got)), format(want), format(tol), PlotUnit.Degrees)
            }
            "distance" -> {
                val want = answer.number ?: return PlotCheck.NeedTyped(answer.label)
                val line = plot.lastMeasured ?: return PlotCheck.NeedPlot(answer.label, PlotNeed.Line)
                val got = geo.distanceNm(line.from, line.to)
                PlotCheck.Result(answer.label, abs(got - want) <= tol, format(got), format(want), format(tol), PlotUnit.NauticalMiles)
            }
            else -> PlotCheck.NeedTyped(answer.label)
        }
    }

    private fun checkTyped(
        geo: ChartGeometry,
        answer: ChartAnswer,
        index: Int,
        typed: Map<String, String>,
        extent: ChartExtent?,
    ): PlotCheck {
        val tol = answer.tolerance ?: defaultTolerance(answer.kind)
        val text = typed[index.toString()].orEmpty()
        return when (answer.kind) {
            "position" -> {
                val target = answer.position ?: return PlotCheck.NeedTyped(answer.label)
                val south = (extent?.south ?: target.lat) < 0
                val west = (extent?.west ?: target.lon) < 0
                val lat = parseDegMin(text, south) ?: return PlotCheck.NeedTyped(answer.label)
                val lon = parseDegMin(typed["$index.lon"].orEmpty(), west) ?: return PlotCheck.NeedTyped(answer.label)
                val off = geo.distanceNm(ChartPos(lat, lon), target)
                PlotCheck.PositionResult(answer.label, off <= tol, format(off), format(tol))
            }
            "time" -> {
                val want = answer.number ?: return PlotCheck.NeedTyped(answer.label)
                val got = parseTime(text) ?: return PlotCheck.NeedTyped(answer.label)
                PlotCheck.Result(answer.label, abs(got - want) <= tol, clock(got), clock(want), format(tol), PlotUnit.Time)
            }
            else -> {
                val want = answer.number ?: return PlotCheck.NeedTyped(answer.label)
                val got = parseNumber(text) ?: return PlotCheck.NeedTyped(answer.label)
                val unit = when (answer.kind) {
                    "bearing" -> PlotUnit.Degrees
                    "distance" -> PlotUnit.NauticalMiles
                    "height" -> PlotUnit.Metres
                    else -> PlotUnit.None
                }
                val diff = if (answer.kind == "bearing") angleDiff(got, want) else abs(got - want)
                PlotCheck.Result(answer.label, diff <= tol, format(got), format(want), format(tol), unit)
            }
        }
    }

    fun parseNumber(text: String): Double? = text.trim().replace(',', '.').toDoubleOrNull()

    /** "13:29", "13.29" or "1329" read as a 24-hour clock time, in minutes after midnight. */
    fun parseTime(text: String): Double? {
        val t = text.trim()
        Regex("""^(\d{1,2})[:.h](\d{2})$""").find(t)?.let { m ->
            val h = m.groupValues[1].toInt(); val min = m.groupValues[2].toInt()
            return if (h < 24 && min < 60) (h * 60 + min).toDouble() else null
        }
        Regex("""^(\d{3,4})$""").find(t)?.let { m ->
            val v = m.groupValues[1].toInt()
            val h = v / 100; val min = v % 100
            return if (h < 24 && min < 60) (h * 60 + min).toDouble() else null
        }
        return null
    }

    /** "33 02.3", "33°02.3′N" or "150 00.1 W" as signed degrees. [negative] is the chart's hemisphere. */
    fun parseDegMin(text: String, negative: Boolean): Double? {
        val nums = Regex("""\d+(?:[.,]\d+)?""").findAll(text).map { it.value.replace(',', '.') }.toList()
        if (nums.isEmpty() || nums.size > 2) return null
        val deg = nums[0].toDoubleOrNull() ?: return null
        val min = nums.getOrNull(1)?.toDoubleOrNull() ?: 0.0
        if (min >= 60.0) return null
        val v = deg + min / 60.0
        return if (negative) -v else v
    }

    fun clock(minutes: Double): String {
        val m = Math.round(minutes).toInt().mod(1440)
        return "%02d:%02d".format(java.util.Locale.US, m / 60, m % 60)
    }

    private fun defaultTolerance(kind: String): Double = when (kind) {
        "bearing" -> 2.0
        "time" -> 10.0
        else -> 0.1
    }

    private fun norm(d: Double): Double = ((d % 360.0) + 360.0) % 360.0

    private fun angleDiff(a: Double, b: Double): Double {
        var d = abs(a - b) % 360.0
        if (d > 180.0) d = 360.0 - d
        return d
    }

    fun format(v: Double): String =
        if (abs(v - Math.rint(v)) < 1e-6) v.toLong().toString()
        else "%.2f".format(java.util.Locale.US, v).trimEnd('0').trimEnd('.')
}
