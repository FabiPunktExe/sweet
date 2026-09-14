package de.fabiexe.sweet.foundation.layout

import androidx.compose.runtime.Composable
import de.fabiexe.sweet.ui.Alignment
import de.fabiexe.sweet.ui.Modifier

@Composable
expect fun Row(
    modifier: Modifier = Modifier,
    horizontalArrangement: Arrangement = Arrangement.Start,
    verticalAlignment: Alignment = Alignment.Start,
    content: @Composable () -> Unit
)