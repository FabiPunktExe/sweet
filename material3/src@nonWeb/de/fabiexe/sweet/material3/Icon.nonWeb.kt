package de.fabiexe.sweet.material3

import androidx.compose.runtime.Composable
import de.fabiexe.sweet.foundation.layout.toAndroidxModifier
import de.fabiexe.sweet.ui.Modifier
import de.fabiexe.sweet.ui.graphics.Color
import de.fabiexe.sweet.ui.graphics.toAndroidxColor
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import androidx.compose.material3.Icon as AndroidxIcon

@Composable
actual fun Icon(
    resource: DrawableResource,
    contentDescription: String?,
    modifier: Modifier,
    tint: Color
) {
    AndroidxIcon(
        painter = painterResource(resource),
        contentDescription = contentDescription,
        modifier = modifier
            .withDefaultIconSize()
            .toAndroidxModifier(),
        tint = tint.toAndroidxColor()
    )
}
