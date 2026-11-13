package de.fabiexe.sweet.material3

import androidx.compose.runtime.*
import de.fabiexe.sweet.foundation.layout.*
import de.fabiexe.sweet.ui.Alignment
import de.fabiexe.sweet.ui.DomApplier
import de.fabiexe.sweet.ui.Modifier
import de.fabiexe.sweet.ui.graphics.toCssString
import de.fabiexe.sweet.ui.input.pointer.PointerIcon
import de.fabiexe.sweet.ui.text.font.FontWeight
import web.dom.document
import web.events.EventHandler
import web.html.HTMLButtonElement

@Composable
actual fun TextButton(
    onClick: () -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    size: ButtonSize,
    shape: ButtonShape,
    colors: ButtonColors,
    content: @Composable () -> Unit
) {
    var hovered by remember { mutableStateOf(false) }
    val contentColor = if (enabled) colors.contentColor else colors.disabledContentColor
    val backgroundColor = buttonBackgroundColor(enabled, colors, hovered)
    val rippleColor = contentColor.copy(alpha = 0.12f)

    InteractiveComponent {
        ComposeNode<HTMLButtonElement, DomApplier>(
            factory = {
                val element = document.createElement("button") as HTMLButtonElement
                element.style.border = "none"
                element.style.outline = "none"
                element.style.position = "relative"
                element.style.overflow = "hidden"
                element.style.fontFamily = "inherit"
                element.applyModifier(modifier)
                element.applyPointerHoverIcon(modifier, PointerIcon.Default)
                element.onclick = EventHandler { onClick() }
                element.applyRippleEventHandlers()
                element.onmouseenter = EventHandler { hovered = true }
                element.onmouseleave = EventHandler { hovered = false }
                element.onpointerdown = EventHandler { event -> element.createRipple(rippleColor.toCssString(), event) }
                element.disabled = !enabled
                element.style.transition = buttonTransition
                element.style.height = "${size.height}px"
                element.style.borderRadius = if (shape == ButtonShape.Round) {
                    "100vh"
                } else {
                    "${size.squareCornerRadius}px"
                }
                element.style.backgroundColor = backgroundColor.toCssString()
                element
            },
            update = {
                set(onClick) { onclick = EventHandler(it) }
                set(modifier) {
                    applyModifier(it)
                    applyPointerHoverIcon(it, PointerIcon.Default)
                }
                set(size) {
                    style.height = "${it.height}px"
                    style.borderRadius = if (shape == ButtonShape.Round) {
                        "100vh"
                    } else {
                        "${it.squareCornerRadius}px"
                    }
                }
                set(shape) {
                    style.borderRadius = if (it == ButtonShape.Round) {
                        "100vh"
                    } else {
                        "${size.squareCornerRadius}px"
                    }
                }
                set(enabled) { disabled = !it }
                set(backgroundColor) { style.backgroundColor = it.toCssString() }
                set(rippleColor) {
                    onpointerdown = EventHandler { event -> createRipple(it.toCssString(), event) }
                }
            }
        ) {
            CompositionLocalProvider(
                LocalContentColor provides contentColor,
                LocalTextStyle provides LocalTextStyle.current.copy(fontWeight = FontWeight.SemiBold)
            ) {
                Row(
                    modifier = Modifier
                        .width(
                            minWidth = 58f - size.contentPadding.left - size.contentPadding.right,
                            maxWidth = null
                        )
                        .height(
                            minHeight = 40f - size.contentPadding.top - size.contentPadding.bottom,
                            maxHeight = null
                        )
                        .padding(size.contentPadding),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.Center,
                    content = content
                )
            }
        }
    }
}
