package io.github.orioncraftmc.stretchkt.geometry

import io.github.orioncraftmc.stretchkt.number.StretchNumber
import io.github.orioncraftmc.stretchkt.style.enums.FlexDirection
import io.github.orioncraftmc.stretchkt.style.enums.StretchDimension


data class Size<T>(var width: T, var height: T) {
    companion object {

        @JvmStatic
        fun zero() = Size(0f, 0f)

        @JvmStatic
        fun zeroNumber() = Size(StretchNumber.zero, StretchNumber.zero)

        @JvmStatic
        fun undefinedDimension(): Size<StretchDimension> = Size(StretchDimension.Undefined, StretchDimension.Undefined)

        @JvmStatic
        fun undefinedNumber(): Size<StretchNumber> = Size(StretchNumber.Undefined, StretchNumber.Undefined)

        @JvmStatic
        fun <T> of(width: T, height: T): Size<T> = Size(width, height)

        @JvmStatic
        fun ofPoints(width: Float, height: Float): Size<StretchDimension> = Size(StretchDimension.Points(width), StretchDimension.Points(height))

        @JvmStatic
        fun ofPercent(width: Float, height: Float): Size<StretchDimension> = Size(StretchDimension.Percent(width), StretchDimension.Percent(height))
    }

    fun <R> map(transform: (T) -> R): Size<R> {
        return Size(
            width = transform(width),
            height = transform(height)
        )
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
