package io.github.orioncraftmc.sprawlkt.style.enums

enum class PositionType {
    Relative,
    Absolute;

    companion object {
        @JvmStatic
        val default: PositionType get() = Relative
    }
}