package ca.nauticlebc.app.reading

import android.content.Context

/** How lesson text is shown: a text size step (0 = normal) and relaxed spacing between lines and letters. */
data class ReadingPrefs(val sizeStep: Int = 0, val relaxed: Boolean = false, val speechStep: Int = 1) {
    val scale: Float get() = SCALES[sizeStep.coerceIn(0, SCALES.lastIndex)]
    val canGrow: Boolean get() = sizeStep < SCALES.lastIndex
    val canShrink: Boolean get() = sizeStep > 0

    fun bigger() = copy(sizeStep = (sizeStep + 1).coerceAtMost(SCALES.lastIndex))
    fun smaller() = copy(sizeStep = (sizeStep - 1).coerceAtLeast(0))
    fun toggleRelaxed() = copy(relaxed = !relaxed)
    val speechRate: Float get() = SPEECH_RATES[speechStep.coerceIn(0, SPEECH_RATES.lastIndex)]
    fun nextSpeed() = copy(speechStep = (speechStep + 1) % SPEECH_RATES.size)

    companion object {
        val SCALES = listOf(1.0f, 1.15f, 1.3f, 1.5f, 1.75f)

        /** Very slow, slow (default), normal, quick. */
        val SPEECH_RATES = listOf(0.7f, 0.85f, 1.0f, 1.2f)
    }
}

/** Keeps the learner's reading choices between launches. */
class ReadingPrefsStore(context: Context) {
    private val prefs = context.applicationContext.getSharedPreferences("reading_prefs", Context.MODE_PRIVATE)

    fun load(): ReadingPrefs = ReadingPrefs(
        sizeStep = prefs.getInt("size_step", 0).coerceIn(0, ReadingPrefs.SCALES.lastIndex),
        relaxed = prefs.getBoolean("relaxed", false),
        speechStep = prefs.getInt("speech_step", 1).coerceIn(0, ReadingPrefs.SPEECH_RATES.lastIndex),
    )

    fun save(value: ReadingPrefs) {
        prefs.edit().putInt("size_step", value.sizeStep).putBoolean("relaxed", value.relaxed)
            .putInt("speech_step", value.speechStep).apply()
    }
}
