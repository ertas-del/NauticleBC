package ca.nauticlebc.app.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.LiveRegionMode
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.liveRegion
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import ca.nauticlebc.app.R
import ca.nauticlebc.app.content.Question
import ca.nauticlebc.app.content.ReviewStatus
import ca.nauticlebc.app.quiz.QuizScore
import ca.nauticlebc.app.quiz.QuizState

/**
 * One quiz session over [questions]. Used for a lesson's quiz and for a whole-module quiz.
 *
 * @param titleText the lesson or module name shown above the questions
 * @param stateKey identifies this session; saved progress is only restored for the same key
 * @param best the learner's best stored score to show on the results page, if this quiz keeps one
 * @param onRetry replaces the default "start the same questions again"; the module quiz uses it to reshuffle
 * @param backLabel text of the button that leaves an empty quiz
 * @param doneLabel text of the button on the results page that leaves the quiz
 */
@Composable
fun QuizScreen(
    titleText: String,
    stateKey: String,
    questions: List<Question>,
    best: QuizScore?,
    onRecordScore: (QuizScore) -> Unit,
    onBackToLesson: () -> Unit,
    onBackToLessons: () -> Unit,
    backLabel: String,
    doneLabel: String,
    onRetry: (() -> Unit)? = null,
) {
    val saver = remember(questions) {
        Saver<QuizState, ArrayList<Int>>(
            save = { ArrayList(it.encode()) },
            restore = { QuizState.restore(questions, it) },
        )
    }
    var state by rememberSaveable(stateKey, stateSaver = saver) {
        mutableStateOf(QuizState.start(questions))
    }

    val finished = state.isFinished
    LaunchedEffect(finished) {
        if (finished && state.total > 0) onRecordScore(state.score)
    }

    NauticleScaffold(title = stringResource(R.string.quiz_screen_title), onBack = onBackToLesson) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Text(
                text = titleText,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.semantics { heading() },
            )
            QuizReviewNote(questions)

            when {
                state.total == 0 -> {
                    Text(stringResource(R.string.quiz_empty), style = MaterialTheme.typography.bodyLarge)
                    OutlinedButton(onClick = onBackToLesson, modifier = Modifier.fillMaxWidth()) {
                        Text(backLabel)
                    }
                }

                finished -> QuizResults(
                    score = state.score,
                    best = best,
                    onRetry = onRetry ?: { state = QuizState.start(questions) },
                    doneLabel = doneLabel,
                    onBackToLessons = onBackToLessons,
                )

                else -> QuestionView(
                    state = state,
                    onSelect = { choice -> state = state.select(choice) },
                    onNext = { state = state.next() },
                )
            }
        }
    }
}

@Composable
private fun QuizReviewNote(questions: List<Question>) {
    if (questions.isEmpty()) return
    val label = ReviewStatus.leastReviewed(questions.map { ReviewStatus.labelFor(it.reviewStatus) })
    val text = reviewLabelText(label)
    ReviewBadge(
        text = stringResource(R.string.quiz_review_prefix, text),
        label = label,
    )
}

@Composable
private fun QuestionView(
    state: QuizState,
    onSelect: (Int) -> Unit,
    onNext: () -> Unit,
) {
    val question = state.current ?: return
    val selected = state.selected
    val answered = selected != null

    Text(
        text = stringResource(R.string.quiz_progress, state.index + 1, state.total),
        style = MaterialTheme.typography.labelLarge,
    )
    Text(
        text = question.stem,
        style = MaterialTheme.typography.titleLarge,
        modifier = Modifier.semantics { heading() },
    )

    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        question.options.forEachIndexed { i, option ->
            OptionCard(
                label = stringResource(R.string.quiz_option_label, ('A' + i).toString(), option),
                answered = answered,
                isCorrect = i == question.correctIndex,
                isSelected = i == selected,
                onClick = { onSelect(i) },
            )
        }
    }

    if (selected != null) {
        val right = selected == question.correctIndex
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .semantics { liveRegion = LiveRegionMode.Polite },
            colors = CardDefaults.cardColors(
                containerColor = if (right) {
                    MaterialTheme.colorScheme.primaryContainer
                } else {
                    MaterialTheme.colorScheme.errorContainer
                },
            ),
        ) {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Text(
                    text = stringResource(if (right) R.string.quiz_feedback_correct else R.string.quiz_feedback_incorrect),
                    style = MaterialTheme.typography.titleMedium,
                )
                Text(text = question.explanation, style = MaterialTheme.typography.bodyLarge)
            }
        }
        Button(onClick = onNext, modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)) {
            Text(stringResource(if (state.isLastQuestion) R.string.quiz_see_results else R.string.quiz_next))
        }
    }
}

@Composable
private fun OptionCard(
    label: String,
    answered: Boolean,
    isCorrect: Boolean,
    isSelected: Boolean,
    onClick: () -> Unit,
) {
    val container = when {
        answered && isCorrect -> MaterialTheme.colorScheme.primaryContainer
        answered && isSelected -> MaterialTheme.colorScheme.errorContainer
        else -> MaterialTheme.colorScheme.surfaceVariant
    }
    // Colour is never the only signal: the result is also written out.
    val marker: String? = when {
        !answered -> null
        isCorrect && isSelected -> stringResource(R.string.quiz_option_yours_right)
        isCorrect -> stringResource(R.string.quiz_option_correct)
        isSelected -> stringResource(R.string.quiz_option_yours_wrong)
        else -> null
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(enabled = !answered, role = Role.Button, onClick = onClick),
        colors = CardDefaults.cardColors(containerColor = container),
    ) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(text = label, style = MaterialTheme.typography.bodyLarge)
            if (marker != null) {
                Text(text = marker, style = MaterialTheme.typography.labelLarge)
            }
        }
    }
}

@Composable
private fun QuizResults(
    score: QuizScore,
    best: QuizScore?,
    onRetry: () -> Unit,
    doneLabel: String,
    onBackToLessons: () -> Unit,
) {
    Text(
        text = stringResource(R.string.quiz_results_title),
        style = MaterialTheme.typography.headlineSmall,
        modifier = Modifier.semantics { heading() },
    )
    Text(
        text = stringResource(R.string.quiz_results_score, score.correct, score.total, score.percent),
        style = MaterialTheme.typography.titleLarge,
    )
    if (best != null) {
        Text(
            text = stringResource(R.string.quiz_results_best, best.correct, best.total),
            style = MaterialTheme.typography.bodyLarge,
        )
    }
    Button(onClick = onRetry, modifier = Modifier.fillMaxWidth()) {
        Text(stringResource(R.string.quiz_retry))
    }
    OutlinedButton(onClick = onBackToLessons, modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)) {
        Text(doneLabel)
    }
}
