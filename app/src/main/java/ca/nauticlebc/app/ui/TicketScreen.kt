package ca.nauticlebc.app.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ca.nauticlebc.app.R
import ca.nauticlebc.app.content.ContentBundle

private val MODULE_ORDER = listOf(
    "bridge-watch", "sixty-ton", "colregs", "seamanship", "chartwork", "tides", "stability", "meteorology",
    "radio", "radar", "electronic-navigation", "canadian-regulations", "machinery", "fishing",
    "svop", "fm4-overview", "fm4-chartwork", "fm4-navigation-safety", "fm4-stability-construction", "fm4-oral",
)

/** Tickets with these subjects get the practice sums card. */
private val DRILL_MODULES = setOf("chartwork", "fm4-chartwork", "svop")

/** Everything for one ticket: its subjects (modules) with lesson counts, then its mock exams. */
@Composable
fun TicketScreen(
    ticketId: String,
    bundle: ContentBundle,
    onOpenModule: (String) -> Unit,
    onOpenMocks: () -> Unit,
    onOpenDrills: () -> Unit,
    onOpenCharts: () -> Unit,
    onBack: () -> Unit,
) {
    val ticketBundle = remember(bundle, ticketId) { bundle.forTicket(ticketId) }
    val modules = remember(ticketBundle) {
        ticketBundle.lessons.groupBy { it.module }
            .map { (module, lessons) -> module to lessons.size }
            .sortedBy { (module, _) -> MODULE_ORDER.indexOf(module).let { if (it < 0) MODULE_ORDER.size else it } }
    }
    val titleRes = ticketTitleRes(ticketId)
    val title = if (titleRes != null) stringResource(titleRes) else ticketId

    NauticleScaffold(title = title, onBack = onBack) { padding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(padding).padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            if (modules.isEmpty()) {
                item {
                    Text(stringResource(R.string.ticket_empty), style = MaterialTheme.typography.bodyLarge)
                }
            }
            items(modules, key = { it.first }) { (module, count) ->
                val moduleTitleRes = moduleTitleRes(module)
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(
                            onClickLabel = stringResource(R.string.module_open_action),
                            onClick = { onOpenModule(module) },
                        ),
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            if (moduleTitleRes != null) stringResource(moduleTitleRes) else module,
                            style = MaterialTheme.typography.titleMedium,
                        )
                        Text(
                            stringResource(R.string.ticket_lesson_count, count),
                            style = MaterialTheme.typography.bodyMedium,
                        )
                    }
                }
            }
            if (modules.any { (module, _) -> module in DRILL_MODULES }) {
                item(key = "drills") {
                    Card(modifier = Modifier.fillMaxWidth().clickable(onClick = onOpenDrills)) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(stringResource(R.string.drill_card_title), style = MaterialTheme.typography.titleMedium)
                            Text(stringResource(R.string.drill_card_desc), style = MaterialTheme.typography.bodyMedium)
                        }
                    }
                }
            }
            if (bundle.charts.isNotEmpty() && modules.any { (module, _) -> module in DRILL_MODULES }) {
                item(key = "charts") {
                    Card(modifier = Modifier.fillMaxWidth().clickable(onClick = onOpenCharts)) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(stringResource(R.string.chart_card_title), style = MaterialTheme.typography.titleMedium)
                            Text(stringResource(R.string.chart_card_desc), style = MaterialTheme.typography.bodyMedium)
                        }
                    }
                }
            }
            if (ticketBundle.mockExams.isNotEmpty()) {
                item(key = "mock-exams") {
                    Card(modifier = Modifier.fillMaxWidth().clickable(onClick = onOpenMocks)) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(stringResource(R.string.mock_card_title), style = MaterialTheme.typography.titleMedium)
                            Text(stringResource(R.string.mock_card_desc), style = MaterialTheme.typography.bodyMedium)
                        }
                    }
                }
            }
            item {
                Text(
                    stringResource(R.string.study_aid_notice),
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(bottom = 16.dp),
                )
            }
        }
    }
}
