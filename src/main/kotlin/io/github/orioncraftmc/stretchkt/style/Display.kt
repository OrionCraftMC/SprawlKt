package io.github.orioncraftmc.stretchkt.style

enum class Display {
    Flex,
    None;

    companion object {
        @JvmStatic
        val default: Display get() = Flex
    }
}