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

private data class ModuleCard(
    val id: String,
    @StringRes val titleRes: Int,
    @StringRes val descRes: Int,
    val available: Boolean,
)

private val modules = listOf(
    ModuleCard("colregs", R.string.module_colregs, R.string.module_colregs_desc, available = true),
    ModuleCard("tides", R.string.module_tides, R.string.module_tides_desc, available = true),
    ModuleCard("bridge-watch", R.string.module_bridge_watch, R.string.module_bridge_watch_desc, available = true),
    ModuleCard("sixty-ton", R.string.module_sixty_ton, R.string.module_sixty_ton_desc, available = true),
    ModuleCard("seamanship", R.string.module_seamanship, R.string.module_seamanship_desc, available = true),
    ModuleCard("chartwork", R.string.module_chartwork, R.string.module_chartwork_desc, available = true),
    ModuleCard("stability", R.string.module_stability, R.string.module_stability_desc, available = true),
    ModuleCard("radio", R.string.module_radio, R.string.module_radio_desc, available = true),
    ModuleCard("meteorology", R.string.module_meteorology, R.string.module_meteorology_desc, available = true),
    ModuleCard("canadian-regulations", R.string.module_canadian_regulations, R.string.module_canadian_regulations_desc, available = true),
    ModuleCard("radar", R.string.module_radar, R.string.module_radar_desc, available = true),
    ModuleCard("electronic-navigation", R.string.module_electronic_navigation, R.string.module_electronic_navigation_desc, available = true),
    ModuleCard("machinery", R.string.module_machinery, R.string.module_machinery_desc, available = true),
)

/** Title string for a module id, falling back to the raw id for modules without a home card. */
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
    else -> null
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    bundle: Result<ContentBundle>?,
    onOpenModule: (String) -> Unit,
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

            val loaded = bundle?.getOrNull()?.lessons?.map { it.module }?.toSet()
            val shown = modules.filter { loaded == null || it.id in loaded || it.id == "colregs" || it.id == "tides" }
            items(shown) { module ->
                val cardModifier = if (module.available) {
                    Modifier
                        .fillMaxWidth()
                        .clickable(
                            onClickLabel = stringResource(R.string.module_open_action),
                            onClick = { onOpenModule(module.id) },
                        )
                } else {
                    Modifier.fillMaxWidth()
                }
                Card(modifier = cardModifier) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(stringResource(module.titleRes), style = MaterialTheme.typography.titleMedium)
                        Text(stringResource(module.descRes), style = MaterialTheme.typography.bodyMedium)
                        if (!module.available) {
                            Text(
                                stringResource(R.string.module_coming_soon),
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
