package io.github.orioncraftmc.stretchkt.style.enums

enum class Direction {
    Inherit,
    Ltr,
    Rtl;

    companion object {
        @JvmStatic
        val default: Direction get() = Inherit
    }
}