package io.github.orioncraftmc.sprawlkt.tests

import io.github.orioncraftmc.sprawlkt.geometry.Size
import io.github.orioncraftmc.sprawlkt.node.Sprawl
import io.github.orioncraftmc.sprawlkt.number.StretchNumber
import io.github.orioncraftmc.sprawlkt.style.Style
import io.github.orioncraftmc.sprawlkt.style.enums.StretchDimension
import kotlin.test.Test
import kotlin.test.assertEquals

class RootConstraintsTest {

    @Test
    fun rootWithPercentageSize() {
        val node = Sprawl
            .newNode(
                Style (
                    size = Size(
                        width = StretchDimension.Percent(1.0f),
                        height = StretchDimension.Percent(1.0f),
                    ),
                ),
            )


        Sprawl
            .computeLayout(
                node,
                Size(width = StretchNumber.from(100.0f), height = StretchNumber.from(200.0f)),
            )

        val layout = Sprawl.layout(node)

        assertEquals(100.0f, layout.size.width)
        assertEquals(200.0f, layout.size.height)
    }

    @Test
    fun rootWithNoSize() {
        val node = Sprawl.newNode()

        Sprawl
            .computeLayout(
                node,
                Size(width = StretchNumber.from(100.0f), height = StretchNumber.from(100.0f)),
            )

        val layout = Sprawl.layout(node)

        assertEquals(0.0f, layout.size.width)
        assertEquals(0.0f, layout.size.height)
    }

    @Test
    fun rootWithLargerSize() {
        val node = Sprawl
            .newNode(
                Style(
                    size = Size(
                        width = StretchDimension.Points(200.0f),
                        height = StretchDimension.Points(200.0f),
                    ),
                ),
            )


        Sprawl
            .computeLayout(
                node,
                Size(width = StretchNumber.from(100.0f), height = StretchNumber.from(100.0f)),
            )

        val layout = Sprawl.layout(node)

        assertEquals(200.0f, layout.size.width)
        assertEquals(200.0f, layout.size.height)
    }

}