package io.github.orioncraftmc.stretchkt.algo

import io.github.orioncraftmc.stretchkt.geometry.Rect
import io.github.orioncraftmc.stretchkt.geometry.Size
import io.github.orioncraftmc.stretchkt.node.NodeId
import io.github.orioncraftmc.stretchkt.number.StretchNumber

internal data class FlexItem(
    val node: NodeId,

    val size: Size<StretchNumber>,
    val minSize: Size<StretchNumber>,
    val maxSize: Size<StretchNumber>,

    val position: Rect<StretchNumber>,
    val margin: Rect<Float>,
    val padding: Rect<Float>,
    val border: Rect<Float>,

    var flexBasis: Float,
    var innerFlexBasis: Float,
    val violation: Float,
    var frozen: Boolean,

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

