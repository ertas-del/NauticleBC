package ca.nauticlebc.app.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import ca.nauticlebc.app.R
import ca.nauticlebc.app.content.ContentBundle
import ca.nauticlebc.app.oral.OralCards

/** One oral prompt at a time. Marking points stay hidden until the learner has answered out loud. */
@Composable
fun OralScreen(
    ticketId: String,
    bundle: ContentBundle,
    onGrade: (questionId: String, correct: Boolean) -> Unit,
    onBack: () -> Unit,
) {
    val questions = OralCards.forTicket(ticketId, bundle.questions)
    var index by rememberSaveable(ticketId) { mutableIntStateOf(0) }
    var showMarks by rememberSaveable(ticketId, index) { mutableStateOf(false) }

    NauticleScaffold(title = stringResource(R.string.oral_screen_title), onBack = onBack) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            if (questions.isEmpty()) {
                Text(stringResource(R.string.oral_empty), style = MaterialTheme.typography.bodyLarge)
                return@Column
            }
            val question = questions[index.coerceIn(0, questions.lastIndex)]
            Text(
                text = stringResource(R.string.oral_progress, index + 1, questions.size),
                style = MaterialTheme.typography.labelLarge,
            )
            Text(
                text = question.stem,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.semantics { heading() },
            )
            Text(stringResource(R.string.oral_prompt_hint), style = MaterialTheme.typography.bodyLarge)
            if (!showMarks) {
                Button(onClick = { showMarks = true }, modifier = Modifier.fillMaxWidth()) {
                    Text(stringResource(R.string.oral_show_marks))
                }
            } else {
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        val marked = question.options.getOrNull(question.correctIndex).orEmpty()
                        Text(stringResource(R.string.mock_correct_answer, marked), style = MaterialTheme.typography.titleMedium)
                        Text(question.explanation, style = MaterialTheme.typography.bodyLarge)
                    }
                }
                Button(
                    onClick = {
                        onGrade(question.id, true)
                        showMarks = false
                        index = (index + 1) % questions.size
                    },
                    modifier = Modifier.fillMaxWidth(),
                ) { Text(stringResource(R.string.oral_got_it)) }
                OutlinedButton(
                    onClick = {
                        onGrade(question.id, false)
                        showMarks = false
                        index = (index + 1) % questions.size
                    },
                    modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
                ) { Text(stringResource(R.string.oral_missed)) }
            }
        }
    }
}
