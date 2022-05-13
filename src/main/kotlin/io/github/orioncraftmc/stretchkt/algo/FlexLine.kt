package io.github.orioncraftmc.stretchkt.algo

internal data class FlexLine(
    val items: List<FlexItem>,
    var crossSize: Float,
    val offsetCross: Float,
)