package de.fabiexe.sweet.material3

import androidx.compose.runtime.*
import de.fabiexe.sweet.foundation.layout.*
import de.fabiexe.sweet.ui.DomApplier
import de.fabiexe.sweet.ui.Modifier
import de.fabiexe.sweet.ui.graphics.Color
import de.fabiexe.sweet.ui.graphics.toCssString
import de.fabiexe.sweet.ui.input.pointer.PointerIcon
import web.dom.ElementId
import web.dom.document
import web.events.EventHandler
import web.html.HTMLDivElement
import web.html.HTMLLabelElement
import web.html.HTMLTextAreaElement
import kotlin.uuid.Uuid

private const val MinimizedLabelHalfHeight = 16 / 2

@Composable
actual fun TextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    readOnly: Boolean,
    label: @Composable (() -> Unit)?,
    singleLine: Boolean,
) {
    ComposeNode<HTMLDivElement, DomApplier>(
        factory = {
            val element = document.createElement("div") as HTMLDivElement
            element.style.position = "relative"
            element
        },
        update = {}
    ) {
        val id = remember { Uuid.random() }
        var focused by remember { mutableStateOf(false) }

        val colorScheme = LocalColorScheme.current

        val borderWidth = if (focused) 2f else 1f
        val outlineColor = when {
            !enabled -> colorScheme.onSurface.copy(alpha = 0.12f)
            focused -> colorScheme.primary
            else -> colorScheme.outline
        }
        val modifier = Modifier
            .padding(top = MinimizedLabelHalfHeight.toFloat())
            .width(minWidth = 280f - 16 - 16, maxWidth = null)
            .height(minHeight = 56f - 16 - 16, maxHeight = null)
            .then(modifier)

        ComposeNode<HTMLTextAreaElement, DomApplier>(
            factory = {
                val element = document.createElement("textarea") as HTMLTextAreaElement

                // Constant properties
                element.id = ElementId(id.toString())
                element.spellcheck = false
                element.style.position = "relative"
                element.style.resize = "none"
                element.style.outline = "none"
                element.style.borderStyle = "solid"
                element.style.borderRadius = "4px"
                element.style.backgroundColor = "transparent"

                // Dynamic properties
                element.style.padding = "${9 - borderWidth}px"
                element.style.borderWidth = "${borderWidth}px"
                element.style.borderColor = outlineColor.toCssString()
                element.applyModifier(modifier)
                element.applyPointerHoverIcon(modifier, if (enabled) PointerIcon.Text else null)

                element.value = value
                element.disabled = !enabled
                element.readOnly = readOnly
                element.oninput = EventHandler { onValueChange(element.value) }
                element.onfocus = EventHandler { focused = true }
                element.onblur = EventHandler { focused = false }
                element.onkeydown = EventHandler { ev ->
                    if (singleLine && ev.key == "Enter") {
                        ev.preventDefault()
                    }
                }

                element
            },
            update = {
                set(value) { if (this.value != it) this.value = it }
                set(enabled) { disabled = !it }
                set(readOnly) { this.readOnly = it }
                set(modifier) {
                    applyModifier(it)
                    applyPointerHoverIcon(it, if (enabled) PointerIcon.Text else null)
                }
                set(focused) {
                    style.padding = "${9 - borderWidth}px"
                    style.borderWidth = "${borderWidth}px"
                    style.borderColor = outlineColor.toCssString()
                }
            }
        )

        if (label != null) {
            FloatingLabel(
                id = id,
                floating = focused || value.isNotEmpty(),
                contentColor = outlineColor,
                content = label
            )
        }
    }
}

@Composable
private fun FloatingLabel(
    id: Uuid,
    floating: Boolean,
    contentColor: Color,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(LocalContentColor provides contentColor) {
        val backgroundColor = LocalBackgroundColor.current
        ComposeNode<HTMLLabelElement, DomApplier>(
            factory = {
                val element = document.createElement("label") as HTMLLabelElement

                // Constant properties
                element.htmlFor = ElementId(id.toString())
                element.style.position = "absolute"
                element.style.left = "12px"
                element.style.pointerEvents = "none"
                element.style.userSelect = "none"
                element.style.whiteSpace = "nowrap"
                element.style.transition = "top 0.15s linear, font-size 0.15s linear"

                // Dynamic properties
                element.applyFloatingLabelStyle(floating, backgroundColor)

                element
            },
            update = {
                set(floating) { applyFloatingLabelStyle(it, backgroundColor) }
                set(backgroundColor) { applyFloatingLabelStyle(floating, it) }
            },
            content = content
        )
    }
}

private fun HTMLLabelElement.applyFloatingLabelStyle(floating: Boolean, backgroundColor: Color) {
    style.top = if (floating) "${MinimizedLabelHalfHeight}px" else "calc(${MinimizedLabelHalfHeight}px + 50%)"
    style.transform = if (floating) {
        "translateY(-50%)"
    } else {
        "translateY(calc(${MinimizedLabelHalfHeight.toFloat() / -2}px - 50%))"
    }
    style.fontSize = if (floating) "12px" else "16px"
    style.background = if (floating) backgroundColor.toCssString() else "transparent"
    style.padding = if (floating) "0 4px" else "0"
}