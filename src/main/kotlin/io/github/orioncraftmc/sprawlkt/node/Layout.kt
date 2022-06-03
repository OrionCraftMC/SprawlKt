package io.github.orioncraftmc.sprawlkt.node

import io.github.orioncraftmc.sprawlkt.geometry.Point
import io.github.orioncraftmc.sprawlkt.geometry.Size

data class Layout(
    val order: UInt,
    val size: Size<Float>,
    val location: Point<Float>
) {
    internal constructor() : this(
        order = 0u,
        size = Size.zero(),
        location = Point.zero()
    )

}