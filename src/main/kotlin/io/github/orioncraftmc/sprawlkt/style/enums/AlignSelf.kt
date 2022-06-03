package io.github.orioncraftmc.sprawlkt.style.enums

enum class AlignSelf {
    Auto, FlexStart, FlexEnd, Center, Baseline, Stretch;

    companion object {
        @JvmStatic
        val default: AlignSelf get() = Auto
    }
}