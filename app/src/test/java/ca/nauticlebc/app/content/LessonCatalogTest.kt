package ca.nauticlebc.app.content

import ca.nauticlebc.app.markdown.MdBlock
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class LessonCatalogTest {
    @Test
    fun sortsLessonsByOrderThenIdAndFiltersByModule() {
        val lessons = listOf(
            testLesson("colregs.b", order = 2),
            testLesson("tides.x", order = 1, module = "tides"),
            testLesson("colregs.c", order = 1),
            testLesson("colregs.a", order = 1),
            testLesson("colregs.z", order = 10),
        )
        val sorted = LessonCatalog.lessonsForModule(lessons, "colregs").map { it.id }
        assertEquals(listOf("colregs.a", "colregs.c", "colregs.b", "colregs.z"), sorted)
        assertEquals(emptyList<Lesson>(), LessonCatalog.lessonsForModule(lessons, "sixty-ton"))
    }

    @Test
    fun findsLessonById() {
        val lessons = listOf(testLesson("a"), testLesson("b"))
        assertEquals("b", LessonCatalog.findLesson(lessons, "b")?.id)
        assertEquals(null, LessonCatalog.findLesson(lessons, "nope"))
    }

    @Test
    fun questionsForLessonAreFilteredAndOrderedById() {
        val questions = listOf(
            testQuestion("q2", lessonId = "l1"),
            testQuestion("q9", lessonId = "l2"),
            testQuestion("q1", lessonId = "l1"),
        )
        assertEquals(listOf("q1", "q2"), LessonCatalog.questionsForLesson(questions, "l1").map { it.id })
    }

    @Test
    fun resolvesSourcesAndKeepsUnknownIds() {
        val sources = listOf(testSource("s1", title = "COLREGs", publisher = "Transport Canada", edition = "2024"))
        val refs = LessonCatalog.resolveSources(sources, listOf("s1", "missing", "s1"))
        assertEquals(2, refs.size)
        assertEquals("COLREGs", refs[0].title)
        assertEquals("Transport Canada", refs[0].publisher)
        assertEquals("2024", refs[0].edition)
        assertTrue(refs[0].known)
        assertEquals("missing", refs[1].title)
        assertFalse(refs[1].known)
    }

    @Test
    fun dropsLeadingHeadingThatRepeatsTheTitle() {
        val lesson = testLesson("l", title = "Rule 5: Lookout", body = "# Rule 5: Lookout\n\nText here.")
        val blocks = LessonCatalog.bodyBlocks(lesson)
        assertEquals(1, blocks.size)
        assertTrue(blocks[0] is MdBlock.Paragraph)
    }

    @Test
    fun keepsLeadingHeadingThatDiffersFromTheTitle() {
        val lesson = testLesson("l", title = "Rule 5", body = "# Something else\n\nText.")
        assertEquals(2, LessonCatalog.bodyBlocks(lesson).size)
    }

    @Test
    fun reviewLabelIsHonest() {
        assertEquals(ReviewLabel.AwaitingExpertReview, ReviewStatus.labelFor("needs_expert_review"))
        assertEquals(ReviewLabel.Draft, ReviewStatus.labelFor("draft"))
        assertEquals(ReviewLabel.AwaitingExpertReview, ReviewStatus.labelFor("something_new"))
        assertEquals(ReviewLabel.ExpertReviewed, ReviewStatus.labelFor("expert_reviewed"))
        // Claimed as reviewed but nobody is named: do not show it as reviewed.
        assertEquals(
            ReviewLabel.AwaitingExpertReview,
            ReviewStatus.labelFor(testLesson("l", reviewStatus = "expert_reviewed", reviewedBy = null)),
        )
        assertEquals(
            ReviewLabel.ExpertReviewed,
            ReviewStatus.labelFor(testLesson("l", reviewStatus = "expert_reviewed", reviewedBy = "A. Master")),
        )
    }

    @Test
    fun leastReviewedPicksTheWeakestLabel() {
        val labels = listOf(ReviewLabel.ExpertReviewed, ReviewLabel.AwaitingExpertReview, ReviewLabel.ExpertReviewed)
        assertEquals(ReviewLabel.AwaitingExpertReview, ReviewStatus.leastReviewed(labels))
        assertEquals(ReviewLabel.ExpertReviewed, ReviewStatus.leastReviewed(listOf(ReviewLabel.ExpertReviewed)))
    }
}
