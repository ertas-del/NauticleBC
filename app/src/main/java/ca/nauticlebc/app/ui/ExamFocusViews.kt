package ca.nauticlebc.app.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.unit.dp
import ca.nauticlebc.app.R
import ca.nauticlebc.app.content.ContentBundle
import ca.nauticlebc.app.content.ExamFocus
import ca.nauticlebc.app.content.LessonKeyFacts
import kotlin.random.Random

/**
 * Collapsible "Exam focus" card listing a lesson's key facts. Shown open by default; the learner can fold it
 * away with the Show/Hide button. Draws nothing when the lesson has no usable key facts.
 */
@Composable
fun ExamFocusCard(keyFacts: List<String>, modifier: Modifier = Modifier) {
    val facts = remember(keyFacts) { ExamFocus.cleanFacts(keyFacts) }
    if (facts.isEmpty()) return
    var expanded by rememberSaveable { mutableStateOf(true) }
    val stateText = stringResource(if (expanded) R.string.exam_focus_state_expanded else R.string.exam_focus_state_collapsed)

    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
    ) {
        Column(modifier = Modifier.padding(start = 16.dp, end = 8.dp, top = 8.dp, bottom = 12.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Text(
                    text = stringResource(R.string.exam_focus_title),
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier
                        .weight(1f)
                        .semantics { heading() },
                )
                TextButton(
                    onClick = { expanded = !expanded },
                    modifier = Modifier.semantics { stateDescription = stateText },
                ) {
                    Text(stringResource(if (expanded) R.string.exam_focus_hide else R.string.exam_focus_show))
                }
            }
            if (expanded) {
                Column(
                    modifier = Modifier.padding(end = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp),
                ) {
                    for (fact in facts) {
                        FactBullet(fact)
                    }
                }
            }
        }
    }
}

@Composable
private fun FactBullet(text: String) {
    Row(modifier = Modifier.fillMaxWidth().semantics(mergeDescendants = true) {}) {
        // The bullet is decoration; a screen reader just reads the fact.
        Text(
            text = "•",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .padding(end = 8.dp)
                .clearAndSetSemantics {},
        )
        Text(text = text, style = MaterialTheme.typography.bodyLarge, modifier = Modifier.weight(1f))
    }
}

/** Every key fact of a module's lessons, grouped by lesson, with a button to start a quiz on the whole module. */
@Composable
fun ExamFocusScreen(
    moduleId: String,
    bundle: ContentBundle,
    onQuizModule: () -> Unit,
    onBack: () -> Unit,
) {
    val groups = remember(bundle, moduleId) { ExamFocus.keyFactsForModule(bundle.lessons, moduleId) }
    val questionCount = remember(bundle, moduleId) {
        ExamFocus.moduleQuestionCount(bundle.questions, bundle.lessons, moduleId)
    }
    val titleRes = moduleTitleRes(moduleId)
    val moduleTitle = if (titleRes != null) stringResource(titleRes) else moduleId

    NauticleScaffold(title = stringResource(R.string.exam_focus_title), onBack = onBack) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp),
            contentPadding = PaddingValues(bottom = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            item {
                Text(
                    text = moduleTitle,
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.semantics { heading() },
                )
            }
            item {
                Text(
                    text = stringResource(R.string.exam_focus_screen_note),
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
            item {
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    if (questionCount > 0) {
                        Button(onClick = onQuizModule, modifier = Modifier.fillMaxWidth()) {
                            Text(stringResource(R.string.exam_focus_quiz_module))
                        }
                        Text(
                            text = stringResource(R.string.exam_focus_question_count, questionCount),
                            style = MaterialTheme.typography.labelLarge,
                        )
                    } else {
                        Text(
                            text = stringResource(R.string.exam_focus_no_questions),
                            style = MaterialTheme.typography.bodyMedium,
                        )
                    }
                }
            }
            if (groups.isEmpty()) {
                item {
                    Text(
                        text = stringResource(R.string.exam_focus_empty),
                        style = MaterialTheme.typography.bodyLarge,
                    )
                }
            } else {
                items(groups, key = { it.lessonId }) { group ->
                    LessonFactsCard(group)
                }
            }
        }
    }
}

@Composable
private fun LessonFactsCard(group: LessonKeyFacts) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text(
                text = group.lessonTitle,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.semantics { heading() },
            )
            for (fact in group.facts) {
                FactBullet(fact)
            }
        }
    }
}

/**
 * One quiz over all of a module's questions in random order. The order comes from a saved seed, so it is the
 * same after rotation or process death, and "Try again" picks a new seed to reshuffle. Scores are not stored
 * (best scores are kept per lesson).
 */
@Composable
fun ModuleQuizScreen(moduleId: String, bundle: ContentBundle, onBack: () -> Unit) {
    var seed by rememberSaveable { mutableStateOf(Random.nextLong()) }
    val questions = remember(bundle, moduleId, seed) {
        ExamFocus.moduleQuiz(bundle.questions, bundle.lessons, moduleId, Random(seed))
    }
    val titleRes = moduleTitleRes(moduleId)
    val moduleTitle = if (titleRes != null) stringResource(titleRes) else moduleId
    val backLabel = stringResource(R.string.quiz_back_to_exam_focus)

    QuizScreen(
        titleText = stringResource(R.string.module_quiz_heading, moduleTitle),
        stateKey = "module-$moduleId-$seed",
        questions = questions,
        best = null,
        onRecordScore = {},
        onBackToLesson = onBack,
        onBackToLessons = onBack,
        backLabel = backLabel,
        doneLabel = backLabel,
        onRetry = { seed = Random.nextLong() },
    )
}
