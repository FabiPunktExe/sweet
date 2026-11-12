package de.fabiexe.sweet.material3

import androidx.compose.material3.CheckboxDefaults
import androidx.compose.runtime.Composable
import de.fabiexe.sweet.foundation.layout.toAndroidxModifier
import de.fabiexe.sweet.ui.Modifier
import de.fabiexe.sweet.ui.graphics.toAndroidxColor
import androidx.compose.material3.Checkbox as AndroidxCheckbox
import androidx.compose.material3.CheckboxColors as AndroidxCheckboxColors

@Composable
fun CheckboxColors.toAndroidxCheckboxColors(): AndroidxCheckboxColors = CheckboxDefaults.colors(
    checkedCheckmarkColor = checkmarkColor.toAndroidxColor(),
    disabledCheckmarkColor = disabledCheckmarkColor.toAndroidxColor(),
    checkedBoxColor = checkedColor.toAndroidxColor(),
    disabledCheckedBoxColor = disabledCheckedColor.toAndroidxColor(),
    checkedBorderColor = checkedColor.toAndroidxColor(),
    uncheckedBorderColor = uncheckedColor.toAndroidxColor(),
    disabledBorderColor = disabledCheckedColor.toAndroidxColor(),
    disabledUncheckedBorderColor = disabledUncheckedColor.toAndroidxColor()
)

@Composable
actual fun Checkbox(
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
    modifier: Modifier,
    enabled: Boolean,
    colors: CheckboxColors
) {
    AndroidxCheckbox(
        checked = checked,
        onCheckedChange = onCheckedChange,
        modifier = modifier.toAndroidxModifier(),
        enabled = enabled,
        colors = colors.toAndroidxCheckboxColors()
    )
}