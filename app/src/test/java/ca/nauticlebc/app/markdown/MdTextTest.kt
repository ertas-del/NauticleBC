package ca.nauticlebc.app.markdown

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class MdTextTest {
    private val wrap = { run: String -> "[hl: $run]" }

    @Test
    fun plainDropsStyling() {
        val spans = MarkdownParser.parseInline("a **b** ==c== d")
        assertEquals("a b c d", MdText.plain(spans))
    }

    @Test
    fun noHighlightMeansNoCustomDescription() {
        val spans = MarkdownParser.parseInline("just **bold** text")
        assertFalse(MdText.hasHighlight(spans))
        assertNull(MdText.spoken(spans, wrap))
    }

    @Test
    fun spokenWrapsEachHighlightedRun() {
        val spans = MarkdownParser.parseInline("keep ==a lookout== and ==two== things")
        assertTrue(MdText.hasHighlight(spans))
        assertEquals("keep [hl: a lookout] and [hl: two] things", MdText.spoken(spans, wrap))
    }

    @Test
    fun adjacentHighlightedSpansAreAnnouncedOnce() {
        // Bold inside a highlight splits it into three spans, but it is one highlighted run.
        val spans = MarkdownParser.parseInline("==see **12 minutes** of arc== now")
        assertEquals("[hl: see 12 minutes of arc] now", MdText.spoken(spans, wrap))
    }

    @Test
    fun highlightAtStartAndEnd() {
        assertEquals("[hl: all]", MdText.spoken(MarkdownParser.parseInline("==all=="), wrap))
    }

    @Test
    fun unbalancedMarkersAreNotAnnounced() {
        assertNull(MdText.spoken(MarkdownParser.parseInline("open ==never closed"), wrap))
    }
}
