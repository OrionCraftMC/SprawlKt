package io.github.orioncraftmc.sprawlkt.forest.algo

import io.github.orioncraftmc.sprawlkt.forest.Forest
import io.github.orioncraftmc.sprawlkt.forest.NodeData
import io.github.orioncraftmc.sprawlkt.result.Cache
import kotlin.math.round

internal fun Forest.roundLayout(
    root: NodeData,
    absX: Float,
    absY: Float,
) {
    val layout = root.layout
    val finalAbsX = absX + layout.location.x
    val finalAbsY = absY + layout.location.y

    layout.location.x = round(layout.location.x)
    layout.location.y = round(layout.location.y)

    layout.size.width = round(finalAbsX + layout.size.width) - round(finalAbsX)
    layout.size.height = round(finalAbsY + layout.size.height) - round(finalAbsY)

    for (child in root.children) {
        roundLayout(child, finalAbsX, finalAbsY)
    }
}

internal fun cache(node: NodeData, mainSize: Boolean): Cache? {
    return if (mainSize) {
        node.mainSizeLayoutCache
    } else {
        node.otherLayoutCache
    }
}

internal fun setCache(node: NodeData, mainSize: Boolean, cache: Cache?) {
    if (mainSize) {
        node.mainSizeLayoutCache = cache
    } else {
        node.otherLayoutCache = cache
    }
}