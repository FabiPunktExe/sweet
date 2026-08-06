package de.fabiexe.sweet.material3

import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import de.fabiexe.sweet.foundation.layout.toAndroidxModifier
import de.fabiexe.sweet.ui.Modifier

@Composable
actual fun TextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    readOnly: Boolean,
    label: @Composable (() -> Unit)?,
    singleLine: Boolean
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.toAndroidxModifier(),
        enabled = enabled,
        readOnly = readOnly,
        singleLine = singleLine,
        label = label
    )
}