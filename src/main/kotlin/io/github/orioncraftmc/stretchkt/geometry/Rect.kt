package io.github.orioncraftmc.stretchkt.geometry

import io.github.orioncraftmc.stretchkt.style.enums.FlexDirection


data class Rect<T>(
    var start: T,
    var end: T,
    var top: T,
    var bottom: T
) {

    internal fun <R> map(transform: (T) -> R): Rect<R> {
        return Rect(
            start = transform(start),
            end = transform(end),
            top = transform(top),
            bottom = transform(bottom)
        )
    }

    internal fun <U, R> zipSize(size: Size<U>, transform: (T, U) -> R): Rect<R> {
        return Rect(
            start = transform(start, size.width),
            end = transform(end, size.width),
            top = transform(top, size.height),
            bottom = transform(bottom, size.height)
        )
    }

    internal fun mainStart(direction: FlexDirection): T {
        return if (direction.isRow()) {
            this.start
        } else {
            this.end
        }
    }

    internal fun mainEnd(direction: FlexDirection): T {
        return if (direction.isRow()) {
            this.end
        } else {
            this.bottom
        }
    }

    internal fun crossStart(direction: FlexDirection): T {
        return if (direction.isRow()) {
            this.top
        } else {
            this.start
        }
    }

    internal fun crossEnd(direction: FlexDirection): T {
        return if (direction.isRow()) {
            this.bottom
        } else {
            this.end
        }
    }
}