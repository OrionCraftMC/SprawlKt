package io.github.orioncraftmc.sprawlkt.style.enums

enum class Display {
    Flex,
    None;

    companion object {
        @JvmStatic
        val default: Display get() = Flex
    }
}