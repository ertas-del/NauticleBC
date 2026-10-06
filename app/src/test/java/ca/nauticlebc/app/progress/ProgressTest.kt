package ca.nauticlebc.app.progress

import ca.nauticlebc.app.quiz.QuizScore
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

private class FakeStore(initial: Map<String, LessonProgress> = emptyMap()) : ProgressStore {
    val saved = mutableMapOf<String, LessonProgress>().apply { putAll(initial) }
    var saveCalls = 0

    override fun load(): Map<String, LessonProgress> = saved.toMap()

    override fun save(lessonId: String, progress: LessonProgress) {
        saveCalls++
        saved[lessonId] = progress
    }
}

class ProgressTest {
    @Test
    fun startsEmpty() {
        val tracker = ProgressTracker(FakeStore())
        assertTrue(tracker.snapshot().isEmpty())
    }

    @Test
    fun markCompletedPersists() {
        val store = FakeStore()
        val tracker = ProgressTracker(store)
        val snap = tracker.markCompleted("l1")
        assertTrue(snap.getValue("l1").completed)
        assertNull(snap.getValue("l1").best)
        assertEquals(snap, tracker.snapshot())
        assertTrue(store.saved.getValue("l1").completed)
    }

    @Test
    fun quizResultCompletesLessonAndKeepsBestScore() {
        val store = FakeStore()
        val tracker = ProgressTracker(store)
        tracker.recordQuiz("l1", QuizScore(2, 4))
        assertEquals(QuizScore(2, 4), tracker.snapshot().getValue("l1").best)
        assertTrue(tracker.snapshot().getValue("l1").completed)

        tracker.recordQuiz("l1", QuizScore(4, 4))
        assertEquals(QuizScore(4, 4), tracker.snapshot().getValue("l1").best)

        // A worse run does not replace the best score.
        tracker.recordQuiz("l1", QuizScore(1, 4))
        assertEquals(QuizScore(4, 4), tracker.snapshot().getValue("l1").best)
        assertEquals(QuizScore(4, 4), store.saved.getValue("l1").best)
    }

    @Test
    fun ignoresEmptyQuizResults() {
        val store = FakeStore()
        val tracker = ProgressTracker(store)
        tracker.recordQuiz("l1", QuizScore(0, 0))
        assertTrue(tracker.snapshot().isEmpty())
        assertEquals(0, store.saveCalls)
    }

    @Test
    fun progressSurvivesANewTracker() {
        val store = FakeStore()
        ProgressTracker(store).recordQuiz("l1", QuizScore(3, 4))
        val reopened = ProgressTracker(store)
        assertEquals(QuizScore(3, 4), reopened.snapshot().getValue("l1").best)
    }

    @Test
    fun markingCompletedKeepsExistingScore() {
        val tracker = ProgressTracker(FakeStore())
        tracker.recordQuiz("l1", QuizScore(3, 4))
        tracker.markCompleted("l1")
        assertEquals(QuizScore(3, 4), tracker.snapshot().getValue("l1").best)
    }

    @Test
    fun codecEncodesAndDecodesBestScore() {
        assertEquals("3/4", ProgressCodec.encodeBest(QuizScore(3, 4)))
        assertEquals(QuizScore(3, 4), ProgressCodec.decodeBest("3/4"))
        assertNull(ProgressCodec.decodeBest(null))
        assertNull(ProgressCodec.decodeBest("x"))
        assertNull(ProgressCodec.decodeBest("3/0"))
        assertNull(ProgressCodec.decodeBest("5/4"))
        assertNull(ProgressCodec.decodeBest("-1/4"))
        assertNull(ProgressCodec.decodeBest("1/2/3"))
    }

    @Test
    fun codecDecodesPreferenceEntries() {
        val entries: Map<String, Any?> = mapOf(
            "done.a" to true,
            "best.a" to "2/3",
            "done.b" to false,
            "best.c" to "4/4",
            "best.d" to "garbage",
            "done.e" to true,
            "unrelated" to "x",
        )
        val result = ProgressCodec.decodeAll(entries)
        assertEquals(LessonProgress(true, QuizScore(2, 3)), result["a"])
        assertFalse(result.containsKey("b"))
        // A stored score implies the lesson was completed.
        assertEquals(LessonProgress(true, QuizScore(4, 4)), result["c"])
        assertFalse(result.containsKey("d"))
        assertEquals(LessonProgress(true, null), result["e"])
        assertEquals(setOf("a", "c", "e"), result.keys)
    }

    @Test
    fun keysDoNotCollide() {
        assertEquals("done.colregs.rule-05.lookout", ProgressCodec.doneKey("colregs.rule-05.lookout"))
        assertEquals("best.colregs.rule-05.lookout", ProgressCodec.bestKey("colregs.rule-05.lookout"))
    }
}
