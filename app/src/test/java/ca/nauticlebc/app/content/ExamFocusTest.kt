package ca.nauticlebc.app.content

import kotlin.random.Random
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class ExamFocusTest {
    @Test
    fun cleanFactsTrimsDropsBlanksAndRepeats() {
        assertEquals(
            listOf("one", "two"),
            ExamFocus.cleanFacts(listOf("  one ", "", "   ", "two", "one")),
        )
        assertEquals(emptyList<String>(), ExamFocus.cleanFacts(emptyList()))
    }

    @Test
    fun groupsKeyFactsByLessonInTeachingOrder() {
        val lessons = listOf(
            testLesson("colregs.b", order = 2, title = "B", keyFacts = listOf("b1", "b2")),
            testLesson("colregs.a", order = 1, title = "A", keyFacts = listOf("a1")),
            testLesson("tides.x", order = 1, module = "tides", keyFacts = listOf("t1")),
        )
        val groups = ExamFocus.keyFactsForModule(lessons, "colregs")
        assertEquals(
            listOf(
                LessonKeyFacts("colregs.a", "A", listOf("a1")),
                LessonKeyFacts("colregs.b", "B", listOf("b1", "b2")),
            ),
            groups,
        )
    }

    @Test
    fun lessonsWithoutUsableFactsAreSkipped() {
        val lessons = listOf(
            testLesson("colregs.a", order = 1, keyFacts = emptyList()),
            testLesson("colregs.b", order = 2, keyFacts = listOf("  ", "")),
            testLesson("colregs.c", order = 3, keyFacts = listOf("c1")),
        )
        assertEquals(listOf("colregs.c"), ExamFocus.keyFactsForModule(lessons, "colregs").map { it.lessonId })
        assertEquals(emptyList<LessonKeyFacts>(), ExamFocus.keyFactsForModule(lessons, "tides"))
    }

    private val lessons = listOf(
        testLesson("colregs.a", module = "colregs"),
        testLesson("colregs.b", module = "colregs"),
        testLesson("tides.x", module = "tides"),
    )

    private val questions = listOf(
        testQuestion("c-1", lessonId = "colregs.a"),
        testQuestion("c-2", lessonId = "colregs.a"),
        testQuestion("c-3", lessonId = "colregs.b"),
        testQuestion("c-4", lessonId = "colregs.b"),
        testQuestion("c-5", lessonId = "colregs.b"),
        testQuestion("c-6", lessonId = "colregs.b"),
        testQuestion("t-1", lessonId = "tides.x", module = "tides"),
    )

    @Test
    fun moduleQuizContainsExactlyTheModulesQuestions() {
        val quiz = ExamFocus.moduleQuiz(questions, lessons, "colregs", Random(1))
        assertEquals(setOf("c-1", "c-2", "c-3", "c-4", "c-5", "c-6"), quiz.map { it.id }.toSet())
        assertEquals(6, quiz.size)
        assertEquals(listOf("t-1"), ExamFocus.moduleQuiz(questions, lessons, "tides", Random(1)).map { it.id })
        assertEquals(emptyList<Question>(), ExamFocus.moduleQuiz(questions, lessons, "sixty-ton", Random(1)))
    }

    @Test
    fun moduleQuizIncludesQuestionsAttachedToTheModulesLessons() {
        // Tagged with another module but pointing at a colregs lesson: still counted once, via the lesson.
        val odd = testQuestion("c-odd", lessonId = "colregs.a", module = "tides")
        val quiz = ExamFocus.moduleQuiz(questions + odd + odd, lessons, "colregs", Random(1))
        assertEquals(1, quiz.count { it.id == "c-odd" })
    }

    @Test
    fun sameSeedGivesSameOrderRegardlessOfInputOrder() {
        val a = ExamFocus.moduleQuiz(questions, lessons, "colregs", Random(42)).map { it.id }
        val b = ExamFocus.moduleQuiz(questions, lessons, "colregs", Random(42)).map { it.id }
        val c = ExamFocus.moduleQuiz(questions.reversed(), lessons, "colregs", Random(42)).map { it.id }
        assertEquals(a, b)
        assertEquals(a, c)
    }

    @Test
    fun differentSeedsGiveDifferentOrders() {
        val orders = (1L..20L).map { seed ->
            ExamFocus.moduleQuiz(questions, lessons, "colregs", Random(seed)).map { it.id }
        }.toSet()
        assertTrue("expected several distinct orders, got ${orders.size}", orders.size > 1)
        val sorted = questions.filter { it.module == "colregs" }.map { it.id }.sorted()
        assertTrue("shuffling should not always return id order", orders.any { it != sorted })
    }

    @Test
    fun questionCountMatchesQuizSize() {
        assertEquals(6, ExamFocus.moduleQuestionCount(questions, lessons, "colregs"))
        assertEquals(0, ExamFocus.moduleQuestionCount(questions, lessons, "sixty-ton"))
        assertNotEquals(0, ExamFocus.moduleQuestionCount(questions, lessons, "tides"))
    }
}
