package io.github.orioncraftmc.sprawlkt.style.enums

enum class FlexWrap {
    NoWrap,
    Wrap,
    WrapReverse;

    companion object {
        @JvmStatic
        val default: FlexWrap get() = NoWrap
    }
}