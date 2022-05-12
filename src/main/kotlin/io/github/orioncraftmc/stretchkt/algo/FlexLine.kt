package io.github.orioncraftmc.stretchkt.algo

internal data class FlexLine(
    val items: HashSet<FlexItem> = HashSet(),
    val crossSize: Float,
    val offsetCross: Float,
)