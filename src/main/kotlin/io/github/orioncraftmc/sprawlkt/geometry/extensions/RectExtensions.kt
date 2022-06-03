package io.github.orioncraftmc.sprawlkt.geometry

import io.github.orioncraftmc.sprawlkt.number.StretchNumber
import io.github.orioncraftmc.sprawlkt.style.enums.FlexDirection
import io.github.orioncraftmc.sprawlkt.style.enums.StretchDimension

internal val defaultDimensionRect
    get() = Rect<StretchDimension>(
        StretchDimension.Undefined,
        StretchDimension.Undefined,
        StretchDimension.Undefined,
        StretchDimension.Undefined
    )

val Rect<Float>.horizontal: Float
    get() = start + end

val Rect<Float>.vertical: Float
    get() = top + bottom /* Together at last */

val Rect<StretchNumber>.horizontal: StretchNumber
    get() = start + end

val Rect<StretchNumber>.vertical: StretchNumber
    get() = top + bottom /* Together at last */

fun Rect<StretchNumber>.main(direction: FlexDirection): StretchNumber {
    return if (direction.isRow()) {
        this.horizontal
    } else {
        this.vertical
    }
}

fun Rect<StretchNumber>.cross(direction: FlexDirection): StretchNumber {
    return if (direction.isRow()) {
        this.vertical
    } else {
        this.horizontal
    }
}

fun Rect<Float>.main(direction: FlexDirection): Float {
    return if (direction.isRow()) {
        this.horizontal
    } else {
        this.vertical
    }
}

fun Rect<Float>.cross(direction: FlexDirection): Float {
    return if (direction.isRow()) {
        this.vertical
    } else {
        this.horizontal
    }
}

