package ca.nauticlebc.app.content

import kotlin.random.Random

/** One lesson's key facts, as listed on a module's Exam focus screen. */
data class LessonKeyFacts(val lessonId: String, val lessonTitle: String, val facts: List<String>)

/** Pure logic behind the Exam focus screen and the module quiz. */
object ExamFocus {
    /** Cleans a lesson's key facts: trimmed, blank entries dropped, exact repeats removed (first one wins). */
    fun cleanFacts(facts: List<String>): List<String> =
        facts.map { it.trim() }.filter { it.isNotEmpty() }.distinct()

    /**
     * Key facts of every lesson in [module], grouped by lesson in teaching order. Lessons with no usable key
     * facts are left out.
     */
    fun keyFactsForModule(lessons: List<Lesson>, module: String): List<LessonKeyFacts> =
        LessonCatalog.lessonsForModule(lessons, module).mapNotNull { lesson ->
            val facts = cleanFacts(lesson.keyFacts)
            if (facts.isEmpty()) null else LessonKeyFacts(lesson.id, lesson.title, facts)
        }

    /**
     * All questions that belong to [module], either tagged with the module or attached to one of its lessons,
     * each once, in a random order drawn from [random]. The same seed always gives the same order, whatever
     * order [questions] arrives in, which lets a saved quiz be rebuilt after the process is killed.
     */
    fun moduleQuiz(
        questions: List<Question>,
        lessons: List<Lesson>,
        module: String,
        random: Random,
    ): List<Question> {
        val lessonIds = lessons.filter { it.module == module }.map { it.id }.toSet()
        return questions
            .filter { it.module == module || it.lessonId in lessonIds }
            .distinctBy { it.id }
            .sortedBy { it.id }
            .shuffled(random)
    }

    /** Number of questions [moduleQuiz] would contain; cheap way to decide whether to offer the quiz. */
    fun moduleQuestionCount(questions: List<Question>, lessons: List<Lesson>, module: String): Int =
        moduleQuiz(questions, lessons, module, Random(0)).size
}
