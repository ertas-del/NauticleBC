package ca.nauticlebc.app.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Navy = Color(0xFF0B3C5D)
private val BarNavy = Color(0xFF07283F)
private val Ink = Color(0xFF13232E)
private val Sea = Color(0xFF1B7F8C)
private val Foam = Color(0xFFF7FBFC)
private val Mist = Color(0xFFE5F0F4)
private val Brass = Color(0xFF8C6A32)
private val BrassSoft = Color(0xFFF4E7C8)

private val LightColors = lightColorScheme(
    primary = Navy,
    onPrimary = Color.White,
    primaryContainer = Color(0xFFD6E8F4),
    onPrimaryContainer = BarNavy,
    secondary = Sea,
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFD3F1F4),
    onSecondaryContainer = Color(0xFF073238),
    tertiary = Brass,
    onTertiary = Color.White,
    tertiaryContainer = BrassSoft,
    onTertiaryContainer = Color(0xFF3D2B0B),
    background = Mist,
    onBackground = Ink,
    surface = Foam,
    onSurface = Ink,
    surfaceVariant = Color(0xFFD7E5EB),
    onSurfaceVariant = Color(0xFF3C5160),
    outline = Color(0xFF7C929E),
    error = Color(0xFF9C2F2F),
    onError = Color.White,
    errorContainer = Color(0xFFF8D6D2),
    onErrorContainer = Color(0xFF3F0A0A),
)

private val Night = Color(0xFF071820)
private val NightCard = Color(0xFF102833)

private val DarkColors = darkColorScheme(
    primary = Color(0xFF9FD0EA),
    onPrimary = BarNavy,
    primaryContainer = Color(0xFF13425F),
    onPrimaryContainer = Color(0xFFD6E8F4),
    secondary = Color(0xFF8FDBE2),
    onSecondary = Color(0xFF00363B),
    secondaryContainer = Color(0xFF0E4A52),
    onSecondaryContainer = Color(0xFFD3F1F4),
    tertiary = Color(0xFFE4C98A),
    onTertiary = Color(0xFF3D2B0B),
    tertiaryContainer = Color(0xFF5A4520),
    onTertiaryContainer = BrassSoft,
    background = Night,
    onBackground = Color(0xFFE6F1F5),
    surface = NightCard,
    onSurface = Color(0xFFE6F1F5),
    surfaceVariant = Color(0xFF1C3A48),
    onSurfaceVariant = Color(0xFFC5D6DE),
    outline = Color(0xFF8AA0AB),
    error = Color(0xFFFFB4AB),
    errorContainer = Color(0xFF6B2420),
    onErrorContainer = Color(0xFFFFDAD6),
)

private val NauticalShapes = Shapes(
    extraSmall = RoundedCornerShape(8.dp),
    small = RoundedCornerShape(12.dp),
    medium = RoundedCornerShape(20.dp),
    large = RoundedCornerShape(28.dp),
    extraLarge = RoundedCornerShape(32.dp),
)

private val NauticalType = Typography().let { type ->
    type.copy(
        displayLarge = type.displayLarge.copy(fontWeight = FontWeight.SemiBold),
        headlineMedium = type.headlineMedium.copy(fontWeight = FontWeight.SemiBold),
        titleLarge = type.titleLarge.copy(fontWeight = FontWeight.Medium),
        bodyLarge = type.bodyLarge.copy(lineHeight = 28.sp),
        bodyMedium = type.bodyMedium.copy(lineHeight = 24.sp),
    )
}

/** Navy bar used on every screen, in both light and dark. */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun nauticalTopAppBarColors(): TopAppBarColors = TopAppBarDefaults.topAppBarColors(
    containerColor = BarNavy,
    scrolledContainerColor = BarNavy,
    titleContentColor = Color.White,
    navigationIconContentColor = Color.White,
    actionIconContentColor = Color.White,
)

@Composable
fun NauticleTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColors else LightColors,
        shapes = NauticalShapes,
        typography = NauticalType,
        content = content,
    )
}
