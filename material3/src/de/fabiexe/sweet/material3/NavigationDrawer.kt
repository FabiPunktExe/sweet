package de.fabiexe.sweet.material3

import androidx.compose.runtime.Composable
import de.fabiexe.sweet.ui.Modifier
import de.fabiexe.sweet.ui.graphics.Color

@Composable
expect fun ModalNavigationDrawer(
    drawerContent: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    open: Boolean = false,
    onClose: (() -> Unit)? = null,
    scrimColor: Color = currentColorScheme().onSurface.copy(alpha = 0.32f),
    width: Float = 360f,
    content: @Composable () -> Unit
)

@Composable
expect fun PermanentNavigationDrawer(
    drawerContent: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    width: Float = 360f,
    content: @Composable () -> Unit
)