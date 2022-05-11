package io.github.orioncraftmc.stretchkt.geometry

import io.github.orioncraftmc.stretchkt.style.StretchDimension

data class Size<T>(val width: T, val height: T) {
    companion object {
        @JvmStatic
        fun undefined() = Size(StretchDimension.Undefined, StretchDimension.Undefined)

        fun zero() = Size(0f, 0f)
    }
}

