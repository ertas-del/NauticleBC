package ca.nauticlebc.app.oral

import ca.nauticlebc.app.content.Question

/**
 * Oral prompts taken from questions already in the bundle. The stem is the prompt.
 * The correct option and the explanation are the marking points, shown only after the learner has answered out loud.
 */
object OralCards {
    val tickets = setOf("master-limited-60gt", "fishing-master-4", "svmo")

    fun forTicket(ticket: String, questions: List<Question>): List<Question> {
        val module = when (ticket) {
            "master-limited-60gt" -> "sixty-ton"
            "fishing-master-4" -> "fm4-oral"
            "svmo" -> "machinery"
            else -> return emptyList()
        }
        return questions.filter { it.module == module && ticket in it.tickets }.sortedBy { it.id }
    }
}
