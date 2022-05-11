package io.github.orioncraftmc.stretchkt.style

enum class PositionType {
    Relative,
    Absolute;

    companion object {
        @JvmStatic
        val default: PositionType get() = Relative
    }
}