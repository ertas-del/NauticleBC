package ca.nauticlebc.app.content

import ca.nauticlebc.app.markdown.MarkdownParser
import ca.nauticlebc.app.markdown.MdBlock

/** How far content has been checked by a qualified person. Order runs from least to most reviewed. */
enum class ReviewLabel { Draft, AwaitingExpertReview, ExpertReviewed }

/** A lesson's source as shown to the learner. [known] is false when the id is missing from the bundle's sources. */
data class SourceRef(
    val id: String,
    val title: String,
    val publisher: String?,
    val edition: String?,
    val known: Boolean,
)

/** Pure lookups over the loaded bundle. */
object LessonCatalog {
    /** Lessons of one module in teaching order (`order`, then id so the result is stable). */
    fun lessonsForModule(lessons: List<Lesson>, module: String): List<Lesson> =
        lessons.filter { it.module == module }.sortedWith(compareBy<Lesson>({ it.order }, { it.id }))

    fun findLesson(lessons: List<Lesson>, lessonId: String): Lesson? =
        lessons.firstOrNull { it.id == lessonId }

    /** Questions for one lesson, in id order. */
    fun questionsForLesson(questions: List<Question>, lessonId: String): List<Question> =
        questions.filter { it.lessonId == lessonId }.sortedBy { it.id }

    /** Resolves source ids to display info. Unknown ids are kept (shown by id) rather than hidden. */
    fun resolveSources(sources: List<Source>, ids: List<String>): List<SourceRef> {
        val byId = sources.associateBy { it.id }
        return ids.distinct().map { id ->
            val s = byId[id]
            if (s == null) {
                SourceRef(id = id, title = id, publisher = null, edition = null, known = false)
            } else {
                SourceRef(
                    id = id,
                    title = s.title,
                    publisher = s.publisher.ifBlank { null },
                    edition = s.edition?.ifBlank { null },
                    known = true,
                )
            }
        }
    }

    /** Parses the lesson body. A leading level-1 heading that repeats the lesson title is dropped. */
    fun bodyBlocks(lesson: Lesson): List<MdBlock> {
        val blocks = MarkdownParser.parse(lesson.body)
        val first = blocks.firstOrNull()
        if (first is MdBlock.Heading && first.level == 1) {
            val headingText = first.spans.joinToString("") { it.text }.trim()
            if (headingText.equals(lesson.title.trim(), ignoreCase = true)) return blocks.drop(1)
        }
        return blocks
    }
}

object ReviewStatus {
    /**
     * Maps a bundle `review_status` to a label. Only `expert_reviewed` with a named reviewer counts as reviewed;
     * anything else, including unknown values, is shown as not yet reviewed.
     */
    fun labelFor(status: String, hasReviewer: Boolean = true): ReviewLabel = when (status) {
        "expert_reviewed" -> if (hasReviewer) ReviewLabel.ExpertReviewed else ReviewLabel.AwaitingExpertReview
        "draft" -> ReviewLabel.Draft
        else -> ReviewLabel.AwaitingExpertReview
    }

    fun labelFor(lesson: Lesson): ReviewLabel =
        labelFor(lesson.reviewStatus, hasReviewer = !lesson.reviewedBy.isNullOrBlank())

    /** The least-reviewed label in [labels]; used to summarise a lesson's questions. */
    fun leastReviewed(labels: List<ReviewLabel>): ReviewLabel =
        labels.minByOrNull { it.ordinal } ?: ReviewLabel.AwaitingExpertReview
}
