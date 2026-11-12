package de.fabiexe.sweet.material3

import androidx.compose.runtime.Composable
import de.fabiexe.sweet.ui.Modifier
import de.fabiexe.sweet.ui.graphics.Color
import de.fabiexe.sweet.ui.text.TextStyle

@Composable
expect fun Text(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = currentContentColor(),
    style: TextStyle? = null
)