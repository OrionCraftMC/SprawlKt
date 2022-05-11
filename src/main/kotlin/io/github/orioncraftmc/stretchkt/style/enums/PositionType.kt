package io.github.orioncraftmc.stretchkt.style.enums

enum class PositionType {
    Relative,
    Absolute;

    companion object {
        @JvmStatic
        val default: PositionType get() = Relative
    }
}