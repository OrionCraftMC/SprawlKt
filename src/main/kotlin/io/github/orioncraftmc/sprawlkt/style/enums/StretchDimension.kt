package io.github.orioncraftmc.sprawlkt.style.enums

import io.github.orioncraftmc.sprawlkt.number.StretchNumber

sealed class StretchDimension {

    abstract fun resolve(parentDimension: StretchNumber): StretchNumber

    val isDefined get() = this is Points || this is Percent

    object Undefined : StretchDimension() {
        override fun resolve(parentDimension: StretchNumber): StretchNumber = StretchNumber.Undefined
    }

    object Auto : StretchDimension() {
        override fun resolve(parentDimension: StretchNumber): StretchNumber = StretchNumber.Undefined
    }

    class Points(private val points: Float) : StretchDimension() {
        override fun resolve(parentDimension: StretchNumber): StretchNumber = StretchNumber.from(points)
    }

    class Percent(private val percent: Float) : StretchDimension() {
        override fun resolve(parentDimension: StretchNumber): StretchNumber =
            parentDimension * StretchNumber.from(percent)
    }
}


