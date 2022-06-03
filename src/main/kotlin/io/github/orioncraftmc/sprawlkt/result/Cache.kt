package io.github.orioncraftmc.sprawlkt.result

import io.github.orioncraftmc.sprawlkt.forest.algo.ComputeResult
import io.github.orioncraftmc.sprawlkt.geometry.Size
import io.github.orioncraftmc.sprawlkt.number.StretchNumber

internal data class Cache(
    val nodeSize: Size<StretchNumber>,
    val parentSize: Size<StretchNumber>,
    val performLayout: Boolean,

    val result: ComputeResult
)