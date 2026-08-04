package de.fabiexe.sweet.foundation.layout

import de.fabiexe.sweet.ui.Modifier
import de.fabiexe.sweet.ui.fold
import web.cssom.min
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

fun HTMLElement.applyModifier(modifier: Modifier) {
    applyPadding(modifier)
    applySize(modifier)
}

fun HTMLElement.applyPadding(modifier: Modifier) {
    val padding = modifier.fold<PaddingValues, PaddingElement>(PaddingValues(0f)) { acc, element ->
        acc + element.paddingValues
    }
    style.marginLeft = "${padding.left}px"
    style.marginRight = "${padding.right}px"
    style.marginTop = "${padding.top}px"
    style.marginBottom = "${padding.bottom}px"
}

fun HTMLElement.applySize(modifier: Modifier) {
    val minWidth = modifier.fold<Float?, SizeElement>(null) { acc, element -> element.minWidth ?: acc }
    style.minWidth = if (minWidth != null) "${minWidth}px" else ""

    val maxWidth = modifier.fold<Float?, SizeElement>(null) { acc, element -> element.maxWidth ?: acc }
    style.maxWidth = if (maxWidth != null) "${maxWidth}px" else ""

    val minHeight = modifier.fold<Float?, SizeElement>(null) { acc, element -> element.minHeight ?: acc }
    style.minHeight = if (minHeight != null) "${minHeight}px" else ""

    val maxHeight = modifier.fold<Float?, SizeElement>(null) { acc, element -> element.maxHeight ?: acc }
    style.maxHeight = if (maxHeight != null) "${maxHeight}px" else ""

    val widthFraction = modifier.fold<Float?, FillElement>(null) { acc, element ->
        if (element.direction == FillElement.Direction.Horizontal || element.direction == FillElement.Direction.Both) {
            element.fraction
        } else {
            acc
        }
    }
    style.width = if (widthFraction != null) "$widthFraction%" else "fit-content"

    val heightFraction = modifier.fold<Float?, FillElement>(null) { acc, element ->
        if (element.direction == FillElement.Direction.Vertical || element.direction == FillElement.Direction.Both) {
            element.fraction
        } else {
            acc
        }
    }
    style.height = if (heightFraction != null) "$heightFraction%" else ""
}