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

    /** One thing to say, then a silent pause before the next. */
    data class SpeechItem(val text: String, val pauseMs: Int)

    /** Spoken form of a run of spans: highlighted words get a short pause either side so the voice leans on them. */
    private fun say(spans: List<MdSpan>): String {
        val sb = StringBuilder()
        var inHighlight = false
        for (span in spans) {
            if (span.highlight && !inHighlight) sb.append(", ")
            if (!span.highlight && inHighlight) sb.append(", ")
            inHighlight = span.highlight
            sb.append(span.text)
        }
        return speakable(sb.toString())
    }

    /** Spells out symbols and short forms that speech engines read badly. */
    fun speakable(text: String): String {
        var t = text
        t = Regex("(\\d)\\s?°\\s?([NSEW])\\b").replace(t) { "${it.groupValues[1]} degrees ${it.groupValues[2]}" }
        t = t.replace("°", " degrees")
        t = Regex("(\\d)\\s?%").replace(t, "$1 percent")
        t = Regex("(\\d)\\s?kn\\b").replace(t, "$1 knots")
        t = Regex("(\\d)\\s?nm\\b").replace(t, "$1 nautical miles")
        t = Regex("(\\d)\\s?kW\\b").replace(t, "$1 kilowatts")
        t = Regex("(\\d)\\s?m\\b").replace(t, "$1 metres")
        t = Regex("(\\d)\\s?min\\b").replace(t, "$1 minutes")
        t = t.replace("e.g.", "for example").replace("i.e.", "that is").replace(" vs ", " versus ")
        t = t.replace("→", " leads to ").replace("≥", " at least ").replace("≤", " at most ")
        t = t.replace("×", " times ").replace(" x ", " times ").replace("&", " and ").replace("~", "about ")
        t = t.replace("—", ", ").replace(" - ", ", ")
        t = Regex("\\s+").replace(t, " ")
        t = Regex(",\\s*,").replace(t, ",").replace(" ,", ",")
        return t.trim().trimStart(',', ' ')
    }

    /** Sentences, so long paragraphs can be cut into pieces the speech engine will accept. */
    private fun sentences(text: String, maxLength: Int): List<String> {
        if (text.length <= maxLength) return listOf(text)
        val out = mutableListOf<String>()
        val cur = StringBuilder()
        for (sentence in text.split(Regex("(?<=[.!?])\\s+"))) {
            if (cur.isNotEmpty() && cur.length + sentence.length + 1 > maxLength) {
                out += cur.toString()
                cur.clear()
            }
            if (sentence.length > maxLength) sentence.chunked(maxLength).forEach { out += it }
            else {
                if (cur.isNotEmpty()) cur.append(' ')
                cur.append(sentence)
            }
        }
        if (cur.isNotEmpty()) out += cur.toString()
        return out
    }

    /**
     * The lesson as a teacher might give it: the title, the key facts to listen for, then the lesson itself with
     * a pause after each heading, paragraph, bullet and table row, and a longer silence after each "Check
     * yourself" question so the learner can think. [maxLength] caps each spoken piece.
     */
    fun speechItems(
        title: String,
        keyFacts: List<String>,
        blocks: List<MdBlock>,
        figures: List<ca.nauticlebc.app.content.Figure> = emptyList(),
        maxLength: Int = 3500,
    ): List<SpeechItem> {
        val items = mutableListOf<SpeechItem>()
        fun add(text: String, pause: Int) {
            val t = text.trim()
            if (t.isEmpty()) return
            val pieces = sentences(t, maxLength)
            pieces.forEachIndexed { i, piece -> items += SpeechItem(piece, if (i == pieces.lastIndex) pause else 150) }
        }
        add("Lesson: " + speakable(title).trimEnd('.') + ".", 1200)
        val facts = keyFacts.map { speakable(it).trim() }.filter { it.isNotEmpty() }
        if (facts.isNotEmpty()) {
            add("First, the key facts to listen for.", 800)
            facts.forEach { add(it.trimEnd('.') + ".", 900) }
            add("Now, the lesson.", 1200)
        }
        var inQuiz = false
        for (block in blocks) {
            when (block) {
                is MdBlock.Heading -> {
                    val heading = plain(block.spans).trim()
                    inQuiz = heading.contains("check yourself", ignoreCase = true)
                    if (inQuiz) add("Check yourself. Pause and think about each question.", 1500)
                    else add(speakable(heading).trimEnd('.') + ".", 1200)
                }
                is MdBlock.Paragraph -> add(say(block.spans), 1000)
                is MdBlock.Quote -> add(say(block.spans), 1000)
                is MdBlock.BulletList -> block.items.forEach { add(say(it).trimEnd('.') + ".", 700) }
                is MdBlock.NumberedList -> block.items.forEachIndexed { i, item ->
                    add("${block.start + i}. " + say(item).trimEnd('.') + (if (inQuiz) "?" else "."), if (inQuiz) 6000 else 700)
                }
                is MdBlock.Table -> block.rows.forEach { row ->
                    val text = row.mapIndexed { i, cell ->
                        val label = block.header.getOrNull(i)?.let { plain(it) }.orEmpty()
                        val value = say(cell)
                        if (value.isBlank()) "" else if (i == 0 || label.isBlank()) value else "${speakable(label)}: $value"
                    }.filter { it.isNotBlank() }.joinToString(". ")
                    add(text.trimEnd('.') + ".", 1000)
                }
                is MdBlock.Code -> add("There is a small diagram here. Look at it on the screen.", 1500)
                is MdBlock.Figure -> figures.firstOrNull { it.id == block.id }?.let {
                    add("Picture: " + speakable(it.caption).trimEnd('.') + ". " + speakable(it.alt).trimEnd('.') + ".", 1500)
                }
                MdBlock.Rule -> {}
            }
        }
        add("That is the end of the lesson. When you are ready, try the quiz.", 0)
        return items
    }
}
