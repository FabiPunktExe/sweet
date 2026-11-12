package de.fabiexe.sweet.material3

import androidx.compose.runtime.Composable
import de.fabiexe.sweet.foundation.layout.toAndroidxModifier
import de.fabiexe.sweet.ui.Modifier
import de.fabiexe.sweet.ui.graphics.toAndroidxColor
import androidx.compose.material3.AlertDialog as AndroidxAlertDialog
import androidx.compose.ui.window.DialogProperties as AndroidxDialogProperties

@Composable
actual fun AlertDialog(
    onDismissRequest: () -> Unit,
    confirmButton: @Composable () -> Unit,
    modifier: Modifier,
    dismissButton: @Composable (() -> Unit)?,
    icon: @Composable (() -> Unit)?,
    title: @Composable (() -> Unit)?,
    text: @Composable (() -> Unit)?,
    properties: DialogProperties
) {
    AndroidxAlertDialog(
        onDismissRequest = onDismissRequest,
        confirmButton = confirmButton,
        modifier = modifier.toAndroidxModifier(),
        dismissButton = dismissButton,
        icon = icon,
        title = title,
        text = text,
        containerColor = currentColorScheme().surface.toAndroidxColor(),
        properties = AndroidxDialogProperties(
            dismissOnBackPress = properties.dismissOnBackPress,
            dismissOnClickOutside = properties.dismissOnClickOutside,
            usePlatformDefaultWidth = properties.usePlatformDefaultWidth
        )
    )
}