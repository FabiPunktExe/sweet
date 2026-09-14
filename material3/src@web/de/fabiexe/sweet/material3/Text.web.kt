package de.fabiexe.sweet.material3

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeNode
import de.fabiexe.sweet.foundation.layout.applyPadding
import de.fabiexe.sweet.ui.DomApplier
import de.fabiexe.sweet.ui.Modifier
import de.fabiexe.sweet.ui.graphics.toCssString
import web.dom.document
import web.html.HTMLElement
import web.html.HTMLSpanElement

@Composable
actual fun Text(
    text: String,
    modifier: Modifier
) {
    val contentColor = LocalContentColor.current
    val fontWeight = LocalFontWeight.current
    ComposeNode<HTMLSpanElement, DomApplier>(
        factory = {
            val element = document.createElement("span") as HTMLSpanElement
            element.style.color = contentColor.toCssString()
            element.style.fontWeight = fontWeight.value.toString()
            element.textContent = text
            element.applyPadding(modifier)
            element
        },
        update = {
            set(contentColor) { style.color = it.toCssString() }
            set(fontWeight) { style.fontWeight = it.value.toString() }
            set(text) { textContent = it }
            set(modifier, HTMLElement::applyPadding)
        }
    )
}