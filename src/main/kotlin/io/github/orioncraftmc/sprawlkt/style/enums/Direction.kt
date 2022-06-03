package io.github.orioncraftmc.sprawlkt.style.enums

enum class Direction {
    Inherit,
    Ltr,
    Rtl;

    companion object {
        @JvmStatic
        val default: Direction get() = Inherit
    }
}