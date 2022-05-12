package io.github.orioncraftmc.stretchkt.extensions

import io.github.orioncraftmc.stretchkt.number.StretchNumber
import io.github.orioncraftmc.stretchkt.style.enums.StretchDimension

inline fun StretchDimension.resolve(parentDimension: Float): StretchNumber {
    return resolve(StretchNumber.from(parentDimension))
}
