package de.fabiexe.sweet.ui.text.font

import kotlin.jvm.JvmInline

@JvmInline
value class FontWeight(val value: Int) {
    companion object {
        val ExtraExtraLight = FontWeight(100)
        val ExtraLight = FontWeight(200)
        val Light = FontWeight(300)
        val Normal = FontWeight(400)
        val Medium = FontWeight(500)
        val SemiBold = FontWeight(600)
        val Bold = FontWeight(700)
        val ExtraBold = FontWeight(800)
        val ExtraExtraBold = FontWeight(900)
    }
}