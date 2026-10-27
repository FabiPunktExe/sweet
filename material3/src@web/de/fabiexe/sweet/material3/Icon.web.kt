package de.fabiexe.sweet.material3

import androidx.compose.runtime.*
import de.fabiexe.sweet.foundation.layout.applyModifier
import de.fabiexe.sweet.ui.DomApplier
import de.fabiexe.sweet.ui.Modifier
import de.fabiexe.sweet.ui.graphics.Color
import de.fabiexe.sweet.ui.graphics.toCssString
import js.iterable.iterator
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.getDrawableResourceBytes
import org.jetbrains.compose.resources.getSystemResourceEnvironment
import web.dom.Element
import web.html.HTMLElement
import web.parsing.DOMParser
import web.parsing.DOMParserSupportedType
import web.parsing.imageSvgXml

@Composable
actual fun Icon(
    resource: DrawableResource,
    contentDescription: String?,
    modifier: Modifier,
    tint: Color
) {
    val environment = remember { getSystemResourceEnvironment() }
    val resourceString by produceState<String?>(null, resource) {
        value = getDrawableResourceBytes(environment, resource).decodeToString()
    }

    if (resourceString != null) {
        val modifier = modifier.withDefaultIconSize()
        ComposeNode<HTMLElement, DomApplier>(
            factory = {
                val element =
                    DOMParser().parseFromString(resourceString!!, DOMParserSupportedType.imageSvgXml).documentElement

                // Constant properties
                element.style.display = "inline-block"
                element.style.lineHeight = "0"
                element.style.verticalAlign = "middle"

                // Dynamic properties
                element.applyContentDescription(contentDescription)
                element.tintSvg(tint)
                element.applyModifier(modifier)

                element
            },
            update = {
                set(contentDescription, HTMLElement::applyContentDescription)
                set(tint, HTMLElement::tintSvg)
                set(modifier, HTMLElement::applyModifier)
            }
        )
    }
}

private fun HTMLElement.applyContentDescription(description: String?) {
    if (description == null) {
        setAttribute("aria-hidden", "true")
        removeAttribute("role")
        removeAttribute("aria-label")
    } else {
        removeAttribute("aria-hidden")
        setAttribute("role", "img")
        setAttribute("aria-label", description)
    }
}

private fun Element.tintSvg(tint: Color) {
    if ((getAttribute("fill") ?: "none") == "none") {
        setAttribute("fill", tint.toCssString())
    }
    if ((getAttribute("stroke") ?: "none") == "none") {
        setAttribute("stroke", tint.toCssString())
    }
    for (child in children) {
        child.tintSvg(tint)
    }
}