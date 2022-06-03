package io.github.orioncraftmc.sprawlkt.forest.algo

import io.github.orioncraftmc.sprawlkt.geometry.Size

@JvmInline
internal value class ComputeResult(val size: Size<Float>) {
    fun clone(): ComputeResult = ComputeResult(size.copy())
}

