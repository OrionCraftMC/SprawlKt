package io.github.orioncraftmc.sprawlkt.extensions

import io.github.orioncraftmc.sprawlkt.number.StretchNumber
import io.github.orioncraftmc.sprawlkt.style.enums.StretchDimension

inline fun StretchDimension.resolve(parentDimension: Float): StretchNumber {
    return resolve(StretchNumber.from(parentDimension))
}
