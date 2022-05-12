package io.github.orioncraftmc.stretchkt.extensions

import io.github.orioncraftmc.stretchkt.number.StretchNumber
import kotlin.math.max
import kotlin.math.min

internal fun Float.maybeMin(other: StretchNumber): Float {
    if (this.isFinite() && other is StretchNumber.Defined) return min(this, other.value)
    if (this.isFinite()) return this
    return other.asFloat()
}

internal fun Float.maybeMax(other: StretchNumber): Float {
    if (this.isFinite() && other is StretchNumber.Defined) return max(this, other.value)
    if (this.isFinite()) return this
    return other.asFloat()
}

internal fun Float.maybeMax(other: Float): Float {
    if (this.isFinite() && !other.isFinite()) return max(this, other)
    if (this.isFinite()) return this
    return other
}

internal fun Float.maybeMin(other: Float): Float {
    if (this.isFinite() && !other.isFinite()) return min(this, other)
    if (this.isFinite()) return this
    return other
}

internal fun Float.orElse(other: Float): Float {
    if (this.isFinite()) return this
    return other
}

internal fun Float.isDefined(): Boolean {
    return this.isFinite()
}

internal fun Float.isUndefined(): Boolean {
    return !this.isDefined()
}

internal fun Float.isNormal(): Boolean {
    return this.isFinite() && this != 0.0f
}