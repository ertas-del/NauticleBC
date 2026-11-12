package ca.nauticlebc.app.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import ca.nauticlebc.app.R
import ca.nauticlebc.app.content.ContentBundle

private data class TicketCard(
    val id: String,
    @StringRes val titleRes: Int,
    @StringRes val descRes: Int,
)

private val tickets = listOf(
    TicketCard("bridge-watch-rating", R.string.ticket_bridge_watch, R.string.ticket_bridge_watch_desc),
    TicketCard("master-limited-60gt", R.string.ticket_master_limited, R.string.ticket_master_limited_desc),
    TicketCard("svmo", R.string.ticket_svmo, R.string.ticket_svmo_desc),
    TicketCard("fishing-master-4", R.string.ticket_fishing_master, R.string.ticket_fishing_master_desc),
    TicketCard("svop", R.string.ticket_svop, R.string.ticket_svop_desc),
)

/** Title string for a ticket id, or null when unknown. */
fun ticketTitleRes(ticketId: String): Int? = tickets.firstOrNull { it.id == ticketId }?.titleRes

/** Title string for a module id, falling back to the raw id for modules without a title. */
fun moduleTitleRes(moduleId: String): Int? = when (moduleId) {
    "colregs" -> R.string.module_colregs
    "tides" -> R.string.module_tides
    "bridge-watch" -> R.string.module_bridge_watch
    "sixty-ton" -> R.string.module_sixty_ton
    "seamanship" -> R.string.module_seamanship
    "chartwork" -> R.string.module_chartwork
    "stability" -> R.string.module_stability
    "radio" -> R.string.module_radio
    "meteorology" -> R.string.module_meteorology
    "canadian-regulations" -> R.string.module_canadian_regulations
    "radar" -> R.string.module_radar
    "electronic-navigation" -> R.string.module_electronic_navigation
    "machinery" -> R.string.module_machinery
    "fishing" -> R.string.module_fishing
    "svop" -> R.string.module_svop
    "fm4-overview" -> R.string.module_fm4_overview
    "fm4-chartwork" -> R.string.module_fm4_chartwork
    "fm4-navigation-safety" -> R.string.module_fm4_navigation_safety
    "fm4-stability-construction" -> R.string.module_fm4_stability_construction
    "fm4-oral" -> R.string.module_fm4_oral
    else -> null
}

/** Keeps a saved ticket only when it is one this app knows. */
fun resolveTicket(saved: String?): String =
    if (tickets.any { it.id == saved }) saved!! else tickets.first().id

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    dueCount: Int,
    ticketId: String,
    mockEnabled: Boolean,
    oralEnabled: Boolean,
    onStart: () -> Unit,
    onOpenMock: () -> Unit,
    onOpenChart: () -> Unit,
    onOpenOral: () -> Unit,
    onOpenAlmanac: () -> Unit,
    onChangeTicket: () -> Unit,
) {
    Scaffold(
        topBar = { TopAppBar(title = { Text(stringResource(R.string.today_title)) }) },
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(
                        text = dueCount.toString(),
                        style = MaterialTheme.typography.displayLarge,
                        modifier = Modifier.semantics { heading() },
                    )
                    Text(
                        text = if (dueCount == 0) stringResource(R.string.today_nothing)
                        else stringResource(R.string.today_to_go_over),
                        style = MaterialTheme.typography.titleLarge,
                    )
                    Spacer(Modifier.height(12.dp))
                    Button(
                        onClick = onStart,
                        enabled = dueCount > 0,
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Text(stringResource(R.string.today_start))
                    }
                }
            }
            TodayRow(stringResource(R.string.today_mock), mockEnabled, onOpenMock)
            TodayRow(stringResource(R.string.today_chart), true, onOpenChart)
            TodayRow(
                title = stringResource(R.string.today_oral),
                enabled = oralEnabled,
                onClick = onOpenOral,
                note = if (oralEnabled) null else stringResource(R.string.today_oral_unavailable),
            )
            TodayRow(
                title = stringResource(R.string.today_almanac),
                enabled = true,
                onClick = onOpenAlmanac,
                note = stringResource(R.string.today_almanac_note),
            )
            val titleRes = ticketTitleRes(ticketId)
            TextButton(onClick = onChangeTicket, modifier = Modifier.fillMaxWidth()) {
                Text(
                    if (titleRes != null) stringResource(titleRes) else ticketId,
                    style = MaterialTheme.typography.titleMedium,
                )
            }
        }
    }
}

@Composable
private fun TodayRow(title: String, enabled: Boolean, onClick: () -> Unit, note: String? = null) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(enabled = enabled, onClick = onClick),
    ) {
        Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 18.dp)) {
            Text(title, style = MaterialTheme.typography.titleLarge)
            if (note != null) {
                Text(note, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}

@Composable
fun TicketListScreen(
    bundle: Result<ContentBundle>?,
    onOpenTicket: (String) -> Unit,
    onBack: () -> Unit,
) {
    NauticleScaffold(title = stringResource(R.string.tickets_title), onBack = onBack) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            items(tickets) { ticket ->
                val count = bundle?.getOrNull()?.lessons?.count { ticket.id in it.tickets }
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(
                            onClickLabel = stringResource(R.string.module_open_action),
                            onClick = { onOpenTicket(ticket.id) },
                        ),
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(stringResource(ticket.titleRes), style = MaterialTheme.typography.titleMedium)
                        Text(stringResource(ticket.descRes), style = MaterialTheme.typography.bodyMedium)
                        if (count != null) {
                            Text(
                                if (count == 0) stringResource(R.string.module_coming_soon)
                                else stringResource(R.string.ticket_lesson_count, count),
                                style = MaterialTheme.typography.labelMedium,
                            )
                        }
                    }
                }
            }
            item {
                Text(
                    text = stringResource(R.string.study_aid_notice),
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(bottom = 16.dp),
                )
            }
        }
    }
}
