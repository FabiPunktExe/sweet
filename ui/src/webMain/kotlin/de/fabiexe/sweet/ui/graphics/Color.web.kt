package de.fabiexe.sweet.ui.graphics

fun Color.toCssString() = "rgba($red, $green, $blue, ${alpha / 255.0})"