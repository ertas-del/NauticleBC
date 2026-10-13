package ca.nauticlebc.app.ui

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.ui.text.font.FontFamily
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import ca.nauticlebc.app.R
import ca.nauticlebc.app.content.Figure
import ca.nauticlebc.app.markdown.MdBlock
import ca.nauticlebc.app.markdown.MdSpan
import ca.nauticlebc.app.markdown.MdText

/** Renders parsed Markdown blocks as a column. Text sizes come from the theme so large fonts scale. */
@Composable
fun MarkdownView(blocks: List<MdBlock>, modifier: Modifier = Modifier, figures: List<Figure> = emptyList()) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(12.dp)) {
        for (block in blocks) {
            MarkdownBlockView(block, figures)
        }
    }
}

@Composable
private fun MarkdownBlockView(block: MdBlock, figures: List<Figure>) {
    when (block) {
        is MdBlock.Heading -> {
            val style = when (block.level) {
                1 -> MaterialTheme.typography.headlineSmall
                2 -> MaterialTheme.typography.titleLarge
                3 -> MaterialTheme.typography.titleMedium
                else -> MaterialTheme.typography.titleSmall
            }
            InlineText(
                spans = block.spans,
                style = style,
                modifier = Modifier
                    .fillMaxWidth()
                    .semantics { heading() },
            )
        }

        is MdBlock.Paragraph -> InlineText(
            spans = block.spans,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.fillMaxWidth(),
        )

        is MdBlock.Quote -> Surface(
            color = MaterialTheme.colorScheme.surfaceVariant,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth(),
        ) {
            InlineText(
                spans = block.spans,
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

        is MdBlock.Table -> TableView(block)

        is MdBlock.Code -> Surface(
            color = MaterialTheme.colorScheme.surfaceVariant,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                text = block.text,
                style = MaterialTheme.typography.bodySmall.copy(fontFamily = FontFamily.Monospace),
                softWrap = false,
                modifier = Modifier.horizontalScroll(rememberScrollState()).padding(12.dp),
            )
        }

        is MdBlock.Figure -> figures.firstOrNull { it.id == block.id }?.let { FigureCard(it) }

        MdBlock.Rule -> HorizontalDivider()
    }
}

/**
 * A table shown as one card per row, so it reads top to bottom on a narrow screen with no sideways scrolling.
 * The first cell is the row's title; the other cells follow with their column name in front.
 */
@Composable
private fun TableView(table: MdBlock.Table) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        for (row in table.rows) {
            Surface(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = MaterialTheme.shapes.small,
                modifier = Modifier.fillMaxWidth().semantics(mergeDescendants = true) {},
            ) {
                Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    InlineText(
                        spans = row.first().map { it.copy(bold = true) },
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.fillMaxWidth(),
                    )
                    row.drop(1).forEachIndexed { i, cell ->
                        if (cell.isEmpty() || cell.all { it.text.isBlank() }) return@forEachIndexed
                        val label = table.header.getOrNull(i + 1)?.joinToString("") { it.text }.orEmpty()
                        Row(modifier = Modifier.fillMaxWidth()) {
                            if (label.isNotBlank()) {
                                Text(
                                    text = "$label: ",
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontWeight = FontWeight.SemiBold,
                                )
                            }
                            InlineText(
                                spans = cell,
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier.weight(1f),
                            )
                        }
                    }
                }
            }
        }
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
        InlineText(
            spans = spans,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.weight(1f),
        )
    }
}

/** Colours for `==highlight==` text. Both pairs keep a contrast ratio above 7:1 between text and background. */
private val LightHighlightBackground = Color(0xFFFFE08A)
private val LightHighlightText = Color(0xFF1F1A00)
private val DarkHighlightBackground = Color(0xFF5C4A00)
private val DarkHighlightText = Color(0xFFFFE9A8)

/**
 * Text for parsed inline spans. Highlighted spans get a tinted background, bold weight and an underline, so the
 * highlight does not depend on colour alone. TalkBack reads each highlighted run as "highlighted, ..., end of
 * highlight".
 */
@Composable
private fun InlineText(spans: List<MdSpan>, style: TextStyle, modifier: Modifier = Modifier) {
    val dark = MaterialTheme.colorScheme.background.luminance() < 0.5f
    val background = if (dark) DarkHighlightBackground else LightHighlightBackground
    val foreground = if (dark) DarkHighlightText else LightHighlightText
    val spokenFormat = stringResource(R.string.md_highlight_spoken)
    val spoken = MdText.spoken(spans) { run -> String.format(spokenFormat, run) }
    val semanticsModifier = if (spoken != null) {
        Modifier.semantics { contentDescription = spoken }
    } else {
        Modifier
    }
    Text(
        text = toAnnotated(spans, background, foreground),
        style = style,
        modifier = modifier.then(semanticsModifier),
    )
}

private fun toAnnotated(spans: List<MdSpan>, highlightBackground: Color, highlightText: Color): AnnotatedString =
    buildAnnotatedString {
        for (span in spans) {
            val style = if (span.highlight) {
                SpanStyle(
                    color = highlightText,
                    background = highlightBackground,
                    fontWeight = FontWeight.Bold,
                    fontStyle = if (span.italic) FontStyle.Italic else null,
                    textDecoration = TextDecoration.Underline,
                )
            } else {
                SpanStyle(
                    fontWeight = if (span.bold) FontWeight.Bold else null,
                    fontStyle = if (span.italic) FontStyle.Italic else null,
                )
            }
            withStyle(style) { append(span.text) }
        }
    }
