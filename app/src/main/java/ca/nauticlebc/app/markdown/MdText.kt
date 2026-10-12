package ca.nauticlebc.app.markdown

/** Plain-text views of parsed inline spans, used for screen-reader descriptions and tests. */
object MdText {
    /** The spans' text with all styling removed. */
    fun plain(spans: List<MdSpan>): String = spans.joinToString("") { it.text }

    fun hasHighlight(spans: List<MdSpan>): Boolean = spans.any { it.highlight }

    /**
     * Text for a screen reader: each run of highlighted spans is passed through [wrap] (for example to add the
     * word "highlighted"). Adjacent highlighted spans, such as a highlight that contains bold text, form one run
     * so the announcement happens once. Returns null when nothing is highlighted, so callers can leave the
     * default text semantics alone.
     */
    fun spoken(spans: List<MdSpan>, wrap: (String) -> String): String? {
        if (!hasHighlight(spans)) return null
        val sb = StringBuilder()
        val run = StringBuilder()
        fun endRun() {
            if (run.isNotEmpty()) {
                sb.append(wrap(run.toString()))
                run.clear()
            }
        }
        for (span in spans) {
            if (span.highlight) {
                run.append(span.text)
            } else {
                endRun()
                sb.append(span.text)
            }
        }
        endRun()
        return sb.toString()
    }

    /**
     * The lesson as plain sentences for text-to-speech, in reading order, split into pieces no longer than
     * [maxLength] characters (speech engines refuse very long input). Tables are read row by row.
     */
    fun speechChunks(title: String, blocks: List<MdBlock>, maxLength: Int = 3500): List<String> {
        val parts = mutableListOf(title.trimEnd('.') + ".")
        for (block in blocks) {
            when (block) {
                is MdBlock.Heading -> parts += plain(block.spans).trimEnd('.') + "."
                is MdBlock.Paragraph -> parts += plain(block.spans)
                is MdBlock.Quote -> parts += plain(block.spans)
                is MdBlock.BulletList -> block.items.forEach { parts += plain(it).trimEnd('.') + "." }
                is MdBlock.NumberedList -> block.items.forEachIndexed { i, item ->
                    parts += "${block.start + i}. " + plain(item).trimEnd('.') + "."
                }
                is MdBlock.Table -> block.rows.forEach { row ->
                    val text = row.mapIndexed { i, cell ->
                        val label = block.header.getOrNull(i)?.let { plain(it) }.orEmpty()
                        val value = plain(cell)
                        if (value.isBlank()) "" else if (i == 0 || label.isBlank()) value else "$label: $value"
                    }.filter { it.isNotBlank() }.joinToString(". ")
                    if (text.isNotBlank()) parts += text.trimEnd('.') + "."
                }
                MdBlock.Rule -> {}
            }
        }
        val chunks = mutableListOf<String>()
        val current = StringBuilder()
        for (part in parts.map { it.trim() }.filter { it.isNotEmpty() }) {
            if (current.isNotEmpty() && current.length + part.length + 1 > maxLength) {
                chunks += current.toString()
                current.clear()
            }
            if (part.length > maxLength) {
                part.chunked(maxLength).forEach { chunks += it }
            } else {
                if (current.isNotEmpty()) current.append(' ')
                current.append(part)
            }
        }
        if (current.isNotEmpty()) chunks += current.toString()
        return chunks
    }
}
