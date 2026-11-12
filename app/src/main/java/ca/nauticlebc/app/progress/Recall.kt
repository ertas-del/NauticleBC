package ca.nauticlebc.app.progress

import ca.nauticlebc.app.quiz.QuizState

/**
 * A question the learner has missed. It stays due until they answer it correctly on two different days.
 * A wrong answer clears those days. A question they have only ever got right is not stored.
 */
data class RecallMemory(val correctDays: List<String>) {
    val mastered: Boolean get() = correctDays.size >= DAYS_REQUIRED

    /** Due today when it has been missed and not yet secured on two different days. */
    fun isDue(today: String): Boolean {
        if (mastered) return false
        if (correctDays.isEmpty()) return true
        return correctDays.last() != today
    }

    companion object {
        const val DAYS_REQUIRED = 2
    }
}

object Recall {
    /**
     * Folds one answer into memory.
     * A first-time correct answer returns null so it never enters the pile.
     * A wrong answer starts (or restarts) the two-day count.
     */
    fun record(existing: RecallMemory?, correct: Boolean, today: String): RecallMemory? {
        if (!correct) return RecallMemory(emptyList())
        if (existing == null || existing.mastered) return existing
        if (today in existing.correctDays) return existing
        return RecallMemory(existing.correctDays + today)
    }
}

/** What is remembered about missed questions. Dates are ISO-8601 (`2026-11-12`). */
interface RecallStore {
    fun load(): Map<String, RecallMemory>
    fun save(questionId: String, memory: RecallMemory?)
}

class RecallTracker(private val store: RecallStore) {
    private var current: Map<String, RecallMemory> = store.load()

    fun snapshot(): Map<String, RecallMemory> = current

    fun dueIds(today: String): List<String> =
        current.filter { it.value.isDue(today) }.keys.sorted()

    /** Records every answer in a finished quiz. Unanswered questions count as wrong. */
    fun recordQuiz(state: QuizState, today: String): Map<String, RecallMemory> {
        if (!state.isFinished || state.total == 0) return current
        state.questions.forEachIndexed { i, question ->
            val choice = state.answers.getOrNull(i)
            val correct = choice != null && choice == question.correctIndex
            record(question.id, correct, today)
        }
        return current
    }

    fun record(questionId: String, correct: Boolean, today: String): Map<String, RecallMemory> {
        val next = Recall.record(current[questionId], correct, today)
        store.save(questionId, next)
        current = if (next == null) current - questionId else current + (questionId to next)
        return current
    }
}

object RecallCodec {
    private const val PREFIX = "recall."

    fun key(questionId: String): String = PREFIX + questionId

    fun encode(memory: RecallMemory): String = memory.correctDays.joinToString(",")

    fun decode(text: String?): RecallMemory? {
        if (text == null) return null
        if (text.isEmpty()) return RecallMemory(emptyList())
        val days = text.split(",").map { it.trim() }.filter { it.length == 10 }
        if (days.isEmpty()) return null
        return RecallMemory(days)
    }

    fun decodeAll(entries: Map<String, Any?>): Map<String, RecallMemory> {
        val result = mutableMapOf<String, RecallMemory>()
        for ((key, value) in entries) {
            if (!key.startsWith(PREFIX)) continue
            val memory = decode(value as? String) ?: continue
            result[key.removePrefix(PREFIX)] = memory
        }
        return result
    }
}
