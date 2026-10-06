package ca.nauticlebc.app.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import ca.nauticlebc.app.markdown.MdBlock
import ca.nauticlebc.app.markdown.MdSpan

/** Renders parsed Markdown blocks as a column. Text sizes come from the theme so large fonts scale. */
@Composable
fun MarkdownView(blocks: List<MdBlock>, modifier: Modifier = Modifier) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(12.dp)) {
        for (block in blocks) {
            MarkdownBlockView(block)
        }
    }
}

@Composable
private fun MarkdownBlockView(block: MdBlock) {
    when (block) {
        is MdBlock.Heading -> {
            val style = when (block.level) {
                1 -> MaterialTheme.typography.headlineSmall
                2 -> MaterialTheme.typography.titleLarge
                3 -> MaterialTheme.typography.titleMedium
                else -> MaterialTheme.typography.titleSmall
            }
            Text(
                text = toAnnotated(block.spans),
                style = style,
                modifier = Modifier
                    .fillMaxWidth()
                    .semantics { heading() },
            )
        }

        is MdBlock.Paragraph -> Text(
            text = toAnnotated(block.spans),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.fillMaxWidth(),
        )

        is MdBlock.Quote -> Surface(
            color = MaterialTheme.colorScheme.surfaceVariant,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                text = toAnnotated(block.spans),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(12.dp),
            )
        }

        is MdBlock.BulletList -> Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
            for (item in block.items) {
                ListItemRow(marker = "•", spans = item)
            }
        }

        is MdBlock.NumberedList -> Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
            block.items.forEachIndexed { i, item ->
                ListItemRow(marker = "${block.start + i}.", spans = item)
            }
        }

        MdBlock.Rule -> HorizontalDivider()
    }
}

@Composable
private fun ListItemRow(marker: String, spans: List<MdSpan>) {
    Row(modifier = Modifier.fillMaxWidth().semantics(mergeDescendants = true) {}) {
        // The marker is decoration; a screen reader just reads the item text.
        Text(
            text = marker,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .padding(end = 8.dp)
                .clearAndSetSemantics {},
        )
        Text(
            text = toAnnotated(spans),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.weight(1f),
        )
    }
}

private fun toAnnotated(spans: List<MdSpan>): AnnotatedString = buildAnnotatedString {
    for (span in spans) {
        val style = SpanStyle(
            fontWeight = if (span.bold) FontWeight.Bold else null,
            fontStyle = if (span.italic) FontStyle.Italic else null,
        )
        withStyle(style) { append(span.text) }
    }
}
