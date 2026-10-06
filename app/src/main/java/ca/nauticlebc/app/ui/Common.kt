package ca.nauticlebc.app.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ca.nauticlebc.app.R
import ca.nauticlebc.app.content.ContentBundle
import ca.nauticlebc.app.content.ReviewLabel

/** Screen frame with a title and a text Back button (no icon dependencies). */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NauticleScaffold(
    title: String,
    onBack: () -> Unit,
    content: @Composable (PaddingValues) -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(title) },
                navigationIcon = {
                    TextButton(onClick = onBack) { Text(stringResource(R.string.action_back)) }
                },
            )
        },
        content = content,
    )
}

/** Shows a plain message (loading, error, not found) inside the standard frame. */
@Composable
fun MessageScreen(title: String, message: String, onBack: () -> Unit) {
    NauticleScaffold(title = title, onBack = onBack) { padding ->
        Box(modifier = Modifier.padding(padding).padding(16.dp)) {
            Text(message, style = MaterialTheme.typography.bodyLarge)
        }
    }
}

/** Runs [content] once the bundle has loaded; otherwise shows a loading or error message. */
@Composable
fun BundleGate(
    result: Result<ContentBundle>?,
    title: String,
    onBack: () -> Unit,
    content: @Composable (ContentBundle) -> Unit,
) {
    val bundle = result?.getOrNull()
    if (bundle != null) {
        content(bundle)
    } else {
        val message = if (result == null) stringResource(R.string.loading) else stringResource(R.string.error_content)
        MessageScreen(title = title, message = message, onBack = onBack)
    }
}

/** Text for a review label, naming the reviewer and date when they are recorded. */
@Composable
fun reviewLabelText(label: ReviewLabel, reviewedBy: String? = null, reviewedOn: String? = null): String =
    when (label) {
        ReviewLabel.Draft -> stringResource(R.string.review_draft)
        ReviewLabel.AwaitingExpertReview -> stringResource(R.string.review_awaiting)
        ReviewLabel.ExpertReviewed -> when {
            !reviewedBy.isNullOrBlank() && !reviewedOn.isNullOrBlank() ->
                stringResource(R.string.review_done_by, reviewedBy, reviewedOn)
            !reviewedBy.isNullOrBlank() -> stringResource(R.string.review_done_by_only, reviewedBy)
            else -> stringResource(R.string.review_done)
        }
    }

/** A visible badge for a review status. The status is spelled out in words, never colour alone. */
@Composable
fun ReviewBadge(text: String, label: ReviewLabel, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier,
        color = if (label == ReviewLabel.ExpertReviewed) {
            MaterialTheme.colorScheme.primaryContainer
        } else {
            MaterialTheme.colorScheme.tertiaryContainer
        },
        shape = MaterialTheme.shapes.small,
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
        )
    }
}
