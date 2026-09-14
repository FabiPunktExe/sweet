package de.fabiexe.sweet.material3

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeNode
import de.fabiexe.sweet.ui.DomApplier
import de.fabiexe.sweet.ui.graphics.Color
import de.fabiexe.sweet.ui.graphics.toCssString
import web.dom.document
import web.html.HTMLDivElement

@Composable
fun Outline(width: Float, color: Color) {
    ComposeNode<HTMLDivElement, DomApplier>(
        factory = {
            val element = document.createElement("div") as HTMLDivElement

            // Constant properties
            element.style.position = "absolute"
            element.style.top = "0"
            element.style.right = "0"
            element.style.bottom = "0"
            element.style.left = "0"
            element.style.borderStyle = "solid"
            element.style.borderRadius = "4px"
            element.style.boxSizing = "border-box"
            element.style.pointerEvents = "none"
            element.style.transition = "border-color 0.15s linear"

            // Dynamic properties
            element.style.borderWidth = "${width}px"
            element.style.borderColor = color.toCssString()

            element
        },
        update = {
            set(width) { style.borderWidth = "${it}px" }
            set(color) { style.borderColor = it.toCssString() }
        }
    )
}