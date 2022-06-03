package io.github.orioncraftmc.sprawlkt.forest

import io.github.orioncraftmc.sprawlkt.node.Layout
import io.github.orioncraftmc.sprawlkt.node.MeasureFunc
import io.github.orioncraftmc.sprawlkt.node.Node
import io.github.orioncraftmc.sprawlkt.result.Cache
import io.github.orioncraftmc.sprawlkt.style.Style

internal data class NodeData(
    internal var style: Style,
    internal var measure: MeasureFunc? = null,
    internal var layout: Layout = Layout(),
    internal var mainSizeLayoutCache: Cache? = null,
    internal var otherLayoutCache: Cache? = null,
    internal var isDirty: Boolean = true,

    internal val children: MutableList<NodeData> = mutableListOf(),
    internal val parents: MutableList<NodeData> = mutableListOf(),

    internal var exposedNode: Node? = null // The node that is exposed to library users

) {
    override fun toString(): String {
        return "NodeData(style=$style, measure=$measure, layout=$layout, mainSizeLayoutCache=$mainSizeLayoutCache, otherLayoutCache=$otherLayoutCache, isDirty=$isDirty)"
    }
}
