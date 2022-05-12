package io.github.orioncraftmc.stretchkt.algo

import io.github.orioncraftmc.stretchkt.geometry.Size

@JvmInline
value class ComputeResult(val size: Size<Float>) {
    fun clone(): ComputeResult = ComputeResult(size.copy())
}

