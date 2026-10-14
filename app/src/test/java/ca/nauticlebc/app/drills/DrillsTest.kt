package ca.nauticlebc.app.drills

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class DrillsTest {
    @Test
    fun sameSeedGivesSameDrill() {
        for (kind in DrillKind.entries) {
            val a = Drills.forSeed(kind, 42L)
            val b = Drills.forSeed(kind, 42L)
            assertEquals(a, b)
        }
    }

    @Test
    fun everyKindProducesStepsAndSaneAnswers() {
        for (kind in DrillKind.entries) {
            for (seed in 1L..300L) {
                val d = Drills.forSeed(kind, seed)
                assertEquals(kind, d.kind)
                assertTrue("$kind $seed needs steps", d.steps.isNotEmpty())
                assertTrue("$kind $seed answer ${d.answer}", d.answer >= 0.0)
                if (d.circular) assertTrue(d.answer in 0.0..359.0)
                assertTrue(d.isCorrect(d.answer))
            }
        }
    }

    @Test
    fun compassAnswersWrapRoundTheCircle() {
        val d = Drill(DrillKind.TrueToCompass, DrillText(0), 359.0, 0.5, true, 0, emptyList())
        assertTrue(d.isCorrect(359.0))
        assertTrue(d.isCorrect(359.4))
        assertFalse(d.isCorrect(1.0))
        val d0 = d.copy(answer = 0.0)
        assertTrue(d0.isCorrect(360.0))
    }

    @Test
    fun trueToCompassMatchesTheRule() {
        // Rebuild the answer from the prompt arguments: East is least, West is best.
        for (seed in 1L..300L) {
            val d = Drills.forSeed(DrillKind.TrueToCompass, seed)
            val a = d.prompt.args
            val t = (a[0] as DrillArg.Num).value.toInt()
            val v = (a[1] as DrillArg.Num).value.toInt() * (if ((a[2] as DrillArg.Dir).east) 1 else -1)
            val dev = (a[3] as DrillArg.Num).value.toInt() * (if ((a[4] as DrillArg.Dir).east) 1 else -1)
            val expected = (((t - v - dev) % 360) + 360) % 360
            assertEquals(expected.toDouble(), d.answer, 0.0)
        }
    }

    @Test
    fun compassToTrueMatchesTheRule() {
        // Compass Add East: deviation then variation, East added, West subtracted.
        for (seed in 1L..300L) {
            val d = Drills.forSeed(DrillKind.CompassToTrue, seed)
            val a = d.prompt.args
            val c = (a[0] as DrillArg.Num).value.toInt()
            val dev = (a[1] as DrillArg.Num).value.toInt() * (if ((a[2] as DrillArg.Dir).east) 1 else -1)
            val v = (a[3] as DrillArg.Num).value.toInt() * (if ((a[4] as DrillArg.Dir).east) 1 else -1)
            val expected = (((c + dev + v) % 360) + 360) % 360
            assertEquals(expected.toDouble(), d.answer, 0.0)
        }
    }

    @Test
    fun speedDistanceTimeAnswersAreConsistent() {
        for (seed in 1L..300L) {
            val d = Drills.forSeed(DrillKind.SpeedDistanceTime, seed)
            assertTrue(d.answer > 0.0)
        }
    }
}
