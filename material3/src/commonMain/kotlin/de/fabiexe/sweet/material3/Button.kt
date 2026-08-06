package de.fabiexe.sweet.material3

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import de.fabiexe.sweet.foundation.layout.PaddingValues
import de.fabiexe.sweet.ui.Modifier
import de.fabiexe.sweet.ui.graphics.Color

enum class ButtonSize(val height: Float, val contentPadding: PaddingValues, val squareCornerRadius: Float) {
    ExtraSmall(32f, PaddingValues(12f, 6f), 12f),
    Small(40f, PaddingValues(16f, 10f), 12f),
    Medium(56f, PaddingValues(24f, 16f), 16f),
    Large(96f, PaddingValues(48f, 32f), 28f),
    ExtraLarge(136f, PaddingValues(64f, 48f), 28f)
}

enum class ButtonShape {
    Round, Square
}

@Immutable
data class ButtonColors(
    val containerColor: Color,
    val contentColor: Color,
    val disabledContainerColor: Color,
    val disabledContentColor: Color
)

@Composable
expect fun Button(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    size: ButtonSize = ButtonSize.Small,
    shape: ButtonShape = ButtonShape.Round,
    colors: ButtonColors = ButtonColors(
        containerColor = currentColorScheme().primary,
        contentColor = currentColorScheme().onPrimary,
        disabledContainerColor = currentColorScheme().onSurface.copy(alpha = 0.1f),
        disabledContentColor = currentColorScheme().onSurface.copy(alpha = 0.38f)
    ),
    content: @Composable () -> Unit
)