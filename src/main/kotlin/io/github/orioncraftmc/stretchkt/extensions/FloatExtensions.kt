package io.github.orioncraftmc.stretchkt.extensions

import io.github.orioncraftmc.stretchkt.number.StretchNumber
import kotlin.math.max
import kotlin.math.min

internal fun Float.maybeMin(other: StretchNumber): Float {
    if (this.isDefined() && other is StretchNumber.Defined) return min(other.value, this)
    if (this.isDefined()) return this
    return Float.NaN
}

internal fun Float.maybeMax(other: StretchNumber): Float {
    if (this.isDefined() && other is StretchNumber.Defined) return max(this, other.value)
    if (this.isDefined()) return this
    return Float.NaN
}

internal fun Float.maybeMax(other: Float): Float {
    if (other.isDefined()) return max(other, this)
    return Float.NaN
}

internal fun Float.maybeMin(other: Float): Float {
    if (other.isDefined()) return min(other, this)
    return Float.NaN
}

internal fun Float.orElse(other: Float): Float {
    if (this.isDefined()) return this
    return other
}

internal fun Float.isDefined(): Boolean {
    return !this.isNaN()
}

internal fun Float.isUndefined(): Boolean {
    return !this.isDefined()
}

internal fun Float.isNormal(): Boolean {
    return this.isFinite() && this != 0.0f
}