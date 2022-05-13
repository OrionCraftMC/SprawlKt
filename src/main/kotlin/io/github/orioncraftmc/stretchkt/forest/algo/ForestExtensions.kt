package io.github.orioncraftmc.stretchkt.forest.algo

import io.github.orioncraftmc.stretchkt.forest.Forest
import io.github.orioncraftmc.stretchkt.forest.NodeData
import io.github.orioncraftmc.stretchkt.result.Cache
import kotlin.math.round

internal fun Forest.roundLayout(
root: NodeData,
absX: Float,
absY: Float,
) {
    val layout = root.layout;
    val finalAbsX = absX + layout.location.x
    val finalAbsY = absY + layout.location.y

    layout.location.x = round(layout.location.x);
    layout.location.y = round(layout.location.y);

    layout.size.width = round(finalAbsX + layout.size.width) - round(finalAbsX);
    layout.size.height = round(finalAbsY + layout.size.height) - round(finalAbsY);

    for (child in root.children) {
        roundLayout(child, finalAbsX, finalAbsY);
    }
}

internal fun Forest.cache(node: NodeData, mainSize: Boolean): Cache? {
    return if (mainSize) {
        node.mainSizeLayoutCache
    } else {
        node.otherLayoutCache
    }
}

internal fun Forest.setCache(node: NodeData, mainSize: Boolean, cache: Cache?) {
    if (mainSize) {
        node.mainSizeLayoutCache = cache
    } else {
        node.otherLayoutCache = cache
    }
}