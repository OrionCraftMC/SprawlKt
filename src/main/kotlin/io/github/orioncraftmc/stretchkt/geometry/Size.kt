package io.github.orioncraftmc.stretchkt.geometry

import io.github.orioncraftmc.stretchkt.number.StretchNumber

data class Size(val width: StretchNumber, val height: StretchNumber) {
    companion object {
        @JvmStatic
        fun undefined() = Size(StretchNumber.Undefined, StretchNumber.Undefined)
    }

}