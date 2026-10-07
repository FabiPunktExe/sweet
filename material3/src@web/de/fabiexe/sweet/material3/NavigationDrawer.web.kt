package de.fabiexe.sweet.material3

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeNode
import de.fabiexe.sweet.foundation.layout.applyModifier
import de.fabiexe.sweet.foundation.layout.fillMaxSize
import de.fabiexe.sweet.ui.DomApplier
import de.fabiexe.sweet.ui.Modifier
import de.fabiexe.sweet.ui.graphics.Color
import de.fabiexe.sweet.ui.graphics.toCssString
import web.dom.document
import web.events.EventHandler
import web.html.HTMLDivElement

private const val drawerEasing = "cubic-bezier(0.2, 0, 0, 1)"
private const val drawerSlideTransition = "transform 250ms $drawerEasing"
private const val scrimFadeTransition = "opacity 250ms $drawerEasing"

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
    val drawerContainerColor = currentColorScheme().surfaceVariant

    ComposeNode<HTMLDivElement, DomApplier>(
        factory = {
            val element = document.createElement("div") as HTMLDivElement
            element.style.position = "relative"
            element.style.overflow = "hidden"
            element.applyModifier(Modifier.fillMaxSize(1f) then modifier)
            element
        },
        update = {
            set(modifier) { applyModifier(Modifier.fillMaxSize(1f) then it) }
        },
        content = {
            ComposeNode<HTMLDivElement, DomApplier>(
                factory = {
                    val element = document.createElement("div") as HTMLDivElement
                    element.style.width = "100%"
                    element.style.height = "100%"
                    element
                },
                update = {},
                content = content
            )

            ComposeNode<HTMLDivElement, DomApplier>(
                factory = {
                    val element = document.createElement("div") as HTMLDivElement
                    element.style.position = "absolute"
                    element.style.left = "0"
                    element.style.top = "0"
                    element.style.right = "0"
                    element.style.bottom = "0"
                    element.style.backgroundColor = scrimColor.toCssString()
                    element.style.opacity = if (open) "1" else "0"
                    element.style.pointerEvents = if (open) "auto" else "none"
                    element.style.transition = scrimFadeTransition
                    element
                },
                update = {
                    set(open) {
                        style.opacity = if (it) "1" else "0"
                        style.pointerEvents = if (it) "auto" else "none"
                    }
                    set(onClose) { handler ->
                        onclick = EventHandler { handler?.invoke() }
                    }
                    set(scrimColor) { style.backgroundColor = it.toCssString() }
                }
            )

            ComposeNode<HTMLDivElement, DomApplier>(
                factory = {
                    val element = document.createElement("div") as HTMLDivElement
                    element.style.position = "absolute"
                    element.style.left = "0"
                    element.style.top = "0"
                    element.style.bottom = "0"
                    element.style.width = "${width}px"
                    element.style.display = "flex"
                    element.style.flexDirection = "column"
                    element.style.borderRadius = "0 16px 16px 0"
                    element.style.backgroundColor = drawerContainerColor.toCssString()
                    element.style.transform = if (open) "translateX(0)" else "translateX(-100%)"
                    element.style.transition = drawerSlideTransition
                    element
                },
                update = {
                    set(open) { style.transform = if (it) "translateX(0)" else "translateX(-100%)" }
                    set(width) { style.width = "${it}px" }
                    set(drawerContainerColor) { style.backgroundColor = it.toCssString() }
                },
                content = drawerContent
            )
        }
    )
}

@Composable
actual fun PermanentNavigationDrawer(
    drawerContent: @Composable () -> Unit,
    modifier: Modifier,
    width: Float,
    content: @Composable () -> Unit
) {
    val drawerContainerColor = currentColorScheme().surfaceVariant

    ComposeNode<HTMLDivElement, DomApplier>(
        factory = {
            val element = document.createElement("div") as HTMLDivElement
            element.style.display = "flex"
            element.style.overflow = "hidden"
            element.applyModifier(Modifier.fillMaxSize(1f) then modifier)
            element
        },
        update = {
            set(modifier) { applyModifier(Modifier.fillMaxSize(1f) then it) }
        },
        content = {
            ComposeNode<HTMLDivElement, DomApplier>(
                factory = {
                    val element = document.createElement("div") as HTMLDivElement
                    element.style.width = "${width}px"
                    element.style.flexGrow = "0"
                    element.style.flexShrink = "0"
                    element.style.display = "flex"
                    element.style.flexDirection = "column"
                    element.style.backgroundColor = drawerContainerColor.toCssString()
                    element
                },
                update = {
                    set(width) { style.width = "${it}px" }
                    set(drawerContainerColor) { style.backgroundColor = it.toCssString() }
                },
                content = drawerContent
            )

            ComposeNode<HTMLDivElement, DomApplier>(
                factory = {
                    val element = document.createElement("div") as HTMLDivElement
                    element.style.flexGrow = "1"
                    element.style.flexShrink = "1"
                    element.style.flexBasis = "0%"
                    element.style.minWidth = "0"
                    element
                },
                update = {},
                content = content
            )
        }
    )
}