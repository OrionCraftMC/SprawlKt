package io.github.orioncraftmc.stretchkt.algo

import com.sun.org.apache.xpath.internal.operations.Bool
import io.github.orioncraftmc.stretchkt.geometry.Rect
import io.github.orioncraftmc.stretchkt.geometry.Size
import io.github.orioncraftmc.stretchkt.node.NodeId

internal data class FlexItem(
    val node: NodeId,

    val size: Size<Number>,
    val minSize: Size<Number>,
    val maxSize: Size<Number>,

    val position: Rect<Number>,
    val margin: Rect<Float>,
    val padding: Rect<Float>,
    val border: Rect<Float>,

    val flexBasis: Float,
    val innerFlexBasis: Float,
    val violation: Float,
    val frozen: Bool,

    val hypotheticalInnerSize: Size<Float>,
    val hypotheticalOuterSize: Size<Float>,
    val targetSize: Size<Float>,
    val outerTargetSize: Size<Float>,

    val baseline: Float,

    // temporary values for holding offset in the main / cross direction.
    // offset is the relative position from the item's natural flow position based on
    // relative position values, alignment, and justification. Does not include margin/padding/border.
    val offsetMain: Float,
    val offsetCross: Float,
)

