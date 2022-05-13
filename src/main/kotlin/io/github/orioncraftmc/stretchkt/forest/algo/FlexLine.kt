package io.github.orioncraftmc.stretchkt.forest.algo

internal data class FlexLine(
    internal val items: List<FlexItem>,
    internal var crossSize: Float,
    internal var offsetCross: Float,
)