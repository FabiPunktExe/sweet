package de.fabiexe.sweet.material3

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeNode
import de.fabiexe.sweet.foundation.layout.applyPadding
import de.fabiexe.sweet.ui.DomApplier
import de.fabiexe.sweet.ui.Modifier
import de.fabiexe.sweet.ui.graphics.toCssString
import de.fabiexe.sweet.ui.text.TextStyle
import web.dom.document
import web.html.HTMLElement
import web.html.HTMLSpanElement

@Composable
actual fun Text(
    text: String,
    modifier: Modifier,
    style: TextStyle?
) {
    val contentColor = LocalContentColor.current
    val textStyle = LocalTextStyle.current.merge(style)
    ComposeNode<HTMLSpanElement, DomApplier>(
        factory = {
            val element = document.createElement("span") as HTMLSpanElement
            element.style.color = contentColor.toCssString()
            element.applyTextStyle(textStyle)
            element.textContent = text
            element.applyPadding(modifier)
            element
        },
        update = {
            set(contentColor) { this.style.color = it.toCssString() }
            set(textStyle, HTMLElement::applyTextStyle)
            set(text) { textContent = it }
            set(modifier, HTMLElement::applyPadding)
        }
    )
}

private fun HTMLElement.applyTextStyle(textStyle: TextStyle) {
    style.fontWeight = textStyle.fontWeight?.value?.toString() ?: ""
    style.fontSize = textStyle.fontSize?.let { "${it}px" } ?: ""
    style.lineHeight = textStyle.lineHeight?.let { "${it}px" } ?: ""
    style.letterSpacing = textStyle.letterSpacing?.let { "${it}px" } ?: ""
}