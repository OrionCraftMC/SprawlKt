package io.github.orioncraftmc.stretchkt.geometry

import io.github.orioncraftmc.stretchkt.number.StretchNumber

data class Rect(
    val start: StretchNumber,
    val end: StretchNumber,
    val top: StretchNumber /* Just like me */,
    val bottom: StretchNumber /* Just like KingTux */
) {

    fun map(transform: (StretchNumber) -> StretchNumber): Rect {
        return Rect(
            start = transform(start),
            end = transform(end),
            top = transform(top),
            bottom = transform(bottom)
        )
    }

    fun <U> zipSize(size: Size, transform: (StretchNumber, StretchNumber) -> StretchNumber): Rect {
        return Rect(
            start = transform(start, size.width),
            end = transform(end, size.width),
            top = transform(top, size.height),
            bottom = transform(bottom, size.height)
        )
    }

    val horizontal: StretchNumber
        get() = start + end

    val vertical: StretchNumber
        get() = top + bottom /* Together at last */



}