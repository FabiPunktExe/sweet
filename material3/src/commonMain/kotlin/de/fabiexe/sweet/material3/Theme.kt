package de.fabiexe.sweet.material3

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import de.fabiexe.sweet.ui.graphics.Color

val LocalColorScheme = compositionLocalOf { ColorScheme() }
val LocalContentColor = compositionLocalOf<Color> { error("not provided") }

@Composable
fun SweetTheme(
    colorScheme: ColorScheme = ColorScheme(),
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(LocalColorScheme provides colorScheme, content)
}