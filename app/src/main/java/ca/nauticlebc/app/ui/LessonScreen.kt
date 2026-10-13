package ca.nauticlebc.app.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Typography
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.sp
import ca.nauticlebc.app.markdown.MdText
import ca.nauticlebc.app.reading.ReadAloud
import ca.nauticlebc.app.reading.ReadingPrefs
import ca.nauticlebc.app.reading.ReadingPrefsStore
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.material3.TextButton
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import ca.nauticlebc.app.R
import ca.nauticlebc.app.content.ContentBundle
import ca.nauticlebc.app.content.Lesson
import ca.nauticlebc.app.content.LessonCatalog
import ca.nauticlebc.app.content.ReviewLabel
import ca.nauticlebc.app.content.ReviewStatus
import ca.nauticlebc.app.content.SourceRef
import ca.nauticlebc.app.progress.LessonProgress

@Composable
fun LessonScreen(
    lesson: Lesson,
    bundle: ContentBundle,
    progress: LessonProgress,
    onMarkComplete: () -> Unit,
    onStartQuiz: () -> Unit,
    onBack: () -> Unit,
) {
    val blocks = remember(lesson.id) { LessonCatalog.bodyBlocks(lesson) }
    val sources = remember(lesson.id, bundle) { LessonCatalog.resolveSources(bundle.sources, lesson.sourceIds) }
    val hasQuiz = remember(lesson.id, bundle) {
        LessonCatalog.questionsForLesson(bundle.questions, lesson.id).isNotEmpty()
    }
    val reviewLabel = ReviewStatus.labelFor(lesson)
    val reviewText = reviewLabelText(reviewLabel, lesson.reviewedBy, lesson.reviewedOn)

    val context = LocalContext.current
    val store = remember { ReadingPrefsStore(context) }
    var reading by remember { mutableStateOf(store.load()) }
    val readAloud = remember { ReadAloud(context) }
    DisposableEffect(Unit) { onDispose { readAloud.shutdown() } }
    val density = LocalDensity.current
    val baseTypography = MaterialTheme.typography
    val typography = remember(baseTypography, reading.relaxed) {
        if (reading.relaxed) baseTypography.relaxed() else baseTypography
    }

    NauticleScaffold(title = stringResource(R.string.lesson_screen_title), onBack = onBack) { padding ->
      CompositionLocalProvider(
        LocalDensity provides Density(density.density, density.fontScale * reading.scale),
      ) {
        MaterialTheme(colorScheme = MaterialTheme.colorScheme, shapes = MaterialTheme.shapes, typography = typography) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Text(
                text = lesson.title,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.semantics { heading() },
            )

            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                ReviewBadge(text = reviewText, label = reviewLabel)
            }

            ReadingControls(
                prefs = reading,
                speaking = readAloud.speaking,
                speechUnavailable = readAloud.unavailable,
                onChange = { reading = it; store.save(it) },
                onToggleSpeech = {
                    if (readAloud.speaking) readAloud.stop()
                    else readAloud.speak(MdText.speechItems(lesson.title, lesson.keyFacts, blocks, lesson.figures), reading.speechRate)
                },
            )
            Text(
                stringResource(R.string.minutes_short, lesson.estimatedMinutes),
                style = MaterialTheme.typography.labelLarge,
            )
            if (reviewLabel != ReviewLabel.ExpertReviewed) {
                Text(
                    text = stringResource(R.string.review_awaiting_note),
                    style = MaterialTheme.typography.bodyMedium,
                )
            }

            ExamFocusCard(keyFacts = lesson.keyFacts)

            if (lesson.objectives.isNotEmpty()) {
                Text(
                    text = stringResource(R.string.lesson_objectives),
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.semantics { heading() },
                )
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    for (objective in lesson.objectives) {
                        Row {
                            Text("•", modifier = Modifier.padding(end = 8.dp))
                            Text(objective, style = MaterialTheme.typography.bodyMedium, modifier = Modifier.weight(1f))
                        }
                    }
                }
            }

            HorizontalDivider()

            MarkdownView(blocks, figures = lesson.figures)

            HorizontalDivider()

            SourcesSection(sources)

            LessonActions(
                completed = progress.completed,
                hasQuiz = hasQuiz,
                onMarkComplete = onMarkComplete,
                onStartQuiz = onStartQuiz,
            )
        }
        }
      }
    }
}

