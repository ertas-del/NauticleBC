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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.unit.dp
import ca.nauticlebc.app.R
import ca.nauticlebc.app.content.ContentBundle
import ca.nauticlebc.app.content.Lesson
import ca.nauticlebc.app.content.LessonCatalog
import ca.nauticlebc.app.content.ReviewStatus
import ca.nauticlebc.app.progress.LessonProgress

@Composable
fun ModuleScreen(
    moduleId: String,
    bundle: ContentBundle,
    progress: Map<String, LessonProgress>,
    onOpenLesson: (String) -> Unit,
    onBack: () -> Unit,
) {
    val lessons = LessonCatalog.lessonsForModule(bundle.lessons, moduleId)
    val titleRes = moduleTitleRes(moduleId)
    val title = if (titleRes != null) stringResource(titleRes) else moduleId

    NauticleScaffold(title = title, onBack = onBack) { padding ->
        if (lessons.isEmpty()) {
            Text(
                text = stringResource(R.string.module_empty),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(padding).padding(16.dp),
            )
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                items(lessons, key = { it.id }) { lesson ->
                    LessonCard(
                        lesson = lesson,
                        progress = progress[lesson.id] ?: LessonProgress(),
                        onClick = { onOpenLesson(lesson.id) },
                    )
                }
            }
        }
    }
}

@Composable
private fun LessonCard(lesson: Lesson, progress: LessonProgress, onClick: () -> Unit) {
    val reviewLabel = ReviewStatus.labelFor(lesson)
    val reviewText = reviewLabelText(reviewLabel, lesson.reviewedBy, lesson.reviewedOn)
    val best = progress.best
    val progressText = when {
        best != null -> stringResource(R.string.progress_best_score, best.correct, best.total)
        progress.completed -> stringResource(R.string.progress_completed)
        else -> stringResource(R.string.progress_not_started)
    }
    val doneVisible = if (progress.completed || best != null) {
        stringResource(R.string.progress_check_prefix, progressText)
    } else {
        progressText
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                onClickLabel = stringResource(R.string.lesson_open_action),
                onClick = onClick,
            ),
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            Text(lesson.title, style = MaterialTheme.typography.titleMedium)
            Text(lesson.summary, style = MaterialTheme.typography.bodyMedium)
            Text(
                stringResource(R.string.minutes_short, lesson.estimatedMinutes),
                style = MaterialTheme.typography.labelMedium,
            )
            ReviewBadge(text = reviewText, label = reviewLabel)
            Text(
                text = doneVisible,
                style = MaterialTheme.typography.labelLarge,
                // The check mark is decoration; read the plain words instead.
                modifier = Modifier.clearAndSetSemantics { contentDescription = progressText },
            )
        }
    }
}
