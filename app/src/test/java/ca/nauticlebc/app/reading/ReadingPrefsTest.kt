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
    fun speechReadsTablesRowByRowAndSplitsLongText() {
        val blocks = MarkdownParser.parse(
            "# Cycle\n\nFirst paragraph.\n\n| Stroke | Piston |\n|---|---|\n| Intake | Down |\n\n- one\n- two"
        )
        val chunks = MdText.speechChunks("Diesel", blocks)
        assertEquals(1, chunks.size)
        assertTrue(chunks[0].contains("Intake. Piston: Down."))
        assertTrue(chunks[0].startsWith("Diesel."))
        val long = MdText.speechChunks("T", MarkdownParser.parse("a ".repeat(500) + "\n\n" + "b ".repeat(500)), maxLength = 600)
        assertTrue(long.size >= 2)
        assertTrue(long.all { it.length <= 600 })
    }
}
