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

    @Test
    fun highlightSpan() {
        assertEquals(
            listOf(MdSpan("keep a "), MdSpan("proper lookout", highlight = true), MdSpan(" always")),
            MarkdownParser.parseInline("keep a ==proper lookout== always"),
        )
    }

    @Test
    fun highlightInsideBoldAndItalic() {
        assertEquals(
            listOf(
                MdSpan("a ", bold = true),
                MdSpan("b", bold = true, highlight = true),
                MdSpan(" c", bold = true),
            ),
            MarkdownParser.parseInline("**a ==b== c**"),
        )
        assertEquals(
            listOf(MdSpan("x", italic = true, highlight = true)),
            MarkdownParser.parseInline("*==x==*"),
        )
    }

    @Test
    fun boldInsideHighlight() {
        assertEquals(
            listOf(
                MdSpan("see ", highlight = true),
                MdSpan("12 minutes", bold = true, highlight = true),
                MdSpan(" of arc", highlight = true),
            ),
            MarkdownParser.parseInline("==see **12 minutes** of arc=="),
        )
    }

    @Test
    fun highlightThatCrossesBoldStillBalances() {
        // Styles toggle independently, so overlapping (not strictly nested) markers still render.
        assertEquals(
            listOf(
                MdSpan("a", highlight = true),
                MdSpan("b", bold = true, highlight = true),
                MdSpan("c", bold = true),
            ),
            MarkdownParser.parseInline("==a**b==c**"),
        )
    }

    @Test
    fun unbalancedHighlightMarkersStayLiteral() {
        assertEquals(plain("open ==never closed"), MarkdownParser.parseInline("open ==never closed"))
        assertEquals(plain("never opened== here"), MarkdownParser.parseInline("never opened== here"))
        assertEquals(plain("a == b"), MarkdownParser.parseInline("a == b"))
        assertEquals(plain("a == b == c"), MarkdownParser.parseInline("a == b == c"))
        assertEquals(plain("===="), MarkdownParser.parseInline("===="))
        assertEquals(plain("===x"), MarkdownParser.parseInline("===x"))
        assertEquals(plain("=="), MarkdownParser.parseInline("=="))
        assertEquals(plain("x ==\u0020"), MarkdownParser.parseInline("x ==\u0020"))
    }

    @Test
    fun escapedHighlightMarkersAreLiteral() {
        assertEquals(plain("==not highlighted=="), MarkdownParser.parseInline("\\=\\=not highlighted\\=\\="))
        // An escaped closer does not count as a closer.
        assertEquals(plain("==open =="), MarkdownParser.parseInline("==open \\=="))
    }

    @Test
    fun twoHighlightsInOneLine() {
        val spans = MarkdownParser.parseInline("==one== and ==two==")
        assertEquals(
            listOf(MdSpan("one", highlight = true), MdSpan(" and "), MdSpan("two", highlight = true)),
            spans,
        )
    }

    @Test
    fun highlightWorksInHeadingsListsAndAcrossWrappedLines() {
        val blocks = MarkdownParser.parse("## The ==rule==\n\n- item ==one==\n\nwraps ==over\ntwo lines== here")
        assertEquals(MdBlock.Heading(2, listOf(MdSpan("The "), MdSpan("rule", highlight = true))), blocks[0])
        assertEquals(
            MdBlock.BulletList(listOf(listOf(MdSpan("item "), MdSpan("one", highlight = true)))),
            blocks[1],
        )
        assertEquals(
            MdBlock.Paragraph(listOf(MdSpan("wraps "), MdSpan("over two lines", highlight = true), MdSpan(" here"))),
            blocks[2],
        )
    }

    @Test
    fun highlightMarkersDoNotLeakAcrossListItems() {
        val blocks = MarkdownParser.parse("- ==open\n- close==")
        assertEquals(
            listOf(MdBlock.BulletList(listOf(plain("==open"), plain("close==")))),
            blocks,
        )
    }

    @Test
    fun parsesPipeTablesAndSkipsTheSeparatorRow() {
        val blocks = MarkdownParser.parse("Before\n\n| Stroke | Piston |\n|---|:---:|\n| **Intake** | Down |\n| Power | Down |\n\nAfter")
        assertEquals(3, blocks.size)
        val table = blocks[1] as MdBlock.Table
        assertEquals(listOf(plain("Stroke"), plain("Piston")), table.header)
        assertEquals(2, table.rows.size)
        assertEquals(listOf(MdSpan("Intake", bold = true)), table.rows[0][0])
        assertEquals(plain("Down"), table.rows[1][1])
        assertEquals(MdBlock.Paragraph(plain("After")), blocks[2])
    }

    @Test
    fun shortRowsArePaddedToTheHeaderWidth() {
        val table = MarkdownParser.parse("| A | B | C |\n|---|---|---|\n| 1 | 2 |").single() as MdBlock.Table
        assertEquals(3, table.rows[0].size)
        assertTrue(table.rows[0][2].isEmpty())
    }

    @Test
    fun fencedBlocksKeepLineBreaksAndSpacing() {
        val blocks = MarkdownParser.parse("Before\n\n```\nTrue --(var)--> Magnetic\n   |\n  deviation\n```\n\nAfter")
        assertEquals(3, blocks.size)
        assertEquals(MdBlock.Code("True --(var)--> Magnetic\n   |\n  deviation"), blocks[1])
        assertEquals(MdBlock.Paragraph(plain("After")), blocks[2])
    }
}
