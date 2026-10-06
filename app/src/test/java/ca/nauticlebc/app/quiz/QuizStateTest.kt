package ca.nauticlebc.app.quiz

import ca.nauticlebc.app.content.testQuestion
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertSame
import org.junit.Assert.assertTrue
import org.junit.Test

class QuizStateTest {
    // Correct answers: q1 -> 1, q2 -> 0, q3 -> 2
    private val questions = listOf(
        testQuestion("q1", correctIndex = 1),
        testQuestion("q2", correctIndex = 0),
        testQuestion("q3", correctIndex = 2),
    )

    @Test
    fun startsOnFirstQuestionUnanswered() {
        val s = QuizState.start(questions)
        assertEquals(0, s.index)
        assertEquals("q1", s.current?.id)
        assertNull(s.selected)
        assertFalse(s.isAnswered)
        assertFalse(s.isFinished)
        assertFalse(s.isLastQuestion)
    }

    @Test
    fun selectingLocksTheAnswer() {
        val s = QuizState.start(questions).select(2)
        assertEquals(2, s.selected)
        assertTrue(s.isAnswered)
        // A second pick is ignored.
        assertEquals(2, s.select(1).selected)
        assertEquals(0, s.correctCount)
    }

    @Test
    fun ignoresOutOfRangeChoices() {
        val s = QuizState.start(questions)
        assertSame(s, s.select(-1))
        assertSame(s, s.select(3))
    }

    @Test
    fun cannotAdvanceBeforeAnswering() {
        val s = QuizState.start(questions)
        assertSame(s, s.next())
    }

    @Test
    fun fullRunScoresCorrectly() {
        var s = QuizState.start(questions)
        s = s.select(1).next() // right
        s = s.select(2).next() // wrong
        assertTrue(s.isLastQuestion)
        s = s.select(2) // right
        assertEquals(2, s.correctCount)
        s = s.next()
        assertTrue(s.isFinished)
        assertNull(s.current)
        assertNull(s.selected)
        assertEquals(QuizScore(2, 3), s.score)
        // Nothing moves once finished.
        assertSame(s, s.next())
        assertSame(s, s.select(0))
    }

    @Test
    fun emptyQuizIsImmediatelyFinished() {
        val s = QuizState.start(emptyList())
        assertTrue(s.isFinished)
        assertEquals(QuizScore(0, 0), s.score)
        assertEquals(0, s.score.percent)
    }

    @Test
    fun encodeAndRestoreRoundTrip() {
        val answeredMid = QuizState.start(questions).select(1).next().select(0)
        assertEquals(answeredMid, QuizState.restore(questions, answeredMid.encode()))

        val unansweredMid = QuizState.start(questions).select(1).next()
        assertEquals(unansweredMid, QuizState.restore(questions, unansweredMid.encode()))

        val finished = QuizState.start(questions).select(1).next().select(0).next().select(2).next()
        assertTrue(finished.isFinished)
        assertEquals(finished, QuizState.restore(questions, finished.encode()))
    }

    @Test
    fun restoreRejectsInconsistentData() {
        val fresh = QuizState.start(questions)
        assertEquals(fresh, QuizState.restore(questions, emptyList()))
        assertEquals(fresh, QuizState.restore(questions, listOf(-1)))
        assertEquals(fresh, QuizState.restore(questions, listOf(9)))
        // Index 2 but only one answer recorded.
        assertEquals(fresh, QuizState.restore(questions, listOf(2, 1)))
        // Answer outside the option range.
        assertEquals(fresh, QuizState.restore(questions, listOf(0, 7)))
        // More answers than questions.
        assertEquals(fresh, QuizState.restore(questions, listOf(3, 0, 0, 0, 0)))
    }

    @Test
    fun percentRoundsHalfUp() {
        assertEquals(33, QuizScore(1, 3).percent)
        assertEquals(67, QuizScore(2, 3).percent)
        assertEquals(100, QuizScore(4, 4).percent)
        assertEquals(0, QuizScore(0, 4).percent)
    }

    @Test
    fun betterThanComparesFractions() {
        assertTrue(QuizScore(3, 4).isBetterThan(QuizScore(2, 4)))
        assertTrue(QuizScore(2, 3).isBetterThan(QuizScore(3, 5)))
        assertFalse(QuizScore(3, 4).isBetterThan(QuizScore(3, 4)))
        assertFalse(QuizScore(6, 8).isBetterThan(QuizScore(3, 4)))
        assertFalse(QuizScore(0, 0).isBetterThan(QuizScore(1, 4)))
        assertTrue(QuizScore(0, 4).isBetterThan(QuizScore(0, 0)))
    }
}
