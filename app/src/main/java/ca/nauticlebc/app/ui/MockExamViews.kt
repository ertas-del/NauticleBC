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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ca.nauticlebc.app.R
import ca.nauticlebc.app.content.ContentBundle
import ca.nauticlebc.app.content.ExamFocus
import ca.nauticlebc.app.quiz.QuizState
import kotlin.random.Random

/** List of practice exams. Each one is a practice format, not the official exam. */
@Composable
fun MockExamListScreen(bundle: ContentBundle, onOpen: (String) -> Unit, onBack: () -> Unit) {
    NauticleScaffold(title = stringResource(R.string.mock_list_title), onBack = onBack) { padding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(padding).padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            item {
                Text(stringResource(R.string.mock_list_intro), style = MaterialTheme.typography.bodyLarge)
            }
            if (bundle.mockExams.isEmpty()) {
                item { Text(stringResource(R.string.mock_list_empty), style = MaterialTheme.typography.bodyLarge) }
            }
            items(bundle.mockExams, key = { it.id }) { exam ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(
                            onClickLabel = stringResource(R.string.mock_start_action),
                            onClick = { onOpen(exam.id) },
                        ),
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(exam.title, style = MaterialTheme.typography.titleMedium)
                        Text(
                            stringResource(
                                R.string.mock_summary,
                                exam.questionCount,
                                exam.timeLimitMinutes,
                                exam.passMarkPercent,
                            ),
                            style = MaterialTheme.typography.bodyMedium,
                        )
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

/** One mock exam drawn from the question banks by the exam's blueprint. "Try again" draws a new exam. */
@Composable
fun MockExamScreen(
    examId: String,
    bundle: ContentBundle,
    onBack: () -> Unit,
    onFinished: (QuizState) -> Unit = {},
) {
    val exam = bundle.mockExams.firstOrNull { it.id == examId }
    if (exam == null) {
        MessageScreen(
            title = stringResource(R.string.mock_list_title),
            message = stringResource(R.string.mock_not_found),
            onBack = onBack,
        )
        return
    }
    var seed by rememberSaveable { mutableStateOf(Random.nextLong()) }
    val questions = remember(bundle, examId, seed) {
        ExamFocus.mockExam(exam, bundle.questions, bundle.lessons, Random(seed))
    }
    val backLabel = stringResource(R.string.mock_back)
    QuizScreen(
        titleText = exam.title,
        stateKey = "mock-$examId-$seed",
        questions = questions,
        best = null,
        onRecordScore = {},
        onBackToLesson = onBack,
        onBackToLessons = onBack,
        backLabel = backLabel,
        doneLabel = backLabel,
        onRetry = { seed = Random.nextLong() },
        closedBook = true,
        timeLimitMinutes = exam.timeLimitMinutes,
        passMarkPercent = exam.passMarkPercent,
        onFinished = onFinished,
    )
}
