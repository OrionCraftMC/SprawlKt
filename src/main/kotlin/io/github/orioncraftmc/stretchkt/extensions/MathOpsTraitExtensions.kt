package io.github.orioncraftmc.stretchkt.extensions

import io.github.orioncraftmc.stretchkt.number.StretchNumber

operator fun StretchNumber.plus(other: Float): Float {
    if (this is StretchNumber.Defined) return this.value + other
    return other
}

operator fun StretchNumber.minus(other: Float): Float {
    if (this is StretchNumber.Defined) return this.value - other
    return -other
}

operator fun StretchNumber.times(other: Float): Float {
    if (this is StretchNumber.Defined) return this.value * other
    return other
}

operator fun StretchNumber.div(other: Float): Float {
    if (this is StretchNumber.Defined) return this.value / other
    return 1 / other
}