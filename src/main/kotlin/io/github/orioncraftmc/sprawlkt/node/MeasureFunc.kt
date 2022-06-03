package io.github.orioncraftmc.sprawlkt.node

import io.github.orioncraftmc.sprawlkt.geometry.Size
import io.github.orioncraftmc.sprawlkt.number.StretchNumber

fun interface MeasureFunc {
    fun measure(size: Size<StretchNumber>): Size<Float>
}