package io.github.orioncraftmc.stretchkt.number

interface OrElse<T> {
    fun orElse(other: T): T
}