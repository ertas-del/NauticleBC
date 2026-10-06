package ca.nauticlebc.app.content

fun testLesson(
    id: String,
    order: Int = 1,
    module: String = "colregs",
    title: String = "Title $id",
    body: String = "body",
    reviewStatus: String = "needs_expert_review",
    reviewedBy: String? = null,
) = Lesson(
    id = id,
    title = title,
    module = module,
    tickets = listOf("bridge-watch-rating"),
    order = order,
    summary = "summary",
    objectives = listOf("objective"),
    estimatedMinutes = 5,
    sourceIds = listOf("s1"),
    reviewStatus = reviewStatus,
    reviewedBy = reviewedBy,
    body = body,
)

fun testQuestion(
    id: String,
    lessonId: String = "l1",
    correctIndex: Int = 1,
    optionCount: Int = 3,
) = Question(
    id = id,
    module = "colregs",
    lessonId = lessonId,
    tickets = listOf("bridge-watch-rating"),
    difficulty = "easy",
    stem = "stem $id",
    options = List(optionCount) { "option $it" },
    correctIndex = correctIndex,
    explanation = "because",
    sourceIds = listOf("s1"),
    reviewStatus = "needs_expert_review",
)

fun testSource(id: String, title: String = "Source $id", publisher: String = "Pub", edition: String? = null) = Source(
    id = id,
    title = title,
    publisher = publisher,
    edition = edition,
    kind = "regulation",
    reproduction = "unclear",
    readDirectly = false,
)
