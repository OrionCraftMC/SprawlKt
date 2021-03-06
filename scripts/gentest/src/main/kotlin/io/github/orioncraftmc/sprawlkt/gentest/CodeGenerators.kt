package io.github.orioncraftmc.sprawlkt.gentest

import io.github.orioncraftmc.sprawlkt.geometry.Rect
import io.github.orioncraftmc.sprawlkt.geometry.Size
import io.github.orioncraftmc.sprawlkt.number.StretchNumber
import io.github.orioncraftmc.sprawlkt.style.Style
import io.github.orioncraftmc.sprawlkt.style.enums.Display
import io.github.orioncraftmc.sprawlkt.style.enums.StretchDimension
import kotlin.reflect.full.memberProperties


internal fun Style.toCode(): String {
    val props = listOf(
        Style::display,
        Style::positionType,
        Style::direction,
        Style::flexDirection,
        Style::flexWrap,
        Style::overflow,
        Style::alignItems,
        Style::class.memberProperties.find { it.name == "alignSelf" }!!,
        Style::alignContent,
        Style::justifyContent,
        Style::position,
        Style::margin,
        Style::padding,
        Style::border,
        Style::flexGrow,
        Style::flexShrink,
        Style::flexBasis,
        Style::size,
        Style::minSize,
        Style::maxSize,
        Style::aspectRatio
    )

    val defaultStyle = Style()

    val lines = props.filter { prop -> prop.get(this) != prop.get(defaultStyle) }
        .map {
            val value = it.get(this@toCode)
            buildString {
                append("\t")
                append(it.name)
                append(" = ")
                when (value) {
                    is Float -> {
                        append(value.toCode())
                    }

                    is StretchDimension -> {
                        append(value.toCode())
                    }

                    is Size<*> -> {
                        append((value as Size<StretchDimension>).toCode())
                    }

                    is Rect<*> -> {
                        append((value as Rect<StretchDimension>).toCode())
                    }

                    is StretchNumber -> {
                        append(value.toCode())
                    }

                    is Enum<*> -> {
                        append(value.toCode())
                    }

                    else -> {
                        append("TODO()")
                    }
                }
                append(",")
            }
        }

    return "${Style::class.java.toCode()}\n(\n${lines.joinToString("\n")}\n)"
}

internal fun StretchNumber.toCode(): String {
    return "${StretchNumber::class.java.toCode()}.from(${this.asFloat().toCode()})"
}

internal fun Float.toCode(): String {
    return when (this) {
        Float.POSITIVE_INFINITY -> "Float.POSITIVE_INFINITY"
        Float.NEGATIVE_INFINITY -> "Float.NEGATIVE_INFINITY"
        else -> if (this.isNaN()) "Float.NaN" else "${this}f"
    }
}

internal fun Size<StretchDimension>.toCode() = """
|${Size::class.java.toCode()}(
|      width = ${width.toCode()},
|      height = ${height.toCode()},
|   )
""".trimMargin()

internal fun StretchDimension.toCode(): String {
    return when (this) {
        is StretchDimension.Auto -> "${StretchDimension::class.java.toCode()}.Auto"
        is StretchDimension.Undefined -> "${StretchDimension::class.java.toCode()}.Undefined"
        is StretchDimension.Percent -> "${StretchDimension::class.java.toCode()}.Percent(${percent.toCode()})"
        is StretchDimension.Points -> "${StretchDimension::class.java.toCode()}.Points(${points.toCode()})"
    }
}

internal fun Rect<StretchDimension>.toCode() = """
    |${Rect::class.java.toCode()}(
    |        top = ${top.toCode()},
    |        bottom = ${bottom.toCode()},
    |        start = ${start.toCode()},
    |        end = ${end.toCode()},
    |    )
""".trimMargin()

internal fun Enum<*>.toCode(): String {
    return javaClass.toCode() + ".${this.name}"

}

internal fun Class<*>.toCode() = if (canonicalName.startsWith(Display::class.java.packageName)) {
    simpleName
} else {
    canonicalName
}