package de.fabiexe.sweet.material3

import androidx.compose.runtime.Composable
import de.fabiexe.sweet.ui.Modifier
import de.fabiexe.sweet.ui.graphics.Color

@Composable
expect fun HorizontalDivider(
    modifier: Modifier = Modifier,
    thickness: Float = 1f,
    color: Color = currentColorScheme().outlineVariant
)

@Composable
expect fun VerticalDivider(
    modifier: Modifier = Modifier,
    thickness: Float = 1f,
    color: Color = currentColorScheme().outlineVariant
)