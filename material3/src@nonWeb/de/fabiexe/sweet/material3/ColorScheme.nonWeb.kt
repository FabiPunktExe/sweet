package de.fabiexe.sweet.material3

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import de.fabiexe.sweet.ui.graphics.toAndroidxColor
import de.fabiexe.sweet.ui.graphics.toSweetColor
import androidx.compose.material3.ColorScheme as AndroidxColorScheme

@Composable
fun ColorScheme.toAndroidxColorScheme() = MaterialTheme.colorScheme.copy(
    primary = primary.toAndroidxColor(),
    onPrimary = onPrimary.toAndroidxColor(),
    primaryContainer = primaryContainer.toAndroidxColor(),
    onPrimaryContainer = onPrimaryContainer.toAndroidxColor(),
    secondary = secondary.toAndroidxColor(),
    onSecondary = onSecondary.toAndroidxColor(),
    secondaryContainer = secondaryContainer.toAndroidxColor(),
    onSecondaryContainer = onSecondaryContainer.toAndroidxColor(),
    tertiary = tertiary.toAndroidxColor(),
    onTertiary = onTertiary.toAndroidxColor(),
    tertiaryContainer = tertiaryContainer.toAndroidxColor(),
    onTertiaryContainer = onTertiaryContainer.toAndroidxColor(),
    error = error.toAndroidxColor(),
    onError = onError.toAndroidxColor(),
    errorContainer = errorContainer.toAndroidxColor(),
    onErrorContainer = onErrorContainer.toAndroidxColor(),
    surface = surface.toAndroidxColor(),
    onSurface = onSurface.toAndroidxColor(),
    surfaceVariant = surfaceVariant.toAndroidxColor(),
    onSurfaceVariant = onSurfaceVariant.toAndroidxColor(),
    outline = outline.toAndroidxColor(),
    outlineVariant = outlineVariant.toAndroidxColor()
)

fun AndroidxColorScheme.toSweetColorScheme() = ColorScheme(
    primary = primary.toSweetColor(),
    onPrimary = onPrimary.toSweetColor(),
    primaryContainer = primaryContainer.toSweetColor(),
    onPrimaryContainer = onPrimaryContainer.toSweetColor(),
    secondary = secondary.toSweetColor(),
    onSecondary = onSecondary.toSweetColor(),
    secondaryContainer = secondaryContainer.toSweetColor(),
    onSecondaryContainer = onSecondaryContainer.toSweetColor(),
    tertiary = tertiary.toSweetColor(),
    onTertiary = onTertiary.toSweetColor(),
    tertiaryContainer = tertiaryContainer.toSweetColor(),
    onTertiaryContainer = onTertiaryContainer.toSweetColor(),
    error = error.toSweetColor(),
    onError = onError.toSweetColor(),
    errorContainer = errorContainer.toSweetColor(),
    onErrorContainer = onErrorContainer.toSweetColor(),
    surface = surface.toSweetColor(),
    onSurface = onSurface.toSweetColor(),
    surfaceVariant = surfaceVariant.toSweetColor(),
    onSurfaceVariant = onSurfaceVariant.toSweetColor(),
    outline = outline.toSweetColor(),
    outlineVariant = outlineVariant.toSweetColor()
)