package io.github.orioncraftmc.stretchkt.style

import io.github.orioncraftmc.stretchkt.geometry.Rect
import io.github.orioncraftmc.stretchkt.geometry.Size
import io.github.orioncraftmc.stretchkt.geometry.defaultDimensionRect
import io.github.orioncraftmc.stretchkt.style.enums.*

data class Style(
    val display: Display = Display.default,
    val positionType: PositionType = PositionType.default,
    val direction: Direction = Direction.default,
    val flexDirection: FlexDirection = FlexDirection.default,
    val flexWrap: FlexWrap = FlexWrap.default,
    val overflow: Overflow = Overflow.default,
    val alignItems: AlignItems = AlignItems.default,
    val alignSelf: AlignSelf = AlignSelf.default,
    val alignContent: AlignContent = AlignContent.default,
    val justifyContent: JustifyContent = JustifyContent.default,
    val position: Rect<StretchDimension> = defaultDimensionRect,
    val margin: Rect<StretchDimension> = defaultDimensionRect,
    val padding: Rect<StretchDimension> = defaultDimensionRect,
    val border: Rect<StretchDimension> = defaultDimensionRect,
    val flexGrow: Float = 0f,
    val flexShrink: Float = 1.0f,
    val flexBasis: StretchDimension = StretchDimension.Auto,
    val size: Size<StretchDimension> = Size.undefined(),
    val minSize: Size<StretchDimension> = Size.undefined(),
    val maxSize: Size<StretchDimension> = Size.undefined(),
    val aspectRatio: Number,
) {

    fun minMainSize(direction: FlexDirection): StretchDimension {
        return if (direction.isRow()) {
            minSize.width
        } else {
            minSize.height
        }
    }


    fun maxMainSize(direction: FlexDirection): StretchDimension {
        return if (direction.isRow()) {
            maxSize.width
        } else {
            maxSize.height
        }
    }

    fun mainMarginStart(direction: FlexDirection): StretchDimension {
        return if (direction.isRow()) {
            margin.start
        } else {
            margin.top
        }
    }

    fun mainMarginEnd(direction: FlexDirection): StretchDimension {
        return if (direction.isRow()) {
            margin.end
        } else {
            margin.bottom
        }
    }

    fun crossSize(direction: FlexDirection): StretchDimension {
        return if (direction.isRow()) {
            size.height
        } else {
            size.width
        }
    }

    fun minCrossSize(direction: FlexDirection): StretchDimension {
        return if (direction.isRow()) {
            minSize.height
        } else {
            minSize.width
        }
    }

    fun maxCrossSize(direction: FlexDirection): StretchDimension {
        return if (direction.isRow()) {
            maxSize.height
        } else {
            maxSize.width
        }
    }

    fun crossMarginStart(direction: FlexDirection): StretchDimension {
        return if (direction.isRow()) {
            margin.top
        } else {
            margin.start
        }
    }

    fun crossMarginEnd(direction: FlexDirection): StretchDimension {
        return if (direction.isRow()) {
            margin.bottom
        } else {
            margin.end
        }
    }

    fun alignSelf(parent: Style): AlignSelf {
        return if (alignSelf == AlignSelf.Auto) {
            when (parent.alignItems) {
                AlignItems.FlexStart -> AlignSelf.FlexStart
                AlignItems.FlexEnd -> AlignSelf.FlexEnd
                AlignItems.Center -> AlignSelf.Center
                AlignItems.Baseline -> AlignSelf.Baseline
                AlignItems.Stretch -> AlignSelf.Stretch
            }
        } else {
            alignSelf
        }
    }

}
