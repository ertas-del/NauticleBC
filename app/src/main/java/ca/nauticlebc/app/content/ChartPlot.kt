package ca.nauticlebc.app.content

import kotlin.math.abs
import kotlin.math.hypot

/** Two plotted points. The first tap is where the line starts, the second is where it ends. */
data class PlotMark(val points: List<ChartPos> = emptyList()) {
    fun tap(point: ChartPos): PlotMark = copy(points = (points + point).takeLast(2))
}

/** One line of a plot grade. [measured] and [expected] are numbers, not sentences. */
sealed class PlotCheck {
    data class Result(
        val label: String,
        val passed: Boolean,
        val measured: String,
        val expected: String,
        val tolerance: String,
    ) : PlotCheck()

    /** The learner has not plotted enough points to mark this answer. */
    data class NeedPoints(val label: String, val need: Int) : PlotCheck()

    /** A calculated figure (tide, clearance, time). The plot cannot mark it. */
    data class Calculated(val label: String) : PlotCheck()
}

object ChartPlot {
    fun grade(geo: ChartGeometry, exercise: ChartExercise, mark: PlotMark): List<PlotCheck> =
        exercise.answers.map { answer -> check(geo, answer, mark) }

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

    private fun check(geo: ChartGeometry, answer: ChartAnswer, mark: PlotMark): PlotCheck {
        val tol = answer.tolerance ?: defaultTolerance(answer.kind)
        return when (answer.kind) {
            "position" -> {
                val target = answer.position ?: return PlotCheck.Calculated(answer.label)
                val nearest = mark.points.minByOrNull { geo.distanceNm(it, target) }
                    ?: return PlotCheck.NeedPoints(answer.label, 1)
                val distance = geo.distanceNm(nearest, target)
                PlotCheck.Result(
                    label = answer.label,
                    passed = distance <= tol,
                    measured = format(distance),
                    expected = "0",
                    tolerance = format(tol),
                )
            }
            "bearing" -> {
                val want = answer.number ?: return PlotCheck.Calculated(answer.label)
                if (mark.points.size < 2) return PlotCheck.NeedPoints(answer.label, 2)
                val measured = geo.bearingTrue(mark.points[0], mark.points[1])
                PlotCheck.Result(
                    label = answer.label,
                    passed = angleDiff(measured, want) <= tol,
                    measured = format(measured),
                    expected = format(want),
                    tolerance = format(tol),
                )
            }
            "distance" -> {
                val want = answer.number ?: return PlotCheck.Calculated(answer.label)
                if (mark.points.size < 2) return PlotCheck.NeedPoints(answer.label, 2)
                val measured = geo.distanceNm(mark.points[0], mark.points[1])
                PlotCheck.Result(
                    label = answer.label,
                    passed = abs(measured - want) <= tol,
                    measured = format(measured),
                    expected = format(want),
                    tolerance = format(tol),
                )
            }
            else -> PlotCheck.Calculated(answer.label)
        }
    }

    private fun defaultTolerance(kind: String): Double = if (kind == "bearing") 2.0 else 0.1

    private fun angleDiff(a: Double, b: Double): Double {
        var d = abs(a - b) % 360.0
        if (d > 180.0) d = 360.0 - d
        return d
    }

    private fun format(v: Double): String =
        if (abs(v - Math.rint(v)) < 1e-6) v.toLong().toString() else "%.2f".format(java.util.Locale.US, v)
}
