package de.fabiexe.sweet.foundation.layout

import androidx.compose.runtime.Composable
import de.fabiexe.sweet.ui.Alignment
import de.fabiexe.sweet.ui.Modifier

@Composable
expect fun Box(
    modifier: Modifier = Modifier,
    horizontalAlignment: Alignment = Alignment.Start,
    verticalAlignment: Alignment = Alignment.Start,
    content: @Composable () -> Unit
)