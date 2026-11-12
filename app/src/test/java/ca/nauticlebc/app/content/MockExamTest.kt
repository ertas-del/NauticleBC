package ca.nauticlebc.app.content

import kotlin.random.Random
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class MockExamTest {
    private fun exam(count: Int, vararg items: BlueprintItem) = MockExam(
        id = "mock.t",
        title = "T",
        ticket = "bridge-watch-rating",
        questionCount = count,
        timeLimitMinutes = 60,
        passMarkPercent = 70,
        blueprint = items.toList(),
        sourceIds = listOf("s1"),
        reviewStatus = "needs_expert_review",
    )

    private val questions =
        (1..6).map { testQuestion("a$it", module = "colregs") } + (1..6).map { testQuestion("b$it", module = "tides") }

    @Test
    fun drawsFromEachModuleByBlueprint() {
        val e = exam(5, BlueprintItem("colregs", 3), BlueprintItem("tides", 2))
        val result = ExamFocus.mockExam(e, questions, emptyList(), Random(1))
        assertEquals(5, result.size)
        assertEquals(3, result.count { it.module == "colregs" })
        assertEquals(2, result.count { it.module == "tides" })
        assertEquals(result.map { it.id }.toSet().size, result.size)
    }

    @Test
    fun sameSeedGivesSameExam() {
        val e = exam(6, BlueprintItem("colregs", 3), BlueprintItem("tides", 3))
        assertEquals(
            ExamFocus.mockExam(e, questions, emptyList(), Random(7)).map { it.id },
            ExamFocus.mockExam(e, questions, emptyList(), Random(7)).map { it.id },
        )
    }

    @Test
    fun usesFewerQuestionsWhenBankIsShort() {
        val e = exam(20, BlueprintItem("colregs", 10))
        assertTrue(ExamFocus.mockExam(e, questions, emptyList(), Random(2)).size == 6)
    }

    @Test
    fun fixedDrawUsesExactlyTheListedQuestions() {
        val e = exam(3, BlueprintItem("colregs", 2), BlueprintItem("tides", 1))
            .copy(questionIds = listOf("a2", "a5", "b3"))
        for (seed in 1..5) {
            val ids = ExamFocus.mockExam(e, questions, emptyList(), Random(seed)).map { it.id }
            assertEquals(setOf("a2", "a5", "b3"), ids.toSet())
            assertEquals(3, ids.size)
        }
    }

    @Test
    fun fixedDrawSkipsUnknownIds() {
        val e = exam(2, BlueprintItem("colregs", 2)).copy(questionIds = listOf("a1", "zz"))
        assertEquals(listOf("a1"), ExamFocus.mockExam(e, questions, emptyList(), Random(3)).map { it.id })
    }
}
