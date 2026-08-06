package de.fabiexe.sweet.material3

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import de.fabiexe.sweet.ui.graphics.Color
import de.fabiexe.sweet.ui.text.font.FontWeight

val LocalColorScheme = compositionLocalOf { ColorScheme() }
val LocalContentColor = compositionLocalOf<Color> { error("not provided") }
val LocalFontWeight = compositionLocalOf<FontWeight> { error("not provided") }

@Composable
actual fun currentColorScheme(): ColorScheme = LocalColorScheme.current

@Composable
actual fun currentContentColor(): Color = LocalContentColor.current

@Composable
actual fun Theme(
    colorScheme: ColorScheme,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalColorScheme provides colorScheme,
        LocalContentColor provides colorScheme.onSurface,
        LocalFontWeight provides FontWeight.Normal,
        content = content
    )
}