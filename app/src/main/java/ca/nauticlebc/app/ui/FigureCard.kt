package ca.nauticlebc.app.ui

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ca.nauticlebc.app.R
import ca.nauticlebc.app.content.Figure
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * A picture inside a lesson, closed until the learner taps it so the lesson stays calm to read.
 * The drawing sits on a white panel in every theme, and its description is what TalkBack announces.
 */
@Composable
fun FigureCard(figure: Figure, modifier: Modifier = Modifier) {
    var open by rememberSaveable(figure.id) { mutableStateOf(false) }
    OutlinedCard(modifier = modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(8.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            TextButton(onClick = { open = !open }) {
                Text(
                    text = stringResource(if (open) R.string.figure_hide else R.string.figure_show, figure.caption),
                    style = MaterialTheme.typography.titleSmall,
                )
            }
            if (open) {
                val context = LocalContext.current
                val bitmap by produceState<androidx.compose.ui.graphics.ImageBitmap?>(null, figure.id) {
                    value = withContext(Dispatchers.IO) {
                        runCatching {
                            context.applicationContext.assets.open("content/figures/${figure.id}.png")
                                .use { BitmapFactory.decodeStream(it) }?.asImageBitmap()
                        }.getOrNull()
                    }
                }
                val loaded = bitmap
                if (loaded != null) {
                    Surface(color = Color.White, shape = MaterialTheme.shapes.small, modifier = Modifier.fillMaxWidth()) {
                        Image(
                            bitmap = loaded,
                            contentDescription = figure.alt,
                            contentScale = ContentScale.FillWidth,
                            modifier = Modifier.fillMaxWidth().padding(4.dp),
                        )
                    }
                    Text(text = figure.alt, style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(horizontal = 8.dp))
                } else {
                    Text(text = stringResource(R.string.figure_missing), style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
    }
}
