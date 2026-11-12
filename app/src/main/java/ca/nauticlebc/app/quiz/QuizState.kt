package ca.nauticlebc.app.quiz

import ca.nauticlebc.app.content.Question

/** Result of a finished quiz: how many were right out of how many. */
data class QuizScore(val correct: Int, val total: Int) {
    /** Whole-number percentage, rounded half up. Zero when there were no questions. */
    val percent: Int
        get() = if (total <= 0) 0 else (correct * 100 + total / 2) / total

    /** True only when this score is strictly better (by fraction correct) than [other]. */
    fun isBetterThan(other: QuizScore): Boolean {
        if (total <= 0) return false
        if (other.total <= 0) return true
        return correct.toLong() * other.total > other.correct.toLong() * total
    }
}

/**
 * Immutable state of one run through a lesson's quiz: questions are shown one at a time, the learner
 * picks an option (which locks the answer and reveals the explanation), then moves on.
 */
data class QuizState(
    val questions: List<Question>,
    /** Index of the question being shown. Equals `questions.size` once the quiz is finished. */
    val index: Int = 0,
    /** Chosen option index per question, in order. Has `index + 1` entries while the current one is answered. */
    val answers: List<Int> = emptyList(),
) {
    val total: Int get() = questions.size

    val isFinished: Boolean get() = index >= questions.size

    val current: Question? get() = questions.getOrNull(index)

    /** The option picked for the current question, or null if it has not been answered yet. */
    val selected: Int? get() = if (isFinished) null else answers.getOrNull(index)

    val isAnswered: Boolean get() = selected != null

    val isLastQuestion: Boolean get() = index == questions.size - 1

    val correctCount: Int
        get() = answers.indices.count { i ->
            val q = questions.getOrNull(i)
            q != null && answers[i] == q.correctIndex
        }

    val score: QuizScore get() = QuizScore(correctCount, total)

    /** Locks in [choice] for the current question. Ignored if finished, already answered, or out of range. */
    fun select(choice: Int): QuizState {
        val q = current ?: return this
        if (isAnswered) return this
        if (choice !in q.options.indices) return this
        return copy(answers = answers + choice)
    }

    /** Moves to the next question (or to the finished state). Ignored until the current question is answered. */
    fun next(): QuizState = if (isAnswered) copy(index = index + 1) else this

    /**
     * Ends the quiz now. Questions that were not answered, including the one on screen, count as wrong.
     * Used when a closed-book mock runs out of time.
     */
    fun forceFinish(): QuizState {
        if (isFinished) return this
        val padded = answers.toMutableList()
        while (padded.size < questions.size) padded += UNANSWERED
        return copy(index = questions.size, answers = padded)
    }

    /** Questions that were wrong or left blank. Only meaningful once the quiz is finished. */
    fun missed(): List<Question> =
        questions.filterIndexed { i, question -> answers.getOrNull(i) != question.correctIndex }

    /** Encodes progress as `[index, answer0, answer1, ...]` so it can survive rotation and process death. */
    fun encode(): List<Int> = listOf(index) + answers

    companion object {
        /** Stored in [answers] when a question was never attempted. */
        const val UNANSWERED = -1

        fun start(questions: List<Question>): QuizState = QuizState(questions)

        /** Rebuilds a state from [encode] output. Anything inconsistent with [questions] yields a fresh quiz. */
        fun restore(questions: List<Question>, encoded: List<Int>): QuizState {
            if (encoded.isEmpty()) return start(questions)
            val index = encoded[0]
            val answers = encoded.drop(1)
            if (index < 0 || index > questions.size) return start(questions)
            // Either the current question is unanswered (answers == index) or answered (answers == index + 1);
            // a finished quiz has exactly one answer per question.
            val validCount = answers.size == index || (index < questions.size && answers.size == index + 1)
            if (!validCount) return start(questions)
            for (i in answers.indices) {
                if (answers[i] != UNANSWERED && answers[i] !in questions[i].options.indices) return start(questions)
            }
            return QuizState(questions, index, answers)
        }
    }
}
