package io.github.orioncraftmc.stretchkt.node

import io.github.orioncraftmc.stretchkt.forest.NodeData
import io.github.orioncraftmc.stretchkt.geometry.Size
import io.github.orioncraftmc.stretchkt.style.Style


class Node {

    internal var internalNodeData: NodeData

    constructor(style: Style, measure: MeasureFunc? = null) {
        this.internalNodeData = Stretch.newLeafInternal(style, measure).also { it.exposedNode = this }
    }

    constructor(style: Style, children: List<Node> = emptyList()) {
        this.internalNodeData = Stretch.newNodeInternal(style, children).also { it.exposedNode = this }
    }

    var measure: MeasureFunc?
        get() = this.internalNodeData.measure
        set(value) {
            Stretch.setMeasure(this, measure)
        }

    var children: List<Node>
        get() {
            return internalNodeData.children.mapNotNull { it.exposedNode }
        }
        set(value) {
            Stretch.setChildren(this, value)
        }

    var style: Style
        get() {
            return Stretch.style(this)
        }
        set(value) {
            Stretch.setStyle(this, value)
        }

    val layout: Layout
        get() {
            return Stretch.layout(this)
        }

    val dirty: Boolean
        get() {
            return Stretch.dirty(this)
        }

    fun addChild(child: Node) {
        Stretch.addChild(this, child)
    }

    fun replaceChildAtIndex(index: Int, child: Node): Node {
        return Stretch.replaceChildAtIndex(this, index, child)
    }

    fun removeChild(child: Node): Node {
        Stretch.removeChild(this, child)
        return child
    }

    fun removeChildAtIndex(index: Int): Node {
        return Stretch.removeChildAtIndex(this, index)
    }

    fun markDirty() {
        Stretch.markDirty(this)
    }

    val childCount: Int
        get() = Stretch.childCount(this)

    fun computeLayout(size: Size<Float>): Layout {
        return Stretch.computeLayout(this, size)
    }

}