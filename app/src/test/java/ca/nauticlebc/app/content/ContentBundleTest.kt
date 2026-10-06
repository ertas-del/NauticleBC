package ca.nauticlebc.app.content

import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test

class ContentBundleTest {
    private val sample = """
        {
          "format_version": 1,
          "sources": [
            {"id": "s1", "title": "T", "publisher": "P", "kind": "regulation",
             "reproduction": "unclear", "read_directly": false}
          ],
          "lessons": [
            {"id": "colregs.rule-05.lookout", "title": "Rule 5", "module": "colregs",
             "tickets": ["bridge-watch-rating"], "order": 5, "summary": "s",
             "objectives": ["o"], "estimated_minutes": 8, "source_ids": ["s1"],
             "review_status": "needs_expert_review", "body": "text", "extra_future_field": 1}
          ],
          "questions": [
            {"id": "q1", "module": "colregs", "lesson_id": "colregs.rule-05.lookout",
             "tickets": ["bridge-watch-rating"], "difficulty": "easy", "stem": "?",
             "options": ["a", "b", "c"], "correct_index": 1, "explanation": "e",
             "source_ids": ["s1"], "review_status": "needs_expert_review"}
          ],
          "mock_exams": []
        }
    """.trimIndent()

    @Test
    fun parsesBundleAndIgnoresUnknownFields() {
        val bundle = ContentBundle.parse(sample)
        assertEquals(1, bundle.lessons.size)
        assertEquals(8, bundle.lessons.first().estimatedMinutes)
        assertEquals(1, bundle.questions.first().correctIndex)
        assertEquals(false, bundle.sources.first().readDirectly)
    }

    @Test
    fun rejectsUnsupportedFormatVersion() {
        assertThrows(IllegalArgumentException::class.java) {
            ContentBundle.parse("""{"format_version": 99}""")
        }
    }
}
