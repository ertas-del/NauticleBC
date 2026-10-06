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
}
