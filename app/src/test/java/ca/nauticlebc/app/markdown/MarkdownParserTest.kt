package ca.nauticlebc.app.markdown

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class MarkdownParserTest {
    private fun plain(text: String) = listOf(MdSpan(text))

    @Test
    fun parsesHeadingsAndParagraphs() {
        val blocks = MarkdownParser.parse("# Title\n\n## Part\n\nFirst line\nsecond line.\n\nNext paragraph.")
        assertEquals(
            listOf(
                MdBlock.Heading(1, plain("Title")),
                MdBlock.Heading(2, plain("Part")),
                MdBlock.Paragraph(plain("First line second line.")),
                MdBlock.Paragraph(plain("Next paragraph.")),
            ),
            blocks,
        )
    }

    @Test
    fun parsesBulletsWithAllMarkers() {
        val blocks = MarkdownParser.parse("- one\n* two\n+ three")
        assertEquals(listOf(MdBlock.BulletList(listOf(plain("one"), plain("two"), plain("three")))), blocks)
    }

    @Test
    fun parsesNumberedListKeepingStartNumber() {
        val blocks = MarkdownParser.parse("3. three\n4. four\n5) five")
        assertEquals(listOf(MdBlock.NumberedList(3, listOf(plain("three"), plain("four"), plain("five")))), blocks)
    }

    @Test
    fun listEndsAtBlankLineAndSwitchesType() {
        val blocks = MarkdownParser.parse("- a\n- b\n\n1. x\n2. y\n- c")
        assertEquals(3, blocks.size)
        assertTrue(blocks[0] is MdBlock.BulletList)
        assertTrue(blocks[1] is MdBlock.NumberedList)
        assertEquals(MdBlock.BulletList(listOf(plain("c"))), blocks[2])
    }

    @Test
    fun indentedLineContinuesListItem() {
        val blocks = MarkdownParser.parse("- first part\n  still first\n- second")
        assertEquals(
            listOf(MdBlock.BulletList(listOf(plain("first part still first"), plain("second")))),
            blocks,
        )
    }

    @Test
    fun boldAndItalicInline() {
        val spans = MarkdownParser.parseInline("a **bold** and *slanted* end")
        assertEquals(
            listOf(
                MdSpan("a "),
                MdSpan("bold", bold = true),
                MdSpan(" and "),
                MdSpan("slanted", italic = true),
                MdSpan(" end"),
            ),
            spans,
        )
    }

    @Test
    fun boldAtLineStartIsNotABullet() {
        val blocks = MarkdownParser.parse("**Pipeline sample.** Written for tests.")
        assertEquals(
            listOf(MdBlock.Paragraph(listOf(MdSpan("Pipeline sample.", bold = true), MdSpan(" Written for tests.")))),
            blocks,
        )
    }

    @Test
    fun unmatchedMarkersStayLiteral() {
        assertEquals(plain("2 * 3 * 4"), MarkdownParser.parseInline("2 * 3 * 4"))
        assertEquals(plain("open **bold only"), MarkdownParser.parseInline("open **bold only"))
        assertEquals(plain("lonely * star"), MarkdownParser.parseInline("lonely * star"))
        assertEquals(plain("****"), MarkdownParser.parseInline("****"))
    }

    @Test
    fun boldItalicTogether() {
        assertEquals(listOf(MdSpan("x", bold = true, italic = true)), MarkdownParser.parseInline("***x***"))
    }

    @Test
    fun escapedMarkersAreLiteral() {
        assertEquals(plain("*not italic*"), MarkdownParser.parseInline("\\*not italic\\*"))
    }

    @Test
    fun quotesAndRules() {
        val blocks = MarkdownParser.parse("> quoted\n> more\n\n---\n\nafter")
        assertEquals(
            listOf(
                MdBlock.Quote(plain("quoted more")),
                MdBlock.Rule,
                MdBlock.Paragraph(plain("after")),
            ),
            blocks,
        )
    }

    @Test
    fun handlesWindowsLineEndingsAndEmptyInput() {
        assertEquals(listOf(MdBlock.Heading(2, plain("Hi")), MdBlock.Paragraph(plain("Body"))), MarkdownParser.parse("## Hi\r\n\r\nBody"))
        assertEquals(emptyList<MdBlock>(), MarkdownParser.parse(""))
        assertEquals(emptyList<MdBlock>(), MarkdownParser.parse("\n\n   \n"))
    }

    @Test
    fun parsesTheSampleLessonShape() {
        val body = """
            # Rule 5

            **Sample.** Intro text.

            ## What the rule asks

            Every vessel must keep a lookout, **at all times**. That means:

            - by **sight**,
            - by **hearing**,

            ## Check yourself

            1. Does it apply?
            2. Name three.
        """.trimIndent()
        val blocks = MarkdownParser.parse(body)
        assertEquals(
            listOf("Heading", "Paragraph", "Heading", "Paragraph", "BulletList", "Heading", "NumberedList"),
            blocks.map { it::class.simpleName },
        )
    }
}
