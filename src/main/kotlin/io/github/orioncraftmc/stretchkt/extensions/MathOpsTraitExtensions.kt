package io.github.orioncraftmc.stretchkt.extensions

import io.github.orioncraftmc.stretchkt.number.StretchNumber

operator fun StretchNumber.plus(other: Float): Float {
    return plus(StretchNumber.from(other)).asFloat()
}

operator fun StretchNumber.minus(other: Float): Float {
    return minus(StretchNumber.from(other)).asFloat()
}

operator fun StretchNumber.times(other: Float): Float {
    return times(StretchNumber.from(other)).asFloat()
}

operator fun StretchNumber.div(other: Float): Float {
    return div(StretchNumber.from(other)).asFloat()
}

operator fun Float.plus(other: StretchNumber): StretchNumber {
    return StretchNumber.from(this).plus(other)
}

operator fun Float.minus(other: StretchNumber): StretchNumber {
    return StretchNumber.from(this).minus(other)
}

operator fun Float.times(other: StretchNumber): StretchNumber {
    return StretchNumber.from(this).times(other)
}

operator fun Float.div(other: StretchNumber): StretchNumber {
    return StretchNumber.from(this).div(other)
}
