package de.fabiexe.sweet.material3

import androidx.compose.runtime.Composable
import de.fabiexe.sweet.ui.Modifier
import de.fabiexe.sweet.ui.graphics.Color
import de.fabiexe.sweet.ui.graphics.toAndroidxColor
import de.fabiexe.sweet.ui.toAndroidxModifier
import androidx.compose.material3.Surface as AndroidxSurface

@Composable
actual fun Surface(
    modifier: Modifier,
    color: Color,
    contentColor: Color,
    content: @Composable () -> Unit
) {
    AndroidxSurface(
        modifier = modifier.toAndroidxModifier(),
        color = color.toAndroidxColor(),
        contentColor = contentColor.toAndroidxColor(),
        content = content
    )
}