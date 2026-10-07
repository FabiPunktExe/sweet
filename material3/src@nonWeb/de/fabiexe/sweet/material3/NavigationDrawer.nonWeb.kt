package de.fabiexe.sweet.material3

import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.*
import de.fabiexe.sweet.foundation.layout.toAndroidxModifier
import de.fabiexe.sweet.foundation.layout.width
import de.fabiexe.sweet.ui.Modifier
import de.fabiexe.sweet.ui.graphics.Color
import de.fabiexe.sweet.ui.graphics.toAndroidxColor
import androidx.compose.material3.ModalNavigationDrawer as AndroidxModalNavigationDrawer
import androidx.compose.material3.PermanentNavigationDrawer as AndroidxPermanentNavigationDrawer

@Composable
actual fun ModalNavigationDrawer(
    drawerContent: @Composable () -> Unit,
    modifier: Modifier,
    open: Boolean,
    onClose: (() -> Unit)?,
    scrimColor: Color,
    width: Float,
    content: @Composable () -> Unit
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val currentOpen by rememberUpdatedState(open)
    val currentOnClose by rememberUpdatedState(onClose)

    LaunchedEffect(Unit) {
        snapshotFlow { drawerState.targetValue }
            .collect { target ->
                if (target == DrawerValue.Closed && currentOpen) {
                    currentOnClose?.invoke()
                }
            }
    }

    LaunchedEffect(open) {
        if (open) {
            drawerState.open()
        } else {
            drawerState.close()
        }
    }

    AndroidxModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier
                    .width(width)
                    .toAndroidxModifier(),
                drawerContainerColor = currentColorScheme().surfaceVariant.toAndroidxColor()
            ) {
                drawerContent()
            }
        },
        modifier = modifier.toAndroidxModifier(),
        drawerState = drawerState,
        scrimColor = scrimColor.toAndroidxColor(),
        content = content
    )
}

@Composable
actual fun PermanentNavigationDrawer(
    drawerContent: @Composable () -> Unit,
    modifier: Modifier,
    width: Float,
    content: @Composable () -> Unit
) {
    AndroidxPermanentNavigationDrawer(
        drawerContent = {
            PermanentDrawerSheet(
                modifier = Modifier
                    .width(width)
                    .toAndroidxModifier(),
                drawerContainerColor = currentColorScheme().surfaceVariant.toAndroidxColor()
            ) {
                drawerContent()
            }
        },
        modifier = modifier.toAndroidxModifier(),
        content = content
    )
}