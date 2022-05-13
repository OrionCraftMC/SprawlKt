package io.github.orioncraftmc.stretchkt.forest

import io.github.orioncraftmc.stretchkt.node.MeasureFunc
import io.github.orioncraftmc.stretchkt.result.Cache
import io.github.orioncraftmc.stretchkt.result.Layout
import io.github.orioncraftmc.stretchkt.style.Style

internal data class NodeData(
    val style: Style,
    val measure: MeasureFunc? = null,
    var layout: Layout = Layout(),
    var mainSizeLayoutCache: Cache? = null,
    var otherLayoutCache: Cache? = null,
    var isDirty: Boolean = true,

    val children: HashSet<NodeData> = HashSet(),
    val parents: HashSet<NodeData> = HashSet()

) {
    fun newLeaf(style: Style, measure: MeasureFunc?): NodeData {
        return copy(
            style = style,
            measure = measure,
            isDirty = true,
            children = HashSet(),
            parents = HashSet()
        )
    }
}

