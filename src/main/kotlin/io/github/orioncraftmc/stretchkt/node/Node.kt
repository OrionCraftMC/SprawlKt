package io.github.orioncraftmc.stretchkt.node

import io.github.orioncraftmc.stretchkt.forest.NodeData
import io.github.orioncraftmc.stretchkt.geometry.Size
import io.github.orioncraftmc.stretchkt.style.Style


class Node {

    internal var internalNodeData: NodeData

    constructor(style: Style, measure: MeasureFunc) {
        this.internalNodeData = Stretch.newLeaf(style, measure).also { it.exposedNode = this }
    }

    constructor(style: Style, children: List<Node>) {
        this.internalNodeData = Stretch.newNode(style, children).also { it.exposedNode = this }
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

    fun getStyle(): Style {
        return Stretch.style(this)
    }

    fun setStyle(style: Style) {
        Stretch.setStyle(this, style)
    }

    fun markDirty() {
        Stretch.markDirty(this)
    }

    fun isDirty(): Boolean {
        return Stretch.dirty(this)
    }

    val childCount: Int
        get() = Stretch.childCount(this)

    fun computeLayout(size: Size<Float>): Layout {
        return Stretch.computeLayout(this, size)
    }

}