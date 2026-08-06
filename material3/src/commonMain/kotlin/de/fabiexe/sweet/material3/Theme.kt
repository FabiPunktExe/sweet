package de.fabiexe.sweet.material3

import androidx.compose.runtime.Composable
import de.fabiexe.sweet.ui.graphics.Color

@Composable
expect fun currentColorScheme(): ColorScheme

@Composable
expect fun currentContentColor(): Color

@Composable
expect fun Theme(
    colorScheme: ColorScheme = ColorScheme(),
    content: @Composable () -> Unit
)