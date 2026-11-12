package ca.nauticlebc.app.content

import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import org.junit.Assert.assertTrue
import org.junit.Test
import kotlin.math.abs

class ChartPlotTest {
    private val geo = ChartGeometry(ChartExtent(south = 33.0, west = -150.0, north = 33.2, east = -149.8))
    private val from = ChartPos(33.0, -150.0)
    private val oneMileNorth = ChartPos(33.0 + 1.0 / 60.0, -150.0)

    @Test
    fun oneMinuteOfLatitudeIsOneMileDueNorth() {
        assertTrue(abs(geo.distanceNm(from, oneMileNorth) - 1.0) < 0.01)
        assertTrue(abs(geo.bearingTrue(from, oneMileNorth)) < 0.1)
    }

    @Test
    fun aBearingAndDistanceWithinTolerancePass() {
        val exercise = exercise(
            answer("Course", "bearing", JsonPrimitive(0), 2.0),
            answer("Distance", "distance", JsonPrimitive(1.0), 0.1),
        )
        val checks = ChartPlot.grade(geo, exercise, PlotMark(listOf(from, oneMileNorth)))
        assertTrue(checks.all { it is PlotCheck.Result && it.passed })
    }

    @Test
    fun aShortPlotFailsTheDistance() {
        val exercise = exercise(answer("Distance", "distance", JsonPrimitive(3.0), 0.1))
        val check = ChartPlot.grade(geo, exercise, PlotMark(listOf(from, oneMileNorth))).single()
        assertTrue(check is PlotCheck.Result && !check.passed)
    }

    @Test
    fun onePointCannotMarkABearing() {
        val exercise = exercise(answer("Course", "bearing", JsonPrimitive(0), 2.0))
        val check = ChartPlot.grade(geo, exercise, PlotMark(listOf(from))).single()
        assertTrue(check is PlotCheck.NeedPoints && check.need == 2)
    }

    @Test
    fun aPositionWithinATenthOfAMilePasses() {
        val target = buildJsonObject { put("lat", oneMileNorth.lat); put("lon", oneMileNorth.lon) }
        val exercise = exercise(answer("Fix", "position", target, 0.1))
        val near = ChartPos(oneMileNorth.lat + 0.02 / 60.0, oneMileNorth.lon)
        val check = ChartPlot.grade(geo, exercise, PlotMark(listOf(near))).single()
        assertTrue(check is PlotCheck.Result && check.passed)
    }

    @Test
    fun theParallelRuleKeepsTheSameBearing() {
        val rose = ChartPos(33.1, -149.9)
        val (a, b) = ChartPlot.parallelThrough(geo, from, oneMileNorth, rose)!!
        assertTrue(abs(geo.bearingTrue(a, b)) < 0.1 || abs(geo.bearingTrue(a, b) - 360.0) < 0.1)
        assertTrue(geo.distanceNm(rose, a) > 0.5)
    }

    private fun answer(label: String, kind: String, value: kotlinx.serialization.json.JsonElement, tolerance: Double) =
        ChartAnswer(label = label, kind = kind, value = value, tolerance = tolerance, unit = null)

    private fun exercise(vararg answers: ChartAnswer) = ChartExercise(
        id = "ex",
        chartId = "training-harbour",
        kind = "plot",
        difficulty = "easy",
        prompt = "Plot it.",
        answers = answers.toList(),
    )
}
