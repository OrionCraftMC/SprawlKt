package io.github.orioncraftmc.stretchkt.style.enums

enum class AlignItems {
    FlexStart, FlexEnd, Center, Baseline, Stretch;

    companion object {
        @JvmStatic
        val default: AlignItems get() = Stretch
    }
}