package io.github.orioncraftmc.sprawlkt.node

import io.github.orioncraftmc.sprawlkt.forest.NodeData
import io.github.orioncraftmc.sprawlkt.geometry.Size
import io.github.orioncraftmc.sprawlkt.style.Style


class Node {

    internal var internalNodeData: NodeData

    constructor(style: Style, measure: MeasureFunc? = null) {
        this.internalNodeData = Sprawl.newLeafInternal(style, measure).also { it.exposedNode = this }
    }

    constructor(style: Style, children: List<Node> = emptyList()) {
        this.internalNodeData = Sprawl.newNodeInternal(style, children).also { it.exposedNode = this }
    }

    var measure: MeasureFunc?
        get() = this.internalNodeData.measure
        set(value) {
            Sprawl.setMeasure(this, measure)
        }

    var children: List<Node>
        get() {
            return internalNodeData.children.mapNotNull { it.exposedNode }
        }
        set(value) {
            Sprawl.setChildren(this, value)
        }

    var style: Style
        get() {
            return Sprawl.style(this)
        }
        set(value) {
            Sprawl.setStyle(this, value)
        }

    val layout: Layout
        get() {
            return Sprawl.layout(this)
        }

    val dirty: Boolean
        get() {
            return Sprawl.dirty(this)
        }

    fun addChild(child: Node) {
        Sprawl.addChild(this, child)
    }

    fun replaceChildAtIndex(index: Int, child: Node): Node {
        return Sprawl.replaceChildAtIndex(this, index, child)
    }

    fun removeChild(child: Node): Node {
        Sprawl.removeChild(this, child)
        return child
    }

    fun removeChildAtIndex(index: Int): Node {
        return Sprawl.removeChildAtIndex(this, index)
    }

    fun markDirty() {
        Sprawl.markDirty(this)
    }

    val childCount: Int
        get() = Sprawl.childCount(this)

    fun computeLayout(size: Size<Float>): Layout {
        return Sprawl.computeLayout(this, size)
    }

}