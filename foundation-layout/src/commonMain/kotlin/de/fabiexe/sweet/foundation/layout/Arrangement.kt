package de.fabiexe.sweet.foundation.layout

import androidx.compose.runtime.Immutable

@Immutable
data class Arrangement(val orientation: Orientation, val space: Float? = null) {
    fun spacedBy(space: Float) = copy(space = space)

    companion object {
        val Start = Arrangement(Orientation.Start)
        val Center = Arrangement(Orientation.Center)
        val End = Arrangement(Orientation.End)
    }

    enum class Orientation {
        Start, Center, End
    }
}