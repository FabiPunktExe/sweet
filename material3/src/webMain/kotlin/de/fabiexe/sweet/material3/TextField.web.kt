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
                element.style.padding = "${16 - borderWidth}px"
                element.style.borderWidth = "${borderWidth}px"
                element.style.borderColor = outlineColor.toCssString()
                element.applyModifier(modifier)
                element.applyPointerHoverIcon(modifier, if (enabled) PointerIcon.Text else null)
                //if (!enabled) element.style.pointerEvents = "none"
                /*input.style.border = "none"
                input.style.padding = paddingValue
                input.style.boxSizing = "border-box"
                input.style.fontSize = "16px"
                input.style.fontFamily = "inherit"
                input.style.color = inputColor.toCssString()
                input.rows = if (singleLine) 1 else 2
                input.style.whiteSpace = if (singleLine) "nowrap" else "normal"
                input.style.overflowX = if (singleLine) "auto" else "hidden"
                input.style.overflowY = if (singleLine) "hidden" else "auto"*/
                element.value = value
                element.disabled = !enabled
                element.readOnly = readOnly
                element.oninput = EventHandler { onValueChange(element.value) }
                element.onfocus = EventHandler { focused = true }
                element.onblur = EventHandler { focused = false }
                element.onkeydown = EventHandler { ev ->
                    if (singleLine && ev.key == "Enter") ev.preventDefault()
                }
                element
            },
            update = {
                set(value) { if (this.value != it) this.value = it }
                //set(inputColor) { style.color = it.toCssString() }
                //set(paddingValue) { style.padding = it }
                set(enabled) { disabled = !it }
                set(readOnly) { this.readOnly = it }
                set(modifier) {
                    applyModifier(it)
                    applyPointerHoverIcon(it, if (enabled) PointerIcon.Text else null)
                }
                set(focused) {
                    style.padding = "${16 - borderWidth}px"
                    style.borderWidth = "${borderWidth}px"
                    style.borderColor = outlineColor.toCssString()
                }
                /*set(singleLine) { sl ->
                rows = if (sl) 1 else 2
                style.whiteSpace = if (sl) "nowrap" else "normal"
                style.overflowX = if (sl) "auto" else "hidden"
                style.overflowY = if (sl) "hidden" else "auto"
                onkeydown = EventHandler { ev -> if (sl && ev.key == "Enter") ev.preventDefault() }
            }
            set(onValueChange) { handler -> oninput = EventHandler { handler(this.value) } }*/
            }
        )

        if (label != null) {
            val labelFloating = focused || value.isNotEmpty()
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
                    element.style.transition = "top 0.15s ease, font-size 0.15s ease"

                    // Dynamic properties
                    element.style.top = if (labelFloating) "0" else "50%"
                    element.style.transform = if (labelFloating) "none" else "translateY(-50%)"
                    element.style.fontSize = if (labelFloating) "12px" else "16px"
                    //element.style.background = if (labelFloating) surfaceColor.toCssString() else "transparent"
                    element.style.padding = if (labelFloating) "0 4px" else "0"
                    element
                },
                update = {
                    set(labelFloating) {
                        style.top = if (it) "0" else "50%"
                        style.transform = if (labelFloating) "none" else "translateY(-50%)"
                        style.fontSize = if (it) "12px" else "16px"
                        //style.background = if (it) surfaceColor.toCssString() else "transparent"
                        style.padding = if (it) "0 4px" else "0"
                    }
                }
            ) {
                CompositionLocalProvider(LocalContentColor provides outlineColor, label)
            }
        }
    }
}