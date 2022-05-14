package io.github.orioncraftmc.stretchkt.style

import io.github.orioncraftmc.stretchkt.geometry.Rect
import io.github.orioncraftmc.stretchkt.geometry.Size
import io.github.orioncraftmc.stretchkt.geometry.defaultDimensionRect
import io.github.orioncraftmc.stretchkt.number.StretchNumber
import io.github.orioncraftmc.stretchkt.style.enums.*

data class Style(
    var display: Display = Display.default,
    var positionType: PositionType = PositionType.default,
    var direction: Direction = Direction.default,
    var flexDirection: FlexDirection = FlexDirection.default,
    var flexWrap: FlexWrap = FlexWrap.default,
    var overflow: Overflow = Overflow.default,
    var alignItems: AlignItems = AlignItems.default,
    var alignSelf: AlignSelf = AlignSelf.default,
    var alignContent: AlignContent = AlignContent.default,
    var justifyContent: JustifyContent = JustifyContent.default,
    var position: Rect<StretchDimension> = defaultDimensionRect,
    var margin: Rect<StretchDimension> = defaultDimensionRect,
    var padding: Rect<StretchDimension> = defaultDimensionRect,
    var border: Rect<StretchDimension> = defaultDimensionRect,
    var flexGrow: Float = 0f,
    var flexShrink: Float = 1.0f,
    var flexBasis: StretchDimension = StretchDimension.Auto,
    var size: Size<StretchDimension> = Size.autoDimension(),
    var minSize: Size<StretchDimension> = Size.autoDimension(),
    var maxSize: Size<StretchDimension> = Size.autoDimension(),
    var aspectRatio: StretchNumber = StretchNumber.Undefined,
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
