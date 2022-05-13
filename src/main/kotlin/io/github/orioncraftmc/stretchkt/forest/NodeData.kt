package io.github.orioncraftmc.stretchkt.forest

import io.github.orioncraftmc.stretchkt.node.Layout
import io.github.orioncraftmc.stretchkt.node.MeasureFunc
import io.github.orioncraftmc.stretchkt.node.Node
import io.github.orioncraftmc.stretchkt.result.Cache
import io.github.orioncraftmc.stretchkt.style.Style

internal data class NodeData(
    var style: Style,
    var measure: MeasureFunc? = null,
    var layout: Layout = Layout(),
    var mainSizeLayoutCache: Cache? = null,
    var otherLayoutCache: Cache? = null,
    var isDirty: Boolean = true,

    val children: MutableList<NodeData> = mutableListOf(),
    val parents: MutableList<NodeData> = mutableListOf(),

    var exposedNode: Node? = null // The node that is exposed to library users

)
