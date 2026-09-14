package de.fabiexe.sweet.foundation.layout

import androidx.compose.runtime.Composable
import de.fabiexe.sweet.ui.Alignment
import de.fabiexe.sweet.ui.Modifier

@Composable
expect fun Column(
    modifier: Modifier = Modifier,
    verticalArrangement: Arrangement = Arrangement.Start,
    horizontalAlignment: Alignment = Alignment.Start,
    content: @Composable () -> Unit
)