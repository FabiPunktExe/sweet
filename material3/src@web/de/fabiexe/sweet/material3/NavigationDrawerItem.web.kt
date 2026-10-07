package de.fabiexe.sweet.material3

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeNode
import androidx.compose.runtime.CompositionLocalProvider
import de.fabiexe.sweet.foundation.layout.*
import de.fabiexe.sweet.ui.Alignment
import de.fabiexe.sweet.ui.DomApplier
import de.fabiexe.sweet.ui.Modifier
import de.fabiexe.sweet.ui.graphics.Color
import de.fabiexe.sweet.ui.graphics.toCssString
import de.fabiexe.sweet.ui.input.pointer.PointerIcon
import de.fabiexe.sweet.ui.text.font.FontWeight
import web.dom.document
import web.events.EventHandler
import web.html.HTMLButtonElement

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
    val containerColor = if (selected) colorScheme.secondaryContainer else Color(0x00000000)
    val contentColor = if (selected) colorScheme.onSecondaryContainer else colorScheme.onSurfaceVariant
    val rippleColor = contentColor.copy(alpha = 0.12f)
    val baseModifier = Modifier.fillMaxWidth(1f).padding(left = 12f, right = 12f) then modifier

    ComposeNode<HTMLButtonElement, DomApplier>(
        factory = {
            val element = document.createElement("button") as HTMLButtonElement
            element.style.border = "none"
            element.style.outline = "none"
            element.style.position = "relative"
            element.style.overflow = "hidden"
            element.style.fontFamily = "inherit"
            element.style.cursor = "pointer"
            element.style.borderRadius = "100vh"
            element.style.backgroundColor = containerColor.toCssString()
            element.applyModifier(baseModifier)
            element.applyPointerHoverIcon(baseModifier, PointerIcon.Hand)
            element.onclick = EventHandler { onClick() }
            element.applyRippleEventHandlers()
            element
        },
        update = {
            set(onClick) { onclick = EventHandler(it) }
            set(modifier) {
                applyModifier(baseModifier)
                applyPointerHoverIcon(baseModifier, PointerIcon.Hand)
            }
            set(selected) {
                style.backgroundColor = (if (it) colorScheme.secondaryContainer else Color(0x00000000)).toCssString()
            }
            set(containerColor) { style.backgroundColor = it.toCssString() }
            set(rippleColor) {
                onpointerdown = EventHandler { event -> createRipple(it.toCssString(), event) }
            }
        }
    ) {
        CompositionLocalProvider(
            LocalContentColor provides contentColor,
            LocalFontWeight provides FontWeight.Medium
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .height(minHeight = 56f, maxHeight = null)
                    .padding(horizontal = 16f),
                horizontalArrangement = Arrangement.Start.spacedBy(12f),
                verticalAlignment = Alignment.Center
            ) {
                if (icon != null) {
                    Box(
                        modifier = Modifier.size(24f),
                        horizontalAlignment = Alignment.Center,
                        verticalAlignment = Alignment.Center,
                        content = icon
                    )
                }
                Box(
                    modifier = Modifier.fillMaxWidth(1f),
                    verticalAlignment = Alignment.Center,
                    content = label
                )
                if (badge != null) {
                    Box(content = badge)
                }
            }
        }
    }
}