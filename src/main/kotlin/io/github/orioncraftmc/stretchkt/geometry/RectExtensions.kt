package io.github.orioncraftmc.stretchkt.geometry

import io.github.orioncraftmc.stretchkt.style.enums.FlexDirection
import io.github.orioncraftmc.stretchkt.style.enums.StretchDimension
import io.github.orioncraftmc.stretchkt.traits.MathOpsTrait

internal val defaultDimensionRect
    get() = Rect<StretchDimension>(
        StretchDimension.Undefined,
        StretchDimension.Undefined,
        StretchDimension.Undefined,
        StretchDimension.Undefined
    )

val <R, T : MathOpsTrait<R>> Rect<T>.horizontal: R
    get() = start + end

val <R, T : MathOpsTrait<R>> Rect<T>.vertical: R
    get() = top + bottom /* Together at last */

fun <R, T : MathOpsTrait<R>> Rect<T>.main(direction: FlexDirection): R {
    return if (direction.isRow()) {
        this.horizontal
    } else {
        this.vertical
    }
}

fun <R, T : MathOpsTrait<R>> Rect<T>.cross(direction: FlexDirection): R {
    return if (direction.isRow()) {
        this.vertical
    } else {
        this.horizontal
    }
}