package io.github.orioncraftmc.sprawlkt.style.enums

enum class JustifyContent {
    FlexStart,
    FlexEnd,
    Center,
    SpaceBetween,
    SpaceAround,
    SpaceEvenly;

    companion object {
        @JvmStatic
        val default: JustifyContent get() = FlexStart
    }
}

