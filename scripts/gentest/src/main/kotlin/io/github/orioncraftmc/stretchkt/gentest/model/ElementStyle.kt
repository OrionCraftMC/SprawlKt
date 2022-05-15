package io.github.orioncraftmc.stretchkt.gentest.model

data class ElementStyle(
    val display: String? = null,

    val positionType: String? = null,
    val direction: String? = null,
    val flexDirection: String? = null,

    val flexWrap: String? = null,
    val overflow: String? = null,

    val alignItems: String? = null,
    val alignSelf: String? = null,
    val alignContent: String? = null,

    val justifyContent: String? = null,

    val flexGrow: Float? = null,
    val flexShrink: Float? = null,
    val flexBasis: Dimension? = null,

    val size: Size? = null,
    val minSize: Size? = null,
    val maxSize: Size? = null,

    val margin: Edge? = null,
    val padding: Edge? = null,
    val border: Edge? = null,

    val position: Edge? = null,
)