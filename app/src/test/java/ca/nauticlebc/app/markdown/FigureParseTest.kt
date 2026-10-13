package ca.nauticlebc.app.markdown

import org.junit.Assert.assertEquals
import org.junit.Test

class FigureParseTest {
    @Test
    fun figureLineBecomesFigureBlock() {
        val blocks = MarkdownParser.parse("Before.\n\n{{figure:four-stroke-diesel-cycle}}\n\nAfter.")
        assertEquals(3, blocks.size)
        assertEquals(MdBlock.Figure("four-stroke-diesel-cycle"), blocks[1])
    }
}
