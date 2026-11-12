package ca.nauticlebc.app.progress

import ca.nauticlebc.app.content.testQuestion
import ca.nauticlebc.app.quiz.QuizState
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class RecallTest {
    @Test
    fun aFirstCorrectAnswerIsNotStored() {
        assertNull(Recall.record(null, correct = true, today = "2026-11-12"))
    }

    @Test
    fun aMissIsDueUntilTwoDifferentDays() {
        val missed = Recall.record(null, correct = false, today = "2026-11-12")!!
        assertTrue(missed.isDue("2026-11-12"))

        val once = Recall.record(missed, correct = true, today = "2026-11-12")!!
        assertFalse(once.isDue("2026-11-12"))
        assertTrue(once.isDue("2026-11-13"))

        val twice = Recall.record(once, correct = true, today = "2026-11-13")!!
        assertTrue(twice.mastered)
        assertFalse(twice.isDue("2026-11-14"))
    }

    @Test
    fun aLaterMissClearsTheDays() {
        val once = RecallMemory(listOf("2026-11-12"))
        val again = Recall.record(once, correct = false, today = "2026-11-13")!!
        assertTrue(again.correctDays.isEmpty())
        assertTrue(again.isDue("2026-11-13"))
    }

    @Test
    fun theSameDayDoesNotCountTwice() {
        val once = RecallMemory(listOf("2026-11-12"))
        val again = Recall.record(once, correct = true, today = "2026-11-12")!!
        assertEquals(listOf("2026-11-12"), again.correctDays)
    }

    @Test
    fun aFinishedQuizStoresOnlyTheMisses() {
        val questions = listOf(
            testQuestion("right", correctIndex = 0),
            testQuestion("wrong", correctIndex = 1),
        )
        val state = QuizState.start(questions).select(0).next().select(0).next()
        val store = MemoryStore()
        val tracker = RecallTracker(store)
        tracker.recordQuiz(state, "2026-11-12")
        assertNull(tracker.snapshot()["right"])
        assertTrue(tracker.snapshot().getValue("wrong").isDue("2026-11-12"))
        assertEquals(listOf("wrong"), tracker.dueIds("2026-11-12"))
    }
}

private class MemoryStore : RecallStore {
    val saved = mutableMapOf<String, RecallMemory>()
    override fun load(): Map<String, RecallMemory> = saved.toMap()
    override fun save(questionId: String, memory: RecallMemory?) {
        if (memory == null) saved.remove(questionId) else saved[questionId] = memory
    }
}
