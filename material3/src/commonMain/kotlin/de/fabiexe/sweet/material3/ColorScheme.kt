package de.fabiexe.sweet.material3

import androidx.compose.runtime.Immutable
import de.fabiexe.sweet.ui.graphics.Color

@Immutable
data class ColorScheme(
    // Primary
    val primary: Color = Color(0xFF6750A4),
    val onPrimary: Color = Color(0xFFFFFFFF),
    val primaryContainer: Color = Color(0xFFEADDFF),
    val onPrimaryContainer: Color = Color(0xFF4F378B),

    // Secondary
    val secondary: Color = Color(0xFF625B71),
    val onSecondary: Color = Color(0xFFFFFFFF),
    val secondaryContainer: Color = Color(0xFFE8DEF8),
    val onSecondaryContainer: Color = Color(0xFF484458),

    // Tertiary
    val tertiary: Color = Color(0xFF7D5260),
    val onTertiary: Color = Color(0xFFFFFFFF),
    val tertiaryContainer: Color = Color(0xFFFFD8E4),
    val onTertiaryContainer: Color = Color(0xFF633B48),

    // Error
    val error: Color = Color(0xFFB3261E),
    val onError: Color = Color(0xFFFFFFFF),
    val errorContainer: Color = Color(0xFFF9DEDC),
    val onErrorContainer: Color = Color(0xFF8C1D18),

    // Surface
    val surface: Color = Color(0xFFFEF7FF),
    val onSurface: Color = Color(0xFF1D1B20),
    val surfaceVariant: Color = Color(0xFFE7E0EC),
    val onSurfaceVariant: Color = Color(0xFF49454F),

    // Outline
    val outline: Color = Color(0xFF79747E),
    val outlineVariant: Color = Color(0xFFCAC4D0)
)