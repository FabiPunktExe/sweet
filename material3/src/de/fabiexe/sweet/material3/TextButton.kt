package de.fabiexe.sweet.material3

import androidx.compose.runtime.Composable
import de.fabiexe.sweet.ui.Modifier
import de.fabiexe.sweet.ui.graphics.Color

@Composable
expect fun TextButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    size: ButtonSize = ButtonSize.Small,
    shape: ButtonShape = ButtonShape.Round,
    colors: ButtonColors = ButtonColors(
        containerColor = Color(0x00000000),
        contentColor = currentColorScheme().primary,
        disabledContainerColor = Color(0x00000000),
        disabledContentColor = currentColorScheme().onSurface.copy(alpha = 0.38f)
    ),
    content: @Composable () -> Unit
)
