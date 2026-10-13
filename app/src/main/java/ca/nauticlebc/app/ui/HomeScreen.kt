package ca.nauticlebc.app.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    bundle: Result<ContentBundle>?,
    onOpenTicket: (String) -> Unit,
) {
    Scaffold(
        topBar = { TopAppBar(title = { Text(stringResource(R.string.home_title)) }) },
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            item {
                Text(
                    text = stringResource(R.string.home_subtitle),
                    style = MaterialTheme.typography.bodyLarge,
                )
            }

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
                val status = bundle?.fold(
                    onSuccess = { stringResource(R.string.content_status_loaded, it.lessons.size, it.questions.size) },
                    onFailure = { stringResource(R.string.content_status_error) },
                )
                if (status != null) {
                    Text(status, style = MaterialTheme.typography.labelMedium)
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
