package ca.nauticlebc.app.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val Navy = Color(0xFF0B3C5D)
private val Teal = Color(0xFF1B7F8C)

private val LightColors = lightColorScheme(
    primary = Navy,
    secondary = Teal,
)

private val DarkColors = darkColorScheme(
    primary = Color(0xFF8FC7E8),
    secondary = Color(0xFF7FD0DA),
)

@Composable
fun NauticleTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColors else LightColors,
        content = content,
    )
}
