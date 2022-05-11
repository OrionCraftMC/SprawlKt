package io.github.orioncraftmc.stretchkt.forest

import io.github.orioncraftmc.stretchkt.extensions.swapRemove
import io.github.orioncraftmc.stretchkt.node.MeasureFunc
import io.github.orioncraftmc.stretchkt.node.NodeId
import io.github.orioncraftmc.stretchkt.style.Style

internal typealias ChildrenVec<T> = MutableList<T>
internal typealias ParentsVec<T> = MutableList<T>

/**
 * Forest - ECS like datastructure for storing node trees.
 *
 * Backing datastructure for `Stretch` structs.
 */
internal data class Forest(
    val nodes: MutableList<NodeData> = mutableListOf(),
    val children: MutableList<ChildrenVec<NodeId>> = mutableListOf(),
    val parents: MutableList<ParentsVec<NodeId>> = mutableListOf(),
) {

    fun withCapacity(capacity: Int): Forest {
        // TODO: Check if capacity is needed
        return Forest()
    }

    fun newLeaf(style: Style, measure: MeasureFunc): NodeId {
        val id = nodes.size
        this.nodes.add(NodeData(style, measure))
        this.children.add(mutableListOf())
        this.parents.add(mutableListOf())

        return id
    }

    fun newNode(style: Style, children: ChildrenVec<NodeId>): NodeId {
        val id = nodes.size
        children.forEach { child ->
            parents[child].add(id)
        }
        this.nodes.add(NodeData(style))
        this.children.add(children)
        this.parents.add(mutableListOf())

        return id
    }


    fun addChild(node: NodeId, child: NodeId) {
        this.parents[child].add(node)
        this.children[node].add(child)
        //TODO: this.markDirty(node)
    }

    fun clear() {
        this.nodes.clear()
        this.children.clear()
        this.parents.clear()
    }


    /// Removes a node and swaps with the last node.
    fun swapRemove(node: NodeId): NodeId? {
        this.nodes.swapRemove(node)

        // Now the last element is swapped in at index `node`.
        if (this.nodes.isEmpty()) {
            this.children.clear()
            this.parents.clear()
            return null
        }

        // Remove old node as parent from all its chilren.
        for (child in this.children[node]) {
            val parentsChild = this.parents[child]
            var pos = 0
            while (pos < parentsChild.size) {
                if (parentsChild[pos] == node) {
                    parentsChild.swapRemove(pos)
                } else {
                    pos += 1
                }
            }
        }

        // Remove old node as child from all its parents.
        for (parent in this.parents[node]) {
            val childrensParent = this.children[parent]
            var pos = 0
            while (pos < childrensParent.size) {
                if (childrensParent[pos] == node) {
                    childrensParent.swapRemove(pos)
                } else {
                    pos += 1
                }
            }
        }

        val last = this.nodes.size

        return if (last != node) {
            for (childIndex in this.children[last].indices) {
                for (parentIndex in this.parents[childIndex].indices) {
                    if (parentIndex == last) {
                        this.parents[childIndex][parentIndex] = node
                    }
                }
            }

            // Update ids for every parent of the swapped in node.
            for (parentIndex in this.parents[last].indices) {
                for (childIndex in this.children[parentIndex].indices) {
                    if (childIndex == last) {
                        this.children[parentIndex][childIndex] = node
                    }
                }
            }

            this.children.swapRemove(node)
            this.parents.swapRemove(node)

            last
        } else {
            this.children.swapRemove(node)
            this.parents.swapRemove(node)

            null
        }
    }

}