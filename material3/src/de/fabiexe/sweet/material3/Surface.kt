package de.fabiexe.sweet.material3

import androidx.compose.runtime.Composable
import de.fabiexe.sweet.ui.Modifier
import de.fabiexe.sweet.ui.graphics.Color

@Composable
expect fun Surface(
    modifier: Modifier = Modifier,
    color: Color = currentColorScheme().surface,
    contentColor: Color = currentColorScheme().onSurface,
    content: @Composable () -> Unit
)