package de.fabiexe.sweet.material3

import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.ToggleButtonDefaults
import androidx.compose.runtime.Composable
import de.fabiexe.sweet.ui.Modifier
import de.fabiexe.sweet.foundation.layout.toAndroidxModifier
import de.fabiexe.sweet.ui.graphics.toAndroidxColor
import androidx.compose.material3.Button as AndroidxButton
import androidx.compose.material3.ButtonColors as AndroidxButtonColors

fun ButtonColors.toAndroidxButtonColors() = AndroidxButtonColors(
    containerColor = containerColor.toAndroidxColor(),
    contentColor = contentColor.toAndroidxColor(),
    disabledContainerColor = disabledContainerColor.toAndroidxColor(),
    disabledContentColor = disabledContentColor.toAndroidxColor()
)

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
actual fun Button(
    onClick: () -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    size: ButtonSize,
    shape: ButtonShape,
    colors: ButtonColors,
    content: @Composable () -> Unit
) {
    AndroidxButton(
        onClick = onClick,
        modifier = modifier.toAndroidxModifier(),
        enabled = enabled,
        shape = if (shape == ButtonShape.Round) {
            ButtonDefaults.shape
        } else {
            when (size) {
                ButtonSize.ExtraSmall -> ToggleButtonDefaults.extraSmallSquareShape
                ButtonSize.Small -> ToggleButtonDefaults.squareShape
                ButtonSize.Medium -> ToggleButtonDefaults.mediumSquareShape
                ButtonSize.Large -> ToggleButtonDefaults.largeSquareShape
                ButtonSize.ExtraLarge -> ToggleButtonDefaults.extraLargeSquareShape
            }
        },
        colors = colors.toAndroidxButtonColors(),
        contentPadding = when (size) {
            ButtonSize.ExtraSmall -> ButtonDefaults.ExtraSmallContentPadding
            ButtonSize.Small -> ButtonDefaults.SmallContentPadding
            ButtonSize.Medium -> ButtonDefaults.MediumContentPadding
            ButtonSize.Large -> ButtonDefaults.LargeContentPadding
            ButtonSize.ExtraLarge -> ButtonDefaults.ExtraLargeContentPadding
        }
    ) {
        content()
    }
}