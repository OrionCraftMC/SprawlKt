package io.github.orioncraftmc.sprawlkt.forest.algo

import io.github.orioncraftmc.sprawlkt.extensions.maybeMax
import io.github.orioncraftmc.sprawlkt.extensions.maybeMin
import io.github.orioncraftmc.sprawlkt.forest.Forest
import io.github.orioncraftmc.sprawlkt.forest.NodeData
import io.github.orioncraftmc.sprawlkt.geometry.Point
import io.github.orioncraftmc.sprawlkt.geometry.Size
import io.github.orioncraftmc.sprawlkt.geometry.resolve
import io.github.orioncraftmc.sprawlkt.geometry.toStretchNumberSize
import io.github.orioncraftmc.sprawlkt.node.Layout
import io.github.orioncraftmc.sprawlkt.number.StretchNumber


internal fun Forest.compute(root: NodeData, size: Size<StretchNumber>) {
    val style = root.style
    val hasRootMinMax =
        style.minSize.width.isDefined || style.minSize.height.isDefined || style.maxSize.width.isDefined || style.maxSize.height.isDefined

    val result = if (hasRootMinMax) {
        val firstPass = computeInternal(
            node = root, nodeSize = style.size.resolve(size), parentSize = size, performLayout = false, mainSize = true
        )

        computeInternal(
            node = root,
            nodeSize = Size(
                width = firstPass.size.width.maybeMax(style.minSize.width.resolve(size.width))
                    .maybeMin(style.maxSize.width.resolve(size.width)),
                height = firstPass.size.height.maybeMax(style.minSize.height.resolve(size.height))
                    .maybeMin(style.maxSize.height.resolve(size.height)),
            ).toStretchNumberSize(),
            parentSize = size,
            performLayout = true,
            mainSize = true,
        )
    } else {
        computeInternal(
            node = root, nodeSize = style.size.resolve(size), parentSize = size, performLayout = true, mainSize = true
        )
    }

    root.layout = Layout(order = 0u, size = result.size, location = Point.zero())

    roundLayout(root, 0.0f, 0.0f)
}