package io.github.orioncraftmc.stretchkt.style.enums

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