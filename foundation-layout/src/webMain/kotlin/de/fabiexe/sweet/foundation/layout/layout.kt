package de.fabiexe.sweet.foundation.layout

import de.fabiexe.sweet.ui.Modifier
import web.html.HTMLDivElement
import web.html.HTMLElement

fun HTMLDivElement.applyArrangement(arrangement: Arrangement) {
    when (arrangement.orientation) {
        Arrangement.Orientation.Start -> style.justifyContent = "start"
        Arrangement.Orientation.Center -> style.justifyContent = "center"
        Arrangement.Orientation.End -> style.justifyContent = "end"
    }
    if ((style.display == "flex" || style.display == "grid") && arrangement.space != null) {
        style.gap = "${arrangement.space}px"
    } else {
        style.gap = ""
    }
}

fun HTMLElement.applyPadding(modifier: Modifier) {
    val padding = modifier.fold(PaddingValues(0f)) { acc, element ->
        when (element) {
            is PaddingElement -> acc + element.paddingValues
            else -> acc
        }
    }
    style.marginLeft = "${padding.left}px"
    style.marginRight = "${padding.right}px"
    style.marginTop = "${padding.top}px"
    style.marginBottom = "${padding.bottom}px"
}