@Composable
private fun ReadingControls(
    prefs: ReadingPrefs,
    speaking: Boolean,
    speechUnavailable: Boolean,
    onChange: (ReadingPrefs) -> Unit,
    onToggleSpeech: () -> Unit,
) {
    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
            OutlinedButton(
                onClick = { onChange(prefs.smaller()) },
                enabled = prefs.canShrink,
                contentPadding = PaddingValues(horizontal = 12.dp),
            ) { Text(stringResource(R.string.reading_smaller)) }
            OutlinedButton(
                onClick = { onChange(prefs.bigger()) },
                enabled = prefs.canGrow,
                contentPadding = PaddingValues(horizontal = 12.dp),
            ) { Text(stringResource(R.string.reading_bigger)) }
            OutlinedButton(
                onClick = { onChange(prefs.toggleRelaxed()) },
                contentPadding = PaddingValues(horizontal = 12.dp),
            ) {
                Text(stringResource(if (prefs.relaxed) R.string.reading_spacing_on else R.string.reading_spacing_off))
            }
        }
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
            Button(onClick = onToggleSpeech, enabled = !speechUnavailable, modifier = Modifier.weight(1f)) {
                Text(stringResource(if (speaking) R.string.read_aloud_stop else R.string.read_aloud_start))
            }
            OutlinedButton(onClick = { onChange(prefs.nextSpeed()) }, contentPadding = PaddingValues(horizontal = 12.dp)) {
                Text(
                    stringResource(
                        when (prefs.speechStep) {
                            0 -> R.string.read_speed_very_slow
                            1 -> R.string.read_speed_slow
                            2 -> R.string.read_speed_normal
                            else -> R.string.read_speed_quick
                        },
                    ),
                )
            }
        }
        if (speechUnavailable) {
            Text(stringResource(R.string.read_aloud_unavailable), style = MaterialTheme.typography.bodySmall)
        }
    }
}

private fun Typography.relaxed(): Typography {
    fun TextStyle.loose() = copy(lineHeight = fontSize * 1.75f, letterSpacing = 0.3.sp)
    return copy(
        bodyLarge = bodyLarge.loose(),
        bodyMedium = bodyMedium.loose(),
        bodySmall = bodySmall.loose(),
        titleMedium = titleMedium.loose(),
        titleLarge = titleLarge.loose(),
        headlineSmall = headlineSmall.loose(),
        headlineMedium = headlineMedium.loose(),
        labelLarge = labelLarge.loose(),
    )
}

/** Sources stay out of the reading flow: one link-style button opens and closes the list. */
@Composable
private fun SourcesSection(sources: List<SourceRef>) {
    if (sources.isEmpty()) return
    var open by rememberSaveable { mutableStateOf(false) }
    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        TextButton(onClick = { open = !open }) {
            Text(
                text = stringResource(
                    if (open) R.string.lesson_sources_hide else R.string.lesson_sources_show,
                    sources.size,
                ),
                style = MaterialTheme.typography.labelLarge,
            )
        }
        if (open) {
            for (source in sources) {
                val text = when {
                    !source.known -> stringResource(R.string.lesson_source_unknown, source.title)
                    else -> {
                        val detail = listOfNotNull(source.publisher, source.edition).joinToString(", ")
                        if (detail.isEmpty()) source.title else stringResource(R.string.lesson_source_detail, source.title, detail)
                    }
                }
                Row {
                    Text("•", style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(end = 8.dp))
                    Text(text, style = MaterialTheme.typography.bodyMedium, modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

@Composable
private fun LessonActions(
    completed: Boolean,
    hasQuiz: Boolean,
    onMarkComplete: () -> Unit,
    onStartQuiz: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        if (hasQuiz) {
            Button(onClick = onStartQuiz, modifier = Modifier.fillMaxWidth()) {
                Text(stringResource(R.string.lesson_take_quiz))
            }
        } else {
            Text(stringResource(R.string.lesson_no_quiz), style = MaterialTheme.typography.bodyMedium)
        }
        if (completed) {
            Text(
                text = stringResource(R.string.lesson_is_complete),
                style = MaterialTheme.typography.labelLarge,
            )
        } else {
            OutlinedButton(onClick = onMarkComplete, modifier = Modifier.fillMaxWidth()) {
                Text(stringResource(R.string.lesson_mark_complete))
            }
        }
    }
}
