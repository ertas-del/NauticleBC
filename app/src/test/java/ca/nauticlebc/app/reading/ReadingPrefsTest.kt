package ca.nauticlebc.app.reading

import ca.nauticlebc.app.markdown.MarkdownParser
import ca.nauticlebc.app.markdown.MdText
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class ReadingPrefsTest {
    @Test
    fun sizeStepsStayInRange() {
        var p = ReadingPrefs()
        assertFalse(p.canShrink)
        repeat(10) { p = p.bigger() }
        assertEquals(ReadingPrefs.SCALES.lastIndex, p.sizeStep)
        assertFalse(p.canGrow)
        repeat(10) { p = p.smaller() }
        assertEquals(0, p.sizeStep)
        assertEquals(1.0f, p.scale, 0.0f)
    }

    @Test
    fun voiceSpeedCyclesAndDefaultsToSlow() {
        var p = ReadingPrefs()
        assertEquals(0.85f, p.speechRate, 0.0f)
        repeat(ReadingPrefs.SPEECH_RATES.size) { p = p.nextSpeed() }
        assertEquals(1, p.speechStep)
    }

    @Test
    fun teacherSpeechHasKeyFactsPausesAndSpellsOutSymbols() {
        val blocks = MarkdownParser.parse(
            "## Cycle\n\nAir is squeezed to ==14:1== at 12 kn, about 50%.\n\n| Stroke | Piston |\n|---|---|\n| Intake | Down |\n\n## Check yourself\n\n1. What ignites the fuel"
        )
        val items = MdText.speechItems("Diesel", listOf("Four strokes"), blocks)
        assertEquals("Lesson: Diesel.", items[0].text)
        assertTrue(items.any { it.text == "Four strokes." })
        assertTrue(items.any { it.text.contains("12 knots") && it.text.contains("50 percent") && it.text.contains(", 14:1,") })
        assertTrue(items.any { it.text.contains("Intake. Piston: Down.") })
        val question = items.first { it.text.startsWith("1. What ignites") }
        assertEquals(6000, question.pauseMs)
        assertTrue(items.last().text.contains("try the quiz"))
        val long = MdText.speechItems("T", emptyList(), MarkdownParser.parse(("Sentence number one. ").repeat(100)), maxLength = 200)
        assertTrue(long.all { it.text.length <= 200 })
    }

    @Test
    fun speakableSaysMarineShortFormsTheWayAPersonWould() {
        assertEquals(
            "The collision regulations say an eepirb and a sart.",
            MdText.speakable("The COLREGS say an EPIRB and a SART."),
        )
        assertEquals("G H A is 32 degrees 15.4 minutes.", MdText.speakable("GHA is 32° 15.4′."))
        assertEquals("running fix", MdText.speakable("RFix"))
    }

    @Test
    fun bestVoiceIsOfflineAndPrefersCanadianEnglish() {
        val voices = listOf(
            VoiceChoice("net", quality = 500, languageTag = "en-CA", needsNetwork = true, installed = true),
            VoiceChoice("us", quality = 400, languageTag = "en-US", needsNetwork = false, installed = true),
            VoiceChoice("ca", quality = 400, languageTag = "en-CA", needsNetwork = false, installed = true),
            VoiceChoice("missing", quality = 500, languageTag = "en-GB", needsNetwork = false, installed = false),
        )
        assertEquals("ca", bestOfflineEnglishVoice(voices)?.name)
        assertEquals(null, bestOfflineEnglishVoice(listOf(voices.first())))
    }
}
