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
import androidx.compose.runtime.mutableLongStateOf
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
import kotlinx.coroutines.delay

/**
 * One quiz session over [questions]. Used for a lesson's quiz and for a whole-module quiz.
 *
 * @param titleText the lesson or module name shown above the questions
 * @param stateKey identifies this session; saved progress is only restored for the same key
 * @param best the learner's best stored score to show on the results page, if this quiz keeps one
 * @param onRetry replaces the default "start the same questions again"; the module quiz uses it to reshuffle
 * @param closedBook when true, the working stays hidden until the end and the clock can run out
 * @param timeLimitMinutes closed-book time limit. Null means no clock.
 * @param passMarkPercent pass mark shown on the results page. Null hides the pass line.
 * @param onFinished called once the quiz is over, with every answer, so missed questions can be stored
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
    closedBook: Boolean = false,
    timeLimitMinutes: Int? = null,
    passMarkPercent: Int? = null,
    onFinished: (QuizState) -> Unit = {},
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
    LaunchedEffect(stateKey, finished) {
        if (finished && state.total > 0) {
            onRecordScore(state.score)
            onFinished(state)
        }
    }
    val timed = closedBook && timeLimitMinutes != null
    var endsAt by rememberSaveable(stateKey) {
        mutableLongStateOf(
            if (timed && timeLimitMinutes != null) System.currentTimeMillis() + timeLimitMinutes * 60_000L else 0L,
        )
    }
    var now by remember { mutableLongStateOf(System.currentTimeMillis()) }
    LaunchedEffect(stateKey, endsAt) {
        if (endsAt == 0L) return@LaunchedEffect
        while (!state.isFinished && System.currentTimeMillis() < endsAt) {
            delay(1_000)
            now = System.currentTimeMillis()
        }
        if (!state.isFinished) state = state.forceFinish()
    }
    val timeLeft = if (timed && endsAt > 0L) (endsAt - now).coerceAtLeast(0L) else null

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
            if (closedBook && !finished && state.total > 0) {
                Text(stringResource(R.string.mock_closed_banner), style = MaterialTheme.typography.bodyMedium)
                timeLeft?.let { left ->
                    val totalSeconds = left / 1000
                    Text(
                        text = stringResource(R.string.mock_time_left, (totalSeconds / 60).toInt(), (totalSeconds % 60).toInt()),
                        style = MaterialTheme.typography.titleMedium,
                    )
                }
            } else {
                QuizReviewNote(questions)
            }

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
                    passMarkPercent = if (closedBook) passMarkPercent else null,
                    questions = state.questions,
                    answers = state.answers,
                    showMisses = closedBook,
                    onRetry = onRetry ?: { state = QuizState.start(questions) },
                    doneLabel = doneLabel,
                    onBackToLessons = onBackToLessons,
                )

                else -> QuestionView(
                    state = state,
                    reveal = !closedBook,
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
    reveal: Boolean,
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
                reveal = reveal,
                isCorrect = i == question.correctIndex,
                isSelected = i == selected,
                onClick = { onSelect(i) },
            )
        }
    }

    if (selected != null && reveal) {
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
    } else if (selected != null) {
        Text(stringResource(R.string.mock_locked), style = MaterialTheme.typography.titleMedium)
        Button(onClick = onNext, modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)) {
            Text(stringResource(if (state.isLastQuestion) R.string.quiz_see_results else R.string.quiz_next))
        }
    }
}

@Composable
private fun OptionCard(
    label: String,
    answered: Boolean,
    reveal: Boolean,
    isCorrect: Boolean,
    isSelected: Boolean,
    onClick: () -> Unit,
) {
    val show = answered && reveal
    val container = when {
        show && isCorrect -> MaterialTheme.colorScheme.primaryContainer
        show && isSelected -> MaterialTheme.colorScheme.errorContainer
        answered && isSelected -> MaterialTheme.colorScheme.secondaryContainer
        else -> MaterialTheme.colorScheme.surfaceVariant
    }
    // Colour is never the only signal: the result is also written out.
    val marker: String? = when {
        !show -> null
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
    passMarkPercent: Int?,
    questions: List<Question>,
    answers: List<Int>,
    showMisses: Boolean,
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
    if (passMarkPercent != null) {
        val passed = score.percent >= passMarkPercent
        Text(
            text = stringResource(
                if (passed) R.string.mock_passed else R.string.mock_failed,
                score.percent,
                passMarkPercent,
            ),
            style = MaterialTheme.typography.titleMedium,
        )
    }
    if (best != null) {
        Text(
            text = stringResource(R.string.quiz_results_best, best.correct, best.total),
            style = MaterialTheme.typography.bodyLarge,
        )
    }
    if (showMisses) {
        val misses = questions.mapIndexedNotNull { i, question ->
            val choice = answers.getOrNull(i)
            if (choice == question.correctIndex) null else question to choice
        }
        if (misses.isNotEmpty()) {
            Text(
                text = stringResource(R.string.mock_misses_title),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.semantics { heading() },
            )
            misses.forEach { (question, choice) -> MissCard(question, choice) }
        }
    }
    Button(onClick = onRetry, modifier = Modifier.fillMaxWidth()) {
        Text(stringResource(R.string.quiz_retry))
    }
    OutlinedButton(onClick = onBackToLessons, modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)) {
        Text(doneLabel)
    }
}

@Composable
private fun MissCard(question: Question, choice: Int?) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
            Text(question.stem, style = MaterialTheme.typography.titleMedium)
            val yours = choice?.let { question.options.getOrNull(it) }
            Text(
                text = if (yours == null) stringResource(R.string.mock_no_answer)
                else stringResource(R.string.mock_your_answer, yours),
                style = MaterialTheme.typography.bodyLarge,
            )
            val marked = question.options.getOrNull(question.correctIndex).orEmpty()
            Text(stringResource(R.string.mock_correct_answer, marked), style = MaterialTheme.typography.bodyLarge)
            Text(question.explanation, style = MaterialTheme.typography.bodyLarge)
        }
    }
}
