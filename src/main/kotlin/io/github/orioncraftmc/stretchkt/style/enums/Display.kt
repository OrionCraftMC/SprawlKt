package io.github.orioncraftmc.stretchkt.style.enums

enum class Display {
    Flex,
    None;

    companion object {
        @JvmStatic
        val default: Display get() = Flex
    }
}