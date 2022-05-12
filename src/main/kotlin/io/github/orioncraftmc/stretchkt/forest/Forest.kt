package io.github.orioncraftmc.stretchkt.forest

import io.github.orioncraftmc.stretchkt.geometry.Size
import io.github.orioncraftmc.stretchkt.node.MeasureFunc
import io.github.orioncraftmc.stretchkt.number.StretchNumber
import io.github.orioncraftmc.stretchkt.style.Style
import io.github.orioncraftmc.stretchkt.style.enums.StretchDimension
import jdk.javadoc.internal.doclets.toolkit.util.DocPath.parent

/**
 * Forest - Backing datastructure for `Stretch` structs.
 *
 * PORT NOTE: The implementation of this class differs from the original implementation.
 */
internal data class Forest(
    val nodes: HashSet<NodeData> = HashSet(),
) {

    fun withCapacity(capacity: Int): Forest {
        // TODO: Check if capacity is needed
        return Forest()
    }

    fun newLeaf(style: Style, measure: MeasureFunc): NodeData {
        return NodeData(style, measure).also {
            this.nodes.add(it)
        }
    }

    fun newNode(style: Style, children: Collection<NodeData>): NodeData {
        return NodeData(style).also { newNode ->
            // First, add the node
            this.nodes.add(newNode)

            // Then, add the children to the node
            newNode.children.addAll(children)

            // Then, for each child, add the node as a parent
            children.forEach { child ->
                child.parents.add(newNode)
            }
        }
    }


    fun addChild(node: NodeData, child: NodeData) {
        node.children.add(child)
        child.parents.add(node)
        markDirty(node)
    }

    fun clear() {
        this.nodes.forEach { n -> n.children.clear(); n.parents.clear() }
        this.nodes.clear()
    }


    /// Removes a node and swaps with the last node.
    fun swapRemove(node: NodeData): NodeData? {
        // PORT NOTE: The implementation of this method differs from the original implementation.
        nodes.remove(node)

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

    fun removeChild(node: NodeData, child: NodeData) {
        node.children.remove(child)
        child.parents.remove(node)
    }

    fun removeChildAtIndex(node: NodeData, index: Int) {
        throw Exception("Port makes use of a HashSet to store children, thus removing with an index is not supported.")
    }

    fun markDirty(node: NodeData) {
        node.mainSizeLayoutCache = null
        node.otherLayoutCache = null
        node.isDirty = true

        for (parent in node.parents) {
            markDirty(parent)
        }
    }

    fun computeLayout(node: NodeData, size: Size<StretchNumber>) {
        //TODO: this.compute(node, size)
    }

}