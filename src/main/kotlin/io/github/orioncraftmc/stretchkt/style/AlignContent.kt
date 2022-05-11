package io.github.orioncraftmc.stretchkt.style

enum class AlignContent {
    FlexStart,
    FlexEnd,
    Center,
    Stretch,
    SpaceBetween,
    SpaceAround;

    companion object {
        @JvmStatic
        val default: AlignContent get() = Stretch
    }
}