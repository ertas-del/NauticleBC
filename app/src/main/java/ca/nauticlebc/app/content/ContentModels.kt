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
    val charts: List<Chart> = emptyList(),
    @SerialName("chart_exercises") val chartExercises: List<ChartExercise> = emptyList(),
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
    /** Optional fixed draw: when not empty the exam always uses exactly these questions. */
    @SerialName("question_ids") val questionIds: List<String> = emptyList(),
    val notes: String? = null,
    @SerialName("source_ids") val sourceIds: List<String>,
    @SerialName("review_status") val reviewStatus: String,
)

@Serializable
data class BlueprintItem(
    val module: String,
    val count: Int,
)

/** A fictional training chart, drawn on screen from this data. Mirrors schemas/chart.schema.json. */
@Serializable
data class Chart(
    val id: String,
    val title: String,
    @SerialName("area_note") val areaNote: String,
    val module: String,
    val extent: ChartExtent,
    @SerialName("scale_ratio") val scaleRatio: Int,
    val variation: ChartVariation,
    @SerialName("compass_roses") val compassRoses: List<ChartRose> = emptyList(),
    val land: List<ChartLand> = emptyList(),
    val soundings: List<ChartSounding> = emptyList(),
    val hazards: List<ChartHazard> = emptyList(),
    val aids: List<ChartAid> = emptyList(),
    val legend: List<ChartLegendItem> = emptyList(),
)

@Serializable
data class ChartExtent(val south: Double, val west: Double, val north: Double, val east: Double)

@Serializable
data class ChartVariation(
    val degrees: Int,
    val minutes: Int,
    val direction: String,
    val year: Int,
    @SerialName("annual_change_minutes") val annualChangeMinutes: Double,
)

@Serializable
data class ChartPos(val lat: Double, val lon: Double)

@Serializable
data class ChartRose(val id: String, val position: ChartPos)

@Serializable
data class ChartLand(val name: String, val kind: String, val ring: List<ChartPos>)

@Serializable
data class ChartSounding(val position: ChartPos, @SerialName("depth_m") val depthM: Double)

@Serializable
data class ChartHazard(
    val id: String,
    val kind: String,
    val name: String,
    val position: ChartPos,
    @SerialName("drying_height_m") val dryingHeightM: Double? = null,
    @SerialName("depth_m") val depthM: Double? = null,
)

@Serializable
data class ChartAid(
    val id: String,
    val name: String,
    val kind: String,
    val position: ChartPos,
    val colour: String? = null,
    val characteristic: String? = null,
)

@Serializable
data class ChartLegendItem(val symbol: String, val meaning: String)

@Serializable
data class ChartAnswer(
    val label: String,
    val kind: String,
    /** A number for bearings, distances, heights and times; a {lat, lon} object for positions. */
    val value: kotlinx.serialization.json.JsonElement? = null,
    val tolerance: Double? = null,
    val unit: String? = null,
) {
    val number: Double?
        get() = (value as? kotlinx.serialization.json.JsonPrimitive)?.content?.toDoubleOrNull()

    val position: ChartPos?
        get() = (value as? kotlinx.serialization.json.JsonObject)?.let { o ->
            val lat = (o["lat"] as? kotlinx.serialization.json.JsonPrimitive)?.content?.toDoubleOrNull()
            val lon = (o["lon"] as? kotlinx.serialization.json.JsonPrimitive)?.content?.toDoubleOrNull()
            if (lat != null && lon != null) ChartPos(lat, lon) else null
        }
}

@Serializable
data class ChartExercise(
    val id: String,
    @SerialName("chart_id") val chartId: String,
    val tickets: List<String> = emptyList(),
    val kind: String,
    val difficulty: String,
    val prompt: String,
    val answers: List<ChartAnswer> = emptyList(),
    val method: List<String> = emptyList(),
)
