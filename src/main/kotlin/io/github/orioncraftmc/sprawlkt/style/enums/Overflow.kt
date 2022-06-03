package io.github.orioncraftmc.sprawlkt.style.enums

enum class Overflow {
    Visible,
    Hidden,
    Scroll;

    companion object {
        @JvmStatic
        val default: Overflow get() = Visible
    }
}