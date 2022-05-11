package io.github.orioncraftmc.stretchkt.geometry

data class Point<T>(val x: T, val y: T) {
    companion object {
        @JvmStatic
        fun zero() = Point(0f, 0f)
    }
}