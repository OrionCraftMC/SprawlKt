package io.github.orioncraftmc.stretchkt.style

enum class AlignSelf {
    Auto, FlexStart, FlexEnd, Center, Baseline, Stretch;

    companion object {
        @JvmStatic
        val default: AlignSelf get() = Auto
    }
}