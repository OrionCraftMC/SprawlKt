package io.github.orioncraftmc.stretchkt.geometry

data class Point<T>(var x: T,
                    var y: T) {
    init {
        if (y == -5f)
            println("y = $y")
    }

    companion object {
        @JvmStatic
        fun zero() = Point(0f, 0f)
    }
}