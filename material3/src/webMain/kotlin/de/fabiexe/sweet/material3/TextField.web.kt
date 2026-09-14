package de.fabiexe.sweet.material3

import androidx.compose.runtime.*
import de.fabiexe.sweet.foundation.layout.*
import de.fabiexe.sweet.ui.DomApplier
import de.fabiexe.sweet.ui.Modifier
import de.fabiexe.sweet.ui.graphics.toCssString
import de.fabiexe.sweet.ui.input.pointer.PointerIcon
import web.dom.ElementId
import web.dom.document
import web.events.EventHandler
import web.html.HTMLDivElement
import web.html.HTMLElement
import web.html.HTMLTextAreaElement
import kotlin.uuid.Uuid

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
    var focused by remember { mutableStateOf(false) }

    val colorScheme = LocalColorScheme.current
    val outlineColor = when {
        !enabled -> colorScheme.onSurface.copy(alpha = 0.12f)
        focused -> colorScheme.primary
        else -> colorScheme.outline
    }
    val textColor = if (enabled) colorScheme.onSurface else colorScheme.onSurface.copy(alpha = 0.38f)
    val caretColor = if (enabled) colorScheme.primary else colorScheme.onSurface.copy(alpha = 0.38f)

    val containerModifier = Modifier
        .padding(top = 10f)
        .width(minWidth = 280f, maxWidth = null)
        .height(minHeight = 56f, maxHeight = null)
        .then(modifier)

    ComposeNode<HTMLDivElement, DomApplier>(
        factory = {
            val element = document.createElement("div") as HTMLDivElement

            // Constant properties
            element.style.position = "relative"
            element.style.overflow = "visible"

            // Dynamic properties
            element.applyModifier(containerModifier)
            element.applyPointerHoverIcon(containerModifier, if (enabled) PointerIcon.Text else null)

            element
        },
        update = {
            set(containerModifier) {
                applyModifier(it)
                applyPointerHoverIcon(it, if (enabled) PointerIcon.Text else null)
            }
            set(enabled) { applyPointerHoverIcon(containerModifier, if (it) PointerIcon.Text else null) }
        }
    ) {
        Outline(if (focused) 2f else 1f, outlineColor)

        val id = remember { Uuid.random() }

        ComposeNode<HTMLTextAreaElement, DomApplier>(
            factory = {
                val element = document.createElement("textarea") as HTMLTextAreaElement

                // Constant properties
                element.id = ElementId(id.toString())
                element.spellcheck = false
                element.rows = 1
                element.style.resize = "none"
                element.style.overflow = "hidden"
                element.applyInputConstants()
                element.applyEnterBehavior(singleLine)

                // Dynamic properties
                element.style.color = textColor.toCssString()
                element.style.caretColor = caretColor.toCssString()
                element.value = value
                element.disabled = !enabled
                element.readOnly = readOnly
                element.oninput = EventHandler {
                    onValueChange(element.value)
                    element.autoGrow()
                }
                element.onfocus = EventHandler { focused = true }
                element.onblur = EventHandler { focused = false }
                element.autoGrow()

                element
            },
            update = {
                set(value) {
                    if (this.value != it) {
                        this.value = it
                        autoGrow()
                    }
                }
                set(onValueChange) { callback ->
                    val field = this
                    oninput = EventHandler {
                        callback(field.value)
                        field.autoGrow()
                    }
                }
                set(singleLine) { applyEnterBehavior(it) }
                set(enabled) { disabled = !it }
                set(readOnly) { this.readOnly = it }
                set(textColor) { style.color = it.toCssString() }
                set(caretColor) { style.caretColor = it.toCssString() }
            }
        )

        if (label != null) {
            FloatingLabel(
                id = id,
                floating = focused || value.isNotEmpty(),
                contentColor = outlineColor,
                backgroundColor = LocalBackgroundColor.current,
                content = label
            )
        }
    }
}

private fun HTMLElement.applyInputConstants() {
    style.display = "block"
    style.width = "100%"
    style.minHeight = "56px"
    style.boxSizing = "border-box"
    style.margin = "0"
    style.padding = "16px"
    style.background = "transparent"
    style.border = "none"
    style.outline = "none"
    style.fontFamily = "inherit"
    style.fontSize = "16px"
    style.lineHeight = "24px"
}

private fun HTMLTextAreaElement.applyEnterBehavior(singleLine: Boolean) {
    onkeydown = EventHandler { ev ->
        if (singleLine && ev.key == "Enter") ev.preventDefault()
    }
}

private fun HTMLTextAreaElement.autoGrow() {
    style.height = "auto"
    style.height = "${scrollHeight}px"
}