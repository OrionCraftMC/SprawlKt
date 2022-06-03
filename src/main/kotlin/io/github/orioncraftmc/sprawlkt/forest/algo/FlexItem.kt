package io.github.orioncraftmc.sprawlkt.forest.algo

import io.github.orioncraftmc.sprawlkt.forest.NodeData
import io.github.orioncraftmc.sprawlkt.geometry.Rect
import io.github.orioncraftmc.sprawlkt.geometry.Size
import io.github.orioncraftmc.sprawlkt.number.StretchNumber

internal data class FlexItem(
    internal val node: NodeData,

    internal val size: Size<StretchNumber>,
    internal val minSize: Size<StretchNumber>,
    internal val maxSize: Size<StretchNumber>,

    internal val position: Rect<StretchNumber>,
    internal val margin: Rect<Float>,
    internal val padding: Rect<Float>,
    internal val border: Rect<Float>,

    internal var flexBasis: Float,
    internal var innerFlexBasis: Float,
    internal var violation: Float,
    internal var frozen: Boolean,

    internal val hypotheticalInnerSize: Size<Float>,
    internal val hypotheticalOuterSize: Size<Float>,
    internal val targetSize: Size<Float>,
    internal val outerTargetSize: Size<Float>,

    internal var baseline: Float,

    // temporary values for holding offset in the main / cross direction.
    // offset is the relative position from the item's natural flow position based on
    // relative position values, alignment, and justification. Does not include margin/padding/border.
    internal var offsetMain: Float,
    internal var offsetCross: Float,
)

