package io.github.orioncraftmc.sprawlkt.number

sealed interface OrElse<T> {
    fun orElse(other: T): T
}