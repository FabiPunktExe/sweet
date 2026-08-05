package de.fabiexe.sweet.material3

import androidx.compose.runtime.Composable
import de.fabiexe.sweet.ui.Modifier

@Composable
expect fun Text(
    text: String,
    modifier: Modifier = Modifier
)