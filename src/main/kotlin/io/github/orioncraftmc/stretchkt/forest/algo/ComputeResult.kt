package io.github.orioncraftmc.stretchkt.forest.algo

import io.github.orioncraftmc.stretchkt.geometry.Size

@JvmInline
internal value class ComputeResult(val size: Size<Float>) {
    fun clone(): ComputeResult = ComputeResult(size.copy())
}

