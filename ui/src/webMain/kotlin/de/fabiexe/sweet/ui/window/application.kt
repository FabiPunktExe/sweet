package de.fabiexe.sweet.ui.window

import androidx.compose.runtime.BroadcastFrameClock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Composition
import androidx.compose.runtime.Recomposer
import androidx.compose.runtime.snapshots.Snapshot
import de.fabiexe.sweet.ui.DomApplier
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import web.animations.requestAnimationFrame
import web.dom.document

fun application(content: @Composable () -> Unit) {
    val clock = BroadcastFrameClock()
    val coroutineContext = Dispatchers.Default + clock
    val recomposer = Recomposer(coroutineContext)
    val composition = Composition(DomApplier(document.body), recomposer)

    CoroutineScope(coroutineContext).launch {
        recomposer.runRecomposeAndApplyChanges()
    }

    Snapshot.registerGlobalWriteObserver {
        Snapshot.sendApplyNotifications()
    }

    composition.setContent(content)

    fun redraw() {
        requestAnimationFrame {
            clock.sendFrame(0)
            redraw()
        }
    }
    redraw()
}