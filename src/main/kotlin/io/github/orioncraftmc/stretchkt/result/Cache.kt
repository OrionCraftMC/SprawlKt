package io.github.orioncraftmc.stretchkt.result

import io.github.orioncraftmc.stretchkt.forest.algo.ComputeResult
import io.github.orioncraftmc.stretchkt.geometry.Size
import io.github.orioncraftmc.stretchkt.number.StretchNumber

internal data class Cache(
    val nodeSize: Size<StretchNumber>,
    val parentSize: Size<StretchNumber>,
    val performLayout: Boolean,

    val result: ComputeResult
)