package io.github.orioncraftmc.stretchkt.forest

import io.github.orioncraftmc.stretchkt.forest.algo.compute
import io.github.orioncraftmc.stretchkt.geometry.Size
import io.github.orioncraftmc.stretchkt.node.MeasureFunc
import io.github.orioncraftmc.stretchkt.number.StretchNumber
import io.github.orioncraftmc.stretchkt.style.Style

/**
 * Forest - Backing datastructure for `Stretch` structs.
 *
 * PORT NOTE: The implementation of this class differs from the original implementation.
 */
internal class Forest {

    internal fun withCapacity(capacity: Int): Forest {
        // TODO: Check if capacity is needed
        return Forest()
    }

    internal fun newLeaf(style: Style, measure: MeasureFunc): NodeData {
        return NodeData(style, measure)
    }

    internal fun newNode(style: Style, children: Collection<NodeData>): NodeData {
        return NodeData(style).also { newNode ->
            // Then, add the children to the node
            newNode.children.addAll(children)

            // Then, for each child, add the node as a parent
            children.forEach { child ->
                child.parents.add(newNode)
            }
        }
    }


    internal fun addChild(node: NodeData, child: NodeData) {
        node.children.add(child)
        child.parents.add(node)
        markDirty(node)
    }

    internal fun clear() {
        // PORT NOTE: The implementation of this method differs from the original implementation.
        throw Exception("Port's implementation of the Forest doesn't keep a list of nodes, so this method is not needed")
    }


    /// Removes a node and swaps with the last node.
    internal fun swapRemove(node: NodeData): NodeData? {

        // Remove old node as parent from all its chilren.
        node.children.forEach { child ->
            child.parents.remove(node)
        }

        // Remove old node as child from all its parents.
        node.parents.forEach { parent ->
            parent.children.remove(node)
        }

        return null
    }

    internal fun removeChild(node: NodeData, child: NodeData) {
        node.children.remove(child)
        child.parents.remove(node)
    }

    internal fun removeChildAtIndex(node: NodeData, index: Int): NodeData {
        return node.children.removeAt(index)
    }

    internal fun markDirty(node: NodeData) {
        node.mainSizeLayoutCache = null
        node.otherLayoutCache = null
        node.isDirty = true

        for (parent in node.parents) {
            markDirty(parent)
        }
    }

    internal fun computeLayout(node: NodeData, size: Size<StretchNumber>) {
        this.compute(node, size)
    }

}