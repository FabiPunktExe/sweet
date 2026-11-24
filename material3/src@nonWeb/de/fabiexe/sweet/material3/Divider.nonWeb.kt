package de.fabiexe.sweet.material3

import androidx.compose.material3.HorizontalDivider as AndroidxHorizontalDivider
import androidx.compose.material3.VerticalDivider as AndroidxVerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import de.fabiexe.sweet.ui.Modifier
import de.fabiexe.sweet.ui.graphics.Color
import de.fabiexe.sweet.ui.graphics.toAndroidxColor
import de.fabiexe.sweet.ui.toAndroidxModifier

@Composable
actual fun HorizontalDivider(modifier: Modifier, thickness: Float, color: Color) {
    AndroidxHorizontalDivider(
        modifier = modifier.toAndroidxModifier(),
        thickness = thickness.dp,
        color = color.toAndroidxColor()
    )
}

@Composable
actual fun VerticalDivider(modifier: Modifier, thickness: Float, color: Color) {
    AndroidxVerticalDivider(
        modifier = modifier.toAndroidxModifier(),
        thickness = thickness.dp,
        color = color.toAndroidxColor()
    )
}