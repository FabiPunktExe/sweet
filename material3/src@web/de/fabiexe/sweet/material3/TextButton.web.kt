package de.fabiexe.sweet.material3

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeNode
import androidx.compose.runtime.CompositionLocalProvider
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

private fun HTMLButtonElement.applyTextButtonColors(enabled: Boolean, colors: ButtonColors) {
    disabled = !enabled
    style.backgroundColor = (if (enabled) colors.containerColor else colors.disabledContainerColor).toCssString()
    val rippleColor = (if (enabled) colors.contentColor else colors.disabledContentColor).copy(alpha = 0.12f)
    onpointerdown = EventHandler { event -> createRipple(rippleColor.toCssString(), event) }
}

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
                element.style.height = "${size.height}px"
                element.style.borderRadius = if (shape == ButtonShape.Round) {
                    "100vh"
                } else {
                    "${size.squareCornerRadius}px"
                }
                element.applyTextButtonColors(enabled, colors)
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
                set(enabled) { applyTextButtonColors(it, colors) }
                set(colors) { applyTextButtonColors(enabled, it) }
            }
        ) {
            val contentColor = if (enabled) colors.contentColor else colors.disabledContentColor
            CompositionLocalProvider(
                LocalContentColor provides contentColor,
                LocalFontWeight provides FontWeight.SemiBold
            ) {
                Row(
                    modifier = Modifier
                        .width(minWidth = 58f - size.contentPadding.left - size.contentPadding.right, maxWidth = null)
                        .height(minHeight = 40f - size.contentPadding.top - size.contentPadding.bottom, maxHeight = null)
                        .padding(size.contentPadding),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.Center,
                    content = content
                )
            }
        }
    }
}
