package io.github.orioncraftmc.stretchkt.node

import io.github.orioncraftmc.stretchkt.forest.Forest
import io.github.orioncraftmc.stretchkt.forest.NodeData
import io.github.orioncraftmc.stretchkt.geometry.Size
import io.github.orioncraftmc.stretchkt.geometry.toStretchNumberSize
import io.github.orioncraftmc.stretchkt.number.StretchNumber
import io.github.orioncraftmc.stretchkt.style.Style

internal object Stretch {
    internal val forest: Forest = Forest()

    fun newLeaf(style: Style, measure: MeasureFunc): NodeData {
        return forest.newLeaf(style, measure)
    }

    fun newNode(style: Style, children: List<Node>): NodeData {
        return forest.newNode(style, children.map { it.internalNodeData })
    }

    fun setMeasure(node: Node, measure: MeasureFunc?) {
        node.internalNodeData.measure = measure
        forest.markDirty(node.internalNodeData)
    }


    fun setChildren(node: Node, children: List<Node>) {
        // Remove node as parent from all its current children.
        for (oldChild in node.internalNodeData.children) {
            oldChild.parents.remove(node.internalNodeData)
        }

        // Clear the children list
        node.internalNodeData.children.clear()

        // Build up relation node <-> child
        for (child in children) {
            child.internalNodeData.parents.add(node.internalNodeData)
            node.internalNodeData.children.add(child.internalNodeData)
        }

        forest.markDirty(node.internalNodeData)
    }

    fun addChild(node: Node, child: Node) {
        forest.addChild(node.internalNodeData, child.internalNodeData)
    }

    fun removeChild(node: Node, child: Node) {
        forest.removeChild(node.internalNodeData, child.internalNodeData)
    }

    fun removeChildAtIndex(node: Node, index: Int): Node {
        // TODO: index check
        return getExposedNode(forest.removeChildAtIndex(node.internalNodeData, index))
    }

    fun replaceChildAtIndex(node: Node, index: Int, child: Node): Node {
        // TODO: index check

        child.internalNodeData.parents.add(node.internalNodeData)

        val oldNode = node.internalNodeData.children[index]
        node.internalNodeData.children[index] = child.internalNodeData
        oldNode.parents.remove(node.internalNodeData)

        forest.markDirty(node.internalNodeData)

        return getExposedNode(oldNode)
    }

    fun children(node: Node): List<Node> {
        return node.children
    }

    fun childAtIndex(node: Node, index: Int): Node {
        return node.children[index]
    }

    fun childCount(node: Node): Int {
        return node.children.size
    }


    fun dirty(node: Node): Boolean {
        return node.internalNodeData.isDirty
    }

    fun markDirty(node: Node) {
        forest.markDirty(node.internalNodeData)
    }

    fun setStyle(node: Node, style: Style) {
        node.internalNodeData.style = style
        forest.markDirty(node.internalNodeData)
    }

    fun style(node: Node): Style {
        return node.internalNodeData.style
    }


    /**
     * Return this node layout relative to its parent
     */
    fun layout(node: Node): Layout = node.internalNodeData.layout

    fun computeLayout(node: Node, size: Size<Float>): Layout {
        return computeLayout(node, size.toStretchNumberSize())
    }

    @JvmName("computeLayoutStretchNumber")
    fun computeLayout(node: Node, size: Size<StretchNumber>): Layout {
        forest.computeLayout(node.internalNodeData, size)
        return node.internalNodeData.layout
    }

    private fun getExposedNode(data: NodeData): Node {
        return data.exposedNode ?: throw IllegalStateException("NodeData is not exposed yet")
    }
}
