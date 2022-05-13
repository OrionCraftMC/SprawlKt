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

operator fun Float.plus(other: StretchNumber): StretchNumber {
    if (other is StretchNumber.Defined) return StretchNumber.from(this + other.value)
    return StretchNumber.from(this)
}

operator fun Float.minus(other: StretchNumber): StretchNumber {
    if (other is StretchNumber.Defined) return StretchNumber.from(this - other.value)
    return StretchNumber.from(-this)
}

operator fun Float.times(other: StretchNumber): StretchNumber {
    if (other is StretchNumber.Defined) return StretchNumber.from(this * other.value)
    return StretchNumber.from(this)
}

operator fun Float.div(other: StretchNumber): StretchNumber {
    if (other is StretchNumber.Defined) return StretchNumber.from(this / other.value)
    return StretchNumber.from(1 / this)
}
