package de.fabiexe.sweet.material3

import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import de.fabiexe.sweet.ui.graphics.Color
import de.fabiexe.sweet.ui.graphics.toSweetColor

@Composable
actual fun currentColorScheme(): ColorScheme = MaterialTheme.colorScheme.toSweetColorScheme()

@Composable
actual fun currentContentColor(): Color = LocalContentColor.current.toSweetColor()

@Composable
actual fun Theme(
    colorScheme: ColorScheme,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = colorScheme.toAndroidxColorScheme(),
        content = content
    )
}