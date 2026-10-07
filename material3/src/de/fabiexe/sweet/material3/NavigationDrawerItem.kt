package de.fabiexe.sweet.material3

import androidx.compose.runtime.Composable
import de.fabiexe.sweet.ui.Modifier

@Composable
expect fun NavigationDrawerItem(
    label: @Composable () -> Unit,
    selected: Boolean = false,
    modifier: Modifier = Modifier,
    icon: (@Composable () -> Unit)? = null,
    badge: (@Composable () -> Unit)? = null,
    onClick: () -> Unit = {}
)