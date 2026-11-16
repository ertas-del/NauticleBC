package ca.nauticlebc.app.content

import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test
import kotlin.math.abs

class ChartPlotTest {
    private val geo = ChartGeometry(ChartExtent(south = 33.0, west = -150.0, north = 33.2, east = -149.8))
    private val from = ChartPos(33.0, -150.0)
    private val oneMileNorth = ChartPos(33.0 + 1.0 / 60.0, -150.0)

    // Training Harbour, with the numbers from content/charts/training-harbour.*.json.
    private val harbour = ChartGeometry(ChartExtent(south = 33.0, west = -150.08333, north = 33.08333, east = -149.96404))
    private val pineIslandLight = ChartPos(33.03833, -149.99784)
    private val gullRockBeacon = ChartPos(33.04167, -150.0553)

    @Test
    fun oneMinuteOfLatitudeIsOneMileDueNorth() {
        assertTrue(abs(geo.distanceNm(from, oneMileNorth) - 1.0) < 0.01)
        assertTrue(abs(geo.bearingTrue(from, oneMileNorth)) < 0.1)
    }

    @Test
    fun aMeasuredLineWithinToleranceIsRight() {
        val exercise = exercise(
            answer("Course", "bearing", JsonPrimitive(0), 2.0),
            answer("Distance", "distance", JsonPrimitive(1.0), 0.1),
        )
        val plot = Plot().measureTap(from).measureTap(oneMileNorth)
        val checks = ChartPlot.grade(geo, exercise, plot)
        assertTrue(checks.all { it is PlotCheck.Result && it.passed })
    }

    @Test
    fun aShortLineFailsTheDistance() {
        val exercise = exercise(answer("Distance", "distance", JsonPrimitive(3.0), 0.1))
        val plot = Plot().measureTap(from).measureTap(oneMileNorth)
        val check = ChartPlot.grade(geo, exercise, plot).single()
        assertTrue(check is PlotCheck.Result && !check.passed)
    }

    @Test
    fun oneTapIsNotALine() {
        val exercise = exercise(answer("Course", "bearing", JsonPrimitive(0), 2.0))
        val check = ChartPlot.grade(geo, exercise, Plot().measureTap(from)).single()
        assertTrue(check is PlotCheck.NeedPlot && check.what == PlotNeed.Line)
    }

    @Test
    fun aPointWithinATenthOfAMileIsRight() {
        val target = buildJsonObject { put("lat", oneMileNorth.lat); put("lon", oneMileNorth.lon) }
        val exercise = exercise(answer("Fix", "position", target, 0.1))
        val near = ChartPos(oneMileNorth.lat + 0.02 / 60.0, oneMileNorth.lon)
        val check = ChartPlot.grade(geo, exercise, Plot().point(near)).single()
        assertTrue(check is PlotCheck.PositionResult && check.passed)
    }

    @Test
    fun theParallelRuleKeepsTheSameBearing() {
        val rose = ChartPos(33.1, -149.9)
        val (a, b) = ChartPlot.parallelThrough(geo, from, oneMileNorth, rose)!!
        assertTrue(abs(geo.bearingTrue(a, b)) < 0.1 || abs(geo.bearingTrue(a, b) - 360.0) < 0.1)
        assertTrue(geo.distanceNm(rose, a) > 0.5)
    }

    @Test
    fun variationIsBroughtUpToTheExerciseYear() {
        // 15°00'E in 2020, decreasing 5' a year, is 14°30'E in 2026.
        val v = ChartPlot.variation(ChartVariation(15, 0, "E", 2020, -5.0), 2026)
        assertEquals(14.5, v, 1e-9)
        assertEquals(19.5, ChartPlot.magnetic(34.0, v), 1e-9)
    }

    @Test
    fun theDrRunFromTheExerciseLandsOnTheAnswer() {
        // 11:15 to 11:45 at 6 knots on 330° true is 3 miles.
        val plot = ChartPlot.layOff(harbour, Plot().withStart(ChartPos(33.01, -150.01573)), 330.0, 3.0)
        val dr = plot.result!!
        assertTrue(harbour.distanceNm(dr, ChartPos(33.0533, -150.04555)) < 0.05)
        assertEquals(dr, plot.start)
    }

    @Test
    fun twoCorrectedBearingsCutAtTheFix() {
        // Compass 034° and 294°, deviation 3°W, variation 14°30'E: true 045.5° and 305.5°.
        var plot = Plot().withStart(pineIslandLight)
        plot = ChartPlot.layOff(harbour, plot, 45.5, null)
        assertNull(plot.result)
        plot = ChartPlot.layOff(harbour, plot.withStart(gullRockBeacon), 305.5, null)
        val fix = plot.result
        assertNotNull(fix)
        assertTrue(harbour.distanceNm(fix!!, ChartPos(33.02, -150.01971)) < 0.15)
        assertEquals(1, plot.fixes.size)
    }

    @Test
    fun nearlyParallelLinesGiveNoFix() {
        var plot = ChartPlot.layOff(harbour, Plot().withStart(pineIslandLight), 45.0, null)
        plot = ChartPlot.layOff(harbour, plot.withStart(gullRockBeacon), 47.0, null)
        assertTrue(plot.fixes.isEmpty())
    }

    @Test
    fun typedAnswersAreMarkedForCalculations() {
        val exercise = ChartExercise(
            id = "w", chartId = "c", kind = "passage-window", difficulty = "hard", prompt = "p",
            answers = listOf(
                answer("Height", "height", JsonPrimitive(3.2), 0.05),
                answer("Time", "time", JsonPrimitive(809), 10.0),
            ),
        )
        val checks = ChartPlot.grade(geo, exercise, Plot(), mapOf("0" to "3.2", "1" to "13:35"))
        assertTrue(checks.all { it is PlotCheck.Result && it.passed })
        val late = ChartPlot.grade(geo, exercise, Plot(), mapOf("0" to "3,2", "1" to "14:00"))
        assertTrue((late[1] as PlotCheck.Result).let { !it.passed && it.expected == "13:29" })
        assertTrue(ChartPlot.grade(geo, exercise, Plot()).all { it is PlotCheck.NeedTyped })
    }

    @Test
    fun aTypedPositionIsReadInDegreesAndMinutes() {
        val target = buildJsonObject { put("lat", 33.03833); put("lon", -149.99784) }
        val exercise = ChartExercise(
            id = "r", chartId = "c", kind = "read-position", difficulty = "easy", prompt = "p",
            answers = listOf(answer("Light", "position", target, 0.1)),
        )
        val typed = mapOf("0" to "33°02.3′N", "0.lon" to "149 59.9")
        val check = ChartPlot.grade(harbour, exercise, Plot(), typed, ChartExtent(33.0, -150.08333, 33.08333, -149.96404))
            .single()
        assertTrue(check is PlotCheck.PositionResult && check.passed)
    }

    @Test
    fun clockTimesParse() {
        assertEquals(809.0, ChartPlot.parseTime("13:29")!!, 1e-9)
        assertEquals(809.0, ChartPlot.parseTime("1329")!!, 1e-9)
        assertNull(ChartPlot.parseTime("25:00"))
        assertEquals("13:29", ChartPlot.clock(809.0))
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
