package de.fabiexe.sweet.material3

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeNode
import androidx.compose.runtime.CompositionLocalProvider
import de.fabiexe.sweet.ui.DomApplier
import de.fabiexe.sweet.ui.graphics.Color
import de.fabiexe.sweet.ui.graphics.toCssString
import web.dom.ElementId
import web.dom.document
import web.html.HTMLLabelElement
import kotlin.uuid.Uuid

@Composable
fun FloatingLabel(
    id: Uuid,
    floating: Boolean,
    contentColor: Color,
    backgroundColor: Color,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(LocalContentColor provides contentColor) {
        ComposeNode<HTMLLabelElement, DomApplier>(
            factory = {
                val element = document.createElement("label") as HTMLLabelElement

                // Constant properties
                element.htmlFor = ElementId(id.toString())
                element.style.position = "absolute"
                element.style.top = "0"
                element.style.left = "16px"
                element.style.pointerEvents = "none"
                element.style.userSelect = "none"
                element.style.whiteSpace = "nowrap"
                element.style.transition = "transform 0.15s linear, font-size 0.15s linear, color 0.15s linear"

                // Dynamic properties
                element.style.background = if (floating) backgroundColor.toCssString() else "transparent"
                element.applyFloatingDependentProperties(floating)

                element
            },
            update = {
                set(floating) {
                    style.background = if (it) backgroundColor.toCssString() else "transparent"
                    applyFloatingDependentProperties(it)
                }
                set(contentColor) { style.color = contentColor.toCssString() }
                set(backgroundColor) {
                    style.background = if (floating) it.toCssString() else "transparent"
                }
            },
            content = content
        )
    }
}

private fun HTMLLabelElement.applyFloatingDependentProperties(floating: Boolean) {
    style.transform = if (floating) "translate(-4px, -50%)" else "translateY(calc(28px - 50%))"
    style.fontSize = if (floating) "12px" else "16px"
    style.lineHeight = if (floating) "16px" else "24px"
    style.padding = if (floating) "0 4px" else "0"
}