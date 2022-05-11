package io.github.orioncraftmc.stretchkt.node

import io.github.orioncraftmc.stretchkt.geometry.Size
import io.github.orioncraftmc.stretchkt.number.StretchNumber

fun interface MeasureFunc {
    fun measure(size: Size<StretchNumber>): Size<Float>
}