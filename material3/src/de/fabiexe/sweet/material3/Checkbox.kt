package de.fabiexe.sweet.material3

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import de.fabiexe.sweet.ui.Modifier
import de.fabiexe.sweet.ui.graphics.Color

@Immutable
data class CheckboxColors(
    val checkedColor: Color,
    val uncheckedColor: Color,
    val checkmarkColor: Color,
    val disabledCheckedColor: Color,
    val disabledUncheckedColor: Color,
    val disabledCheckmarkColor: Color
)

@Composable
expect fun Checkbox(
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    colors: CheckboxColors = CheckboxColors(
        checkedColor = currentColorScheme().primary,
        uncheckedColor = currentColorScheme().outline,
        checkmarkColor = currentColorScheme().onPrimary,
        disabledCheckedColor = currentColorScheme().onSurface.copy(alpha = 0.38f),
        disabledUncheckedColor = currentColorScheme().onSurface.copy(alpha = 0.38f),
        disabledCheckmarkColor = currentColorScheme().surface
    )
)