package io.github.orioncraftmc.stretchkt.geometry

import io.github.orioncraftmc.stretchkt.style.enums.FlexDirection
import io.github.orioncraftmc.stretchkt.style.enums.StretchDimension


data class Size<T>(var width: T, var height: T) {
    companion object {
        @JvmStatic
        fun undefined(): Size<StretchDimension> = Size(StretchDimension.Undefined, StretchDimension.Undefined)

        fun zero() = Size(0f, 0f)
    }

    internal fun main(direction: FlexDirection): T {
        return if (direction.isRow()) {
            this.width
        } else {
            this.height
        }
    }

    internal fun setMain(direction: FlexDirection, value: T) {
        if (direction.isRow()) {
            this.width = value
        } else {
            this.height = value
        }
    }

    internal fun cross(direction: FlexDirection): T {
        return if (direction.isRow()) {
            this.height
        } else {
            this.width
        }
    }

    internal fun setCross(direction: FlexDirection, value: T) {
        if (direction.isRow()) {
            this.height = value
        } else {
            this.width = value
        }
    }
}
