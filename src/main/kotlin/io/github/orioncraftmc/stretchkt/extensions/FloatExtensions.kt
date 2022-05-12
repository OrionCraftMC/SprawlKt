package io.github.orioncraftmc.stretchkt.extensions

import io.github.orioncraftmc.stretchkt.number.StretchNumber

@Deprecated(message = "Usage of Float#toStretchNumber is usually not correct and will produce useless objects", level = DeprecationLevel.WARNING)
fun Float.toStretchNumber() = StretchNumber.from(this)