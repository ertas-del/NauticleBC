package ca.nauticlebc.app.content

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

/** Mirrors the JSON written by tools/build_bundle.py. Keep in step with schemas/. */
@Serializable
data class ContentBundle(
    @SerialName("format_version") val formatVersion: Int,
    val sources: List<Source> = emptyList(),
    val lessons: List<Lesson> = emptyList(),
    val questions: List<Question> = emptyList(),
    @SerialName("mock_exams") val mockExams: List<MockExam> = emptyList(),
) {
    /** Only the lessons and questions that count towards [ticket]; an empty id keeps everything. */
    fun forTicket(ticket: String): ContentBundle =
        if (ticket.isEmpty()) this else copy(
            lessons = lessons.filter { ticket in it.tickets },
            questions = questions.filter { ticket in it.tickets },
            mockExams = mockExams.filter { it.ticket == ticket },
        )

    companion object {
        const val SUPPORTED_FORMAT_VERSION = 1

        private val json = Json { ignoreUnknownKeys = true }

        fun parse(text: String): ContentBundle {
            val bundle = json.decodeFromString<ContentBundle>(text)
            require(bundle.formatVersion == SUPPORTED_FORMAT_VERSION) {
                "Unsupported content bundle format ${bundle.formatVersion}"
            }
            return bundle
        }
    }
}

@Serializable
data class Source(
    val id: String,
    val title: String,
    val publisher: String,
    val url: String? = null,
    val edition: String? = null,
    val kind: String,
    val reproduction: String,
    @SerialName("read_directly") val readDirectly: Boolean,
    val notes: String? = null,
)

@Serializable
data class Figure(
    val id: String,
    val caption: String,
    val alt: String,
)

@Serializable
data class Lesson(
    val id: String,
    val title: String,
    val module: String,
    val tickets: List<String>,
    val order: Int,
    val summary: String,
    val objectives: List<String>,
    val prerequisites: List<String> = emptyList(),
    @SerialName("estimated_minutes") val estimatedMinutes: Int,
    @SerialName("key_facts") val keyFacts: List<String> = emptyList(),
    @SerialName("source_ids") val sourceIds: List<String>,
    @SerialName("review_status") val reviewStatus: String,
    @SerialName("reviewed_by") val reviewedBy: String? = null,
    @SerialName("reviewed_on") val reviewedOn: String? = null,
    val figures: List<Figure> = emptyList(),
    val body: String,
)

@Serializable
data class Question(
    val id: String,
    val module: String,
    @SerialName("lesson_id") val lessonId: String,
    val tickets: List<String>,
    val difficulty: String,
    val stem: String,
    val options: List<String>,
    @SerialName("correct_index") val correctIndex: Int,
    val explanation: String,
    @SerialName("source_ids") val sourceIds: List<String>,
    @SerialName("review_status") val reviewStatus: String,
)

@Serializable
data class MockExam(
    val id: String,
    val title: String,
    val ticket: String,
    @SerialName("question_count") val questionCount: Int,
    @SerialName("time_limit_minutes") val timeLimitMinutes: Int,
    @SerialName("pass_mark_percent") val passMarkPercent: Int,
    val blueprint: List<BlueprintItem>,
    val notes: String? = null,
    @SerialName("source_ids") val sourceIds: List<String>,
    @SerialName("review_status") val reviewStatus: String,
)

@Serializable
data class BlueprintItem(
    val module: String,
    val count: Int,
)
