package ca.nauticlebc.app.ui

import org.junit.Assert.assertEquals
import org.junit.Test

class ResolveTicketTest {
    @Test
    fun unknownOrMissingTicketFallsBackToBridgeWatch() {
        assertEquals("bridge-watch-rating", resolveTicket(null))
        assertEquals("bridge-watch-rating", resolveTicket("not-a-ticket"))
    }

    @Test
    fun aKnownTicketIsKept() {
        assertEquals("svmo", resolveTicket("svmo"))
        assertEquals("fishing-master-4", resolveTicket("fishing-master-4"))
    }
}
