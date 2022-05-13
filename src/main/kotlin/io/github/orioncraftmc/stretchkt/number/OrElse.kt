package io.github.orioncraftmc.stretchkt.number

sealed interface OrElse<T> {
    fun orElse(other: T): T
}