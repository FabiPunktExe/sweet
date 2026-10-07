package de.fabiexe.sweet.material3

import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.runtime.Composable
import de.fabiexe.sweet.foundation.layout.toAndroidxModifier
import de.fabiexe.sweet.ui.Modifier
import de.fabiexe.sweet.ui.graphics.Color
import de.fabiexe.sweet.ui.graphics.toAndroidxColor
import androidx.compose.material3.NavigationDrawerItem as AndroidxNavigationDrawerItem

@Composable
actual fun NavigationDrawerItem(
    label: @Composable () -> Unit,
    selected: Boolean,
    modifier: Modifier,
    icon: (@Composable () -> Unit)?,
    badge: (@Composable () -> Unit)?,
    onClick: () -> Unit
) {
    val colorScheme = currentColorScheme()

    AndroidxNavigationDrawerItem(
        selected = selected,
        onClick = onClick,
        label = label,
        modifier = modifier.toAndroidxModifier(),
        icon = icon,
        badge = badge,
        colors = NavigationDrawerItemDefaults.colors(
            selectedContainerColor = colorScheme.secondaryContainer.toAndroidxColor(),
            unselectedContainerColor = Color(0x00000000).toAndroidxColor(),
            selectedIconColor = colorScheme.onSecondaryContainer.toAndroidxColor(),
            unselectedIconColor = colorScheme.onSurfaceVariant.toAndroidxColor(),
            selectedTextColor = colorScheme.onSecondaryContainer.toAndroidxColor(),
            unselectedTextColor = colorScheme.onSurfaceVariant.toAndroidxColor()
        )
    )
}