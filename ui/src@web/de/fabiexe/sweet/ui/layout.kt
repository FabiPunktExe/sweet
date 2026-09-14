package de.fabiexe.sweet.ui

import web.html.HTMLDivElement

fun HTMLDivElement.applyAlignment(alignment: Alignment) {
    when (alignment) {
        Alignment.Start -> style.alignItems = "start"
        Alignment.Center -> style.alignItems = "center"
        Alignment.End -> style.alignItems = "end"
    }
}