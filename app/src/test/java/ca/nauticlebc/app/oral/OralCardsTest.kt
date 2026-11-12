package ca.nauticlebc.app.oral

import ca.nauticlebc.app.content.testQuestion
import org.junit.Assert.assertEquals
import org.junit.Test

class OralCardsTest {
    private val questions = listOf(
        testQuestion("oral", module = "fm4-oral", tickets = listOf("fishing-master-4")),
        testQuestion("engine", module = "machinery", tickets = listOf("svmo")),
        testQuestion("ticket", module = "sixty-ton", tickets = listOf("master-limited-60gt")),
        testQuestion("lights", module = "colregs", tickets = listOf("master-limited-60gt", "fishing-master-4")),
    )

    @Test
    fun eachOralTicketUsesOnlyItsOwnModule() {
        assertEquals(listOf("ticket"), OralCards.forTicket("master-limited-60gt", questions).map { it.id })
        assertEquals(listOf("oral"), OralCards.forTicket("fishing-master-4", questions).map { it.id })
        assertEquals(listOf("engine"), OralCards.forTicket("svmo", questions).map { it.id })
        assertEquals(emptyList<String>(), OralCards.forTicket("svop", questions).map { it.id })
    }
}
