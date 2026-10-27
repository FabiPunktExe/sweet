package de.fabiexe.sweet.ui

import androidx.compose.runtime.AbstractApplier
import js.array.asList
import web.dom.Element

class DomApplier(root: Element) : AbstractApplier<Element>(root) {
    override fun insertTopDown(index: Int, instance: Element) {
        current.insertBefore(instance, current.children.item(index))
    }

    override fun insertBottomUp(index: Int, instance: Element) {}

    override fun remove(index: Int, count: Int) {
        repeat(count) {
            current.removeChild(current.children.item(index)!!)
        }
    }

    override fun move(from: Int, to: Int, count: Int) {
        val moving = current.children.asList().subList(from, from + count)
        for (child in moving) {
            current.removeChild(child)
        }

        val referenceNode = if (to < current.children.length) {
            current.children.item(to)
        } else {
            null
        }
        for (child in moving) {
            current.insertBefore(child, referenceNode)
        }
    }

    override fun onClear() {
        for (child in current.children.asList()) {
            current.removeChild(child)
        }
    }
}