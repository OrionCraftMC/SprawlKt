package io.github.orioncraftmc.stretchkt.extensions

import io.github.orioncraftmc.stretchkt.number.StretchNumber
import kotlin.math.max
import kotlin.math.min

fun Float.maybeMin(other: StretchNumber): Float {
    if (this.isFinite() && other is StretchNumber.Defined) return min(this, other.value)
    if (this.isFinite()) return this
    return other.asFloat()
}

fun Float.maybeMax(other: StretchNumber): Float {
    if (this.isFinite() && other is StretchNumber.Defined) return max(this, other.value)
    if (this.isFinite()) return this
    return other.asFloat()
}

fun Float.maybeMax(other: Float): Float {
    if (this.isFinite() && !other.isFinite()) return max(this, other)
    if (this.isFinite()) return this
    return other
}

fun Float.maybeMin(other: Float): Float {
    if (this.isFinite() && !other.isFinite()) return min(this, other)
    if (this.isFinite()) return this
    return other
}

fun Float.orElse(other: Float): Float {
    if (this.isFinite()) return this
    return other
}

fun Float.isDefined(): Boolean {
    return this.isFinite()
}

fun Float.isUndefined(): Boolean {
    return !this.isDefined()
}
