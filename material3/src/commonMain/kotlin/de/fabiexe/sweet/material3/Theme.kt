package de.fabiexe.sweet.material3

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf

val LocalColorScheme = compositionLocalOf { ColorScheme() }

@Composable
fun SweetTheme(
    colorScheme: ColorScheme = ColorScheme(),
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(LocalColorScheme provides colorScheme, content)
}