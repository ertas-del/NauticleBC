package ca.nauticlebc.app.ui

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ca.nauticlebc.app.R
import ca.nauticlebc.app.content.ContentBundle
import ca.nauticlebc.app.content.ContentRepository

private data class ModuleCard(val titleRes: Int, val descRes: Int, val available: Boolean)

private val modules = listOf(
    ModuleCard(R.string.module_colregs, R.string.module_colregs_desc, available = true),
    ModuleCard(R.string.module_tides, R.string.module_tides_desc, available = true),
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    val context = LocalContext.current
    val bundle by produceState<Result<ContentBundle>?>(initialValue = null) {
        value = ContentRepository.load(context)
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text(stringResource(R.string.home_title)) }) },
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Text(
                text = stringResource(R.string.home_subtitle),
                style = MaterialTheme.typography.bodyLarge,
            )

            LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                items(modules) { module ->
                    Card(modifier = Modifier.fillMaxWidth()) {
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
            }

            val status = bundle?.fold(
                onSuccess = { stringResource(R.string.content_status_loaded, it.lessons.size, it.questions.size) },
                onFailure = { stringResource(R.string.content_status_error) },
            )
            if (status != null) {
                Text(status, style = MaterialTheme.typography.labelMedium)
            }

            Text(
                text = stringResource(R.string.study_aid_notice),
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(bottom = 16.dp),
            )
        }
    }
}
