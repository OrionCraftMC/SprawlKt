package io.github.orioncraftmc.stretchkt.geometry

import io.github.orioncraftmc.stretchkt.number.StretchNumber
import io.github.orioncraftmc.stretchkt.style.enums.StretchDimension

fun Size<StretchDimension>.resolve(parent: Size<StretchNumber>): Size<StretchNumber> {
    return Size(
        width = this.width.resolve(parent.width),
        height = this.height.resolve(parent.height)
    )
}

@JvmName("resolveSizeFloat")
fun Size<StretchDimension>.resolve(parent: Size<Float>): Size<StretchNumber> {
    return Size(
        width = this.width.resolve(StretchNumber.from(parent.width)),
        height = this.height.resolve(StretchNumber.from(parent.height))
    )
}

internal fun Size<Float>.toStretchNumberSize() = map { StretchNumber.from(it) }