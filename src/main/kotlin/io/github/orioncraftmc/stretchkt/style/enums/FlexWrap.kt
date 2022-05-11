package io.github.orioncraftmc.stretchkt.style.enums

enum class FlexWrap {
    NoWrap,
    Wrap,
    WrapReverse;

    companion object {
        @JvmStatic
        val default: FlexWrap get() = NoWrap
    }
}