package io.github.orioncraftmc.sprawlkt.geometry

data class Point<T>(var x: T,
                    var y: T) {
    companion object {
        @JvmStatic
        fun zero() = Point(0f, 0f)
    }
}