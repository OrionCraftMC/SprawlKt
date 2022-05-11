package io.github.orioncraftmc.stretchkt.style

enum class AlignItems {
    FlexStart, FlexEnd, Center, Baseline, Stretch;

    companion object {
        @JvmStatic
        val default: AlignItems get() = Stretch
    }
}