package io.github.orioncraftmc.stretchkt.style

enum class FlexWrap {
    NoWrap,
    Wrap,
    WrapReverse;

    companion object {
        @JvmStatic
        val default: FlexWrap get() = NoWrap
    }
}