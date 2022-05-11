package io.github.orioncraftmc.stretchkt.forest

import io.github.orioncraftmc.stretchkt.node.MeasureFunc
import io.github.orioncraftmc.stretchkt.result.Cache
import io.github.orioncraftmc.stretchkt.result.Layout
import io.github.orioncraftmc.stretchkt.style.Style

internal data class NodeData(
    val style: Style,
    val measure: MeasureFunc? = null,
    val layout: Layout = Layout(),
    val mainSizeLayoutCache: Cache? = null,
    val otherLayoutCache: Cache? = null,
    val isDirty: Boolean = true
) {
    fun newLeaf(style: Style, measure: MeasureFunc?): NodeData {
        return copy(
            style = style,
            measure = measure,
            isDirty = true
        )
    }
}

