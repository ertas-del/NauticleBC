package ca.nauticlebc.app.markdown

/** A run of inline text with its emphasis. [highlight] marks exam-focus text written as `==text==`. */
data class MdSpan(
    val text: String,
    val bold: Boolean = false,
    val italic: Boolean = false,
    val highlight: Boolean = false,
)

/** Block-level pieces of the small Markdown subset used by lesson bodies. */
sealed interface MdBlock {
    data class Heading(val level: Int, val spans: List<MdSpan>) : MdBlock
    data class Paragraph(val spans: List<MdSpan>) : MdBlock
    data class Quote(val spans: List<MdSpan>) : MdBlock
    data class BulletList(val items: List<List<MdSpan>>) : MdBlock
    data class NumberedList(val start: Int, val items: List<List<MdSpan>>) : MdBlock
    data object Rule : MdBlock
}

/**
 * Small, forgiving Markdown parser. Supports: `#` headings, paragraphs, `-`/`*`/`+` bullets,
 * `1.` numbered lists, `>` quotes, `---` rules, `**bold**`, `*italic*` and `==highlight==`. Anything else is kept as plain text.
 * It never throws: unknown syntax simply shows up as text.
 */
object MarkdownParser {
    private val headingRe = Regex("^(#{1,6})\\s+(.*)$")
    private val bulletRe = Regex("^\\s*[-*+]\\s+(.*)$")
    private val numberedRe = Regex("^\\s*(\\d{1,9})[.)]\\s+(.*)$")
    private val ruleRe = Regex("^\\s*([-*_])(\\s*\\1){2,}\\s*$")
    private val quoteRe = Regex("^\\s*>\\s?(.*)$")

    fun parse(markdown: String): List<MdBlock> {
        val blocks = mutableListOf<MdBlock>()
        val paragraph = mutableListOf<String>()
        val quote = mutableListOf<String>()
        val bullets = mutableListOf<String>()
        val numbers = mutableListOf<String>()
        var numberStart = 1

        fun flushText() {
            if (paragraph.isNotEmpty()) {
                blocks += MdBlock.Paragraph(parseInline(paragraph.joinToString(" ")))
                paragraph.clear()
            }
            if (quote.isNotEmpty()) {
                blocks += MdBlock.Quote(parseInline(quote.joinToString(" ")))
                quote.clear()
            }
        }

        fun flushLists() {
            if (bullets.isNotEmpty()) {
                blocks += MdBlock.BulletList(bullets.map { parseInline(it) })
                bullets.clear()
            }
            if (numbers.isNotEmpty()) {
                blocks += MdBlock.NumberedList(numberStart, numbers.map { parseInline(it) })
                numbers.clear()
            }
        }

        fun flushAll() {
            flushText()
            flushLists()
        }

        for (rawLine in markdown.replace("\r\n", "\n").replace('\r', '\n').split('\n')) {
            val line = rawLine.trimEnd()
            if (line.isBlank()) {
                flushAll()
                continue
            }
            val heading = headingRe.matchEntire(line.trimStart())
            if (heading != null && !rawLine.startsWith("    ")) {
                flushAll()
                blocks += MdBlock.Heading(heading.groupValues[1].length, parseInline(heading.groupValues[2].trim()))
                continue
            }
            if (ruleRe.matches(line)) {
                flushAll()
                blocks += MdBlock.Rule
                continue
            }
            val quoteMatch = quoteRe.matchEntire(line)
            if (quoteMatch != null) {
                flushLists()
                if (paragraph.isNotEmpty()) flushText()
                quote += quoteMatch.groupValues[1].trim()
                continue
            }
            val bullet = bulletRe.matchEntire(line)
            if (bullet != null) {
                flushText()
                if (numbers.isNotEmpty()) flushLists()
                bullets += bullet.groupValues[1].trim()
                continue
            }
            val numbered = numberedRe.matchEntire(line)
            if (numbered != null) {
                flushText()
                if (bullets.isNotEmpty()) flushLists()
                if (numbers.isEmpty()) {
                    numberStart = numbered.groupValues[1].toIntOrNull() ?: 1
                }
                numbers += numbered.groupValues[2].trim()
                continue
            }
            // Plain text line.
            val indented = line.first().isWhitespace()
            val openList = if (bullets.isNotEmpty()) bullets else numbers
            if (indented && openList.isNotEmpty()) {
                // Continuation of the previous list item.
                openList[openList.lastIndex] = openList.last() + " " + line.trim()
                continue
            }
            flushLists()
            if (quote.isNotEmpty()) flushText()
            paragraph += line.trim()
        }
        flushAll()
        return blocks
    }

    /**
     * Parses `**bold**`, `*italic*` and `==highlight==`. Unmatched markers are kept as literal characters.
     * The three styles toggle independently, so they can be nested in any order; a `==` only opens a highlight
     * when a closing `==` follows and the next character is not whitespace (so `a == b` stays literal).
     */
    fun parseInline(text: String): List<MdSpan> {
        val out = mutableListOf<MdSpan>()
        val sb = StringBuilder()
        var bold = false
        var italic = false
        var highlight = false

        fun flush() {
            if (sb.isNotEmpty()) {
                out += MdSpan(sb.toString(), bold, italic, highlight)
                sb.clear()
            }
        }

        var i = 0
        while (i < text.length) {
            val c = text[i]
            if (c == '\\' && i + 1 < text.length && "\\*#-_`>=".indexOf(text[i + 1]) >= 0) {
                sb.append(text[i + 1])
                i += 2
                continue
            }
            if (c == '=' && text.startsWith("==", i)) {
                if (highlight) {
                    flush()
                    highlight = false
                    i += 2
                    continue
                }
                val nextOk = i + 2 < text.length && !text[i + 2].isWhitespace() && text[i + 2] != '='
                if (nextOk && indexOfUnescaped(text, "==", i + 2) != -1) {
                    flush()
                    highlight = true
                    i += 2
                    continue
                }
                // Not a usable marker: keep every '=' of the run as plain text.
                var end = i
                while (end < text.length && text[end] == '=') end++
                sb.append(text, i, end)
                i = end
                continue
            }
            if (c == '*' && text.startsWith("**", i)) {
                if (bold) {
                    flush()
                    bold = false
                    i += 2
                    continue
                }
                val close = text.indexOf("**", i + 2)
                if (close > i + 2) {
                    flush()
                    bold = true
                    i += 2
                    continue
                }
                sb.append("**")
                i += 2
                continue
            }
            if (c == '*') {
                if (italic) {
                    flush()
                    italic = false
                    i += 1
                    continue
                }
                val nextOk = i + 1 < text.length && !text[i + 1].isWhitespace() && text[i + 1] != '*'
                if (nextOk && text.indexOf('*', i + 1) != -1) {
                    flush()
                    italic = true
                    i += 1
                    continue
                }
            }
            sb.append(c)
            i += 1
        }
        flush()
        return out
    }

    /** Index of the first [token] at or after [from] that is not preceded by a backslash escape, or -1. */
    private fun indexOfUnescaped(text: String, token: String, from: Int): Int {
        var i = from
        while (i < text.length) {
            if (text[i] == '\\') {
                i += 2
                continue
            }
            if (text.startsWith(token, i)) return i
            i++
        }
        return -1
    }
}
