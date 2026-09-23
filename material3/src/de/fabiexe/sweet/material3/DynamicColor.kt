package de.fabiexe.sweet.material3

import com.materialkolor.hct.Hct
import com.materialkolor.scheme.SchemeTonalSpot
import de.fabiexe.sweet.ui.graphics.Color

/**
 * Builds a Material 3 color scheme from a seed color.
 *
 * @param seedColor The seed color.
 * @param isDark Whether the color scheme should be dark or light.
 * @return The generated color scheme.
 */
fun colorScheme(seedColor: Color, isDark: Boolean = false): ColorScheme {
    val scheme = SchemeTonalSpot(
        sourceColorHct = Hct.fromInt(seedColor.value or 0xFF000000.toInt()),
        isDark = isDark,
        contrastLevel = 0.0
    )

    return ColorScheme(
        // Primary
        primary = Color(scheme.primary),
        onPrimary = Color(scheme.onPrimary),
        primaryContainer = Color(scheme.primaryContainer),
        onPrimaryContainer = Color(scheme.onPrimaryContainer),

        // Secondary
        secondary = Color(scheme.secondary),
        onSecondary = Color(scheme.onSecondary),
        secondaryContainer = Color(scheme.secondaryContainer),
        onSecondaryContainer = Color(scheme.onSecondaryContainer),

        // Tertiary
        tertiary = Color(scheme.tertiary),
        onTertiary = Color(scheme.onTertiary),
        tertiaryContainer = Color(scheme.tertiaryContainer),
        onTertiaryContainer = Color(scheme.onTertiaryContainer),

        // Error
        error = Color(scheme.error),
        onError = Color(scheme.onError),
        errorContainer = Color(scheme.errorContainer),
        onErrorContainer = Color(scheme.onErrorContainer),

        // Surface
        surface = Color(scheme.surface),
        onSurface = Color(scheme.onSurface),
        surfaceVariant = Color(scheme.surfaceVariant),
        onSurfaceVariant = Color(scheme.onSurfaceVariant),

        // Outline
        outline = Color(scheme.outline),
        outlineVariant = Color(scheme.outlineVariant)
    )
}