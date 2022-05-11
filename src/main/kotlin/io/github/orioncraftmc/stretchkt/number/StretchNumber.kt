package io.github.orioncraftmc.stretchkt.number

import io.github.orioncraftmc.stretchkt.traits.MathOpsTrait
import kotlin.math.max
import kotlin.math.min

sealed class StretchNumber : OrElse<Float>, MathOpsTrait<StretchNumber> {

    abstract fun orElse(other: StretchNumber): StretchNumber

    abstract val isDefined: Boolean

    fun maybeMin(other: StretchNumber): StretchNumber {
        if (this is Defined && other is Defined) return from(min(this.value, other.value))
        if (this is Defined) return this
        return other
    }

    fun maybeMax(other: StretchNumber): StretchNumber {
        if (this is Defined && other is Defined) return from(max(this.value, other.value))
        if (this is Defined) return this
        return other
    }


    abstract fun asFloat(): Float

    class Defined internal constructor(val value: Float) : StretchNumber() {
        override fun orElse(other: Float): Float = value
        override fun orElse(other: StretchNumber): StretchNumber = this

        override val isDefined get() = true
        override fun plus(other: StretchNumber): StretchNumber {
            if (other is Defined) return from(value + other.value)
            return this
        }

        override fun minus(other: StretchNumber): StretchNumber {
            if (other is Defined) return from(value - other.value)
            return this
        }

        override fun times(other: StretchNumber): StretchNumber {
            if (other is Defined) return from(value * other.value)
            return this
        }

        override fun div(other: StretchNumber): StretchNumber {
            if (other is Defined) return from(value / other.value)
            return this
        }

        override fun asFloat(): Float = value

        override fun toString(): String {
            return "Defined($value)"
        }
    }

    object Undefined : StretchNumber() {
        override fun orElse(other: Float): Float = other
        override fun orElse(other: StretchNumber): StretchNumber = other

        override val isDefined get() = false
        override fun plus(other: StretchNumber): StretchNumber = Undefined

        override fun minus(other: StretchNumber): StretchNumber = Undefined

        override fun times(other: StretchNumber): StretchNumber = Undefined

        override fun div(other: StretchNumber): StretchNumber = Undefined

        override fun asFloat(): Float = Float.NaN

        override fun toString(): String {
            return "Undefined"
        }
    }

    companion object {
        @JvmStatic
        fun from(value: Float): StretchNumber = if (value.isNaN()) Undefined else Defined(value)
    }
}

