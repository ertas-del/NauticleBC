package ca.nauticlebc.app.progress

import ca.nauticlebc.app.quiz.QuizScore

/** What the learner has done with one lesson. */
data class LessonProgress(
    val completed: Boolean = false,
    val best: QuizScore? = null,
) {
    fun withCompleted(): LessonProgress = copy(completed = true)

    /** Finishing a quiz also counts the lesson as completed. Keeps the better of the old and new score. */
    fun withQuizResult(score: QuizScore): LessonProgress {
        val old = best
        val newBest = if (old == null || score.isBetterThan(old)) score else old
        return LessonProgress(completed = true, best = newBest)
    }
}

/** Where progress is persisted. The Android implementation uses SharedPreferences. */
interface ProgressStore {
    fun load(): Map<String, LessonProgress>
    fun save(lessonId: String, progress: LessonProgress)
}

/** Holds the current progress snapshot and writes every change through to the [ProgressStore]. */
class ProgressTracker(private val store: ProgressStore) {
    private var current: Map<String, LessonProgress> = store.load()

    fun snapshot(): Map<String, LessonProgress> = current

    fun markCompleted(lessonId: String): Map<String, LessonProgress> =
        update(lessonId) { it.withCompleted() }

    fun recordQuiz(lessonId: String, score: QuizScore): Map<String, LessonProgress> {
        if (score.total <= 0) return current
        return update(lessonId) { it.withQuizResult(score) }
    }

    private fun update(lessonId: String, change: (LessonProgress) -> LessonProgress): Map<String, LessonProgress> {
        val updated = change(current[lessonId] ?: LessonProgress())
        store.save(lessonId, updated)
        current = current + (lessonId to updated)
        return current
    }
}

/** Key and value formats used by the SharedPreferences store. Pure so it can be unit tested. */
object ProgressCodec {
    private const val DONE_PREFIX = "done."
    private const val BEST_PREFIX = "best."

    fun doneKey(lessonId: String): String = DONE_PREFIX + lessonId

    fun bestKey(lessonId: String): String = BEST_PREFIX + lessonId

    fun encodeBest(score: QuizScore): String = "${score.correct}/${score.total}"

    fun decodeBest(text: String?): QuizScore? {
        if (text == null) return null
        val parts = text.split('/')
        if (parts.size != 2) return null
        val correct = parts[0].toIntOrNull() ?: return null
        val total = parts[1].toIntOrNull() ?: return null
        if (total <= 0 || correct < 0 || correct > total) return null
        return QuizScore(correct, total)
    }

    /** Rebuilds progress from the raw key/value entries of a SharedPreferences file. Unknown or bad entries are skipped. */
    fun decodeAll(entries: Map<String, Any?>): Map<String, LessonProgress> {
        val ids = mutableSetOf<String>()
        for (key in entries.keys) {
            when {
                key.startsWith(DONE_PREFIX) -> ids += key.removePrefix(DONE_PREFIX)
                key.startsWith(BEST_PREFIX) -> ids += key.removePrefix(BEST_PREFIX)
            }
        }
        val result = mutableMapOf<String, LessonProgress>()
        for (id in ids) {
            val done = entries[doneKey(id)] == true
            val best = decodeBest(entries[bestKey(id)] as? String)
            if (done || best != null) {
                result[id] = LessonProgress(completed = done || best != null, best = best)
            }
        }
        return result
    }
}
