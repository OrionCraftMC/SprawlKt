package io.github.orioncraftmc.stretchkt.traits

interface MathOpsTrait<T> {
    operator fun plus(other: MathOpsTrait<T>): T {
        return this.plus(other as T)
    }

    operator fun minus(other: MathOpsTrait<T>): T {
        return this.minus(other as T)
    }

    operator fun times(other: MathOpsTrait<T>): T {
        return this.times(other as T)
    }

    operator fun div(other: MathOpsTrait<T>): T {
        return this.div(other as T)
    }

    operator fun plus(other: T): T
    operator fun minus(other: T): T
    operator fun times(other: T): T
    operator fun div(other: T): T
}