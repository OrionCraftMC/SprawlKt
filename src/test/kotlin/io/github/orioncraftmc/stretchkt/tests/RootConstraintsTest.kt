package io.github.orioncraftmc.stretchkt.tests

import io.github.orioncraftmc.stretchkt.geometry.Size
import io.github.orioncraftmc.stretchkt.node.Stretch
import io.github.orioncraftmc.stretchkt.number.StretchNumber
import io.github.orioncraftmc.stretchkt.style.Style
import io.github.orioncraftmc.stretchkt.style.enums.StretchDimension
import kotlin.test.Test
import kotlin.test.assertEquals

class RootConstraintsTest {

    @Test
    fun rootWithPercentageSize() {
        val node = Stretch
            .newNode(
                Style (
                    size = Size(
                        width = StretchDimension.Percent(1.0f),
                        height = StretchDimension.Percent(1.0f),
                    ),
                ),
            )


        Stretch
            .computeLayout(
                node,
                Size(width = StretchNumber.from(100.0f), height = StretchNumber.from(200.0f)),
            )

        val layout = Stretch.layout(node)

        assertEquals(100.0f, layout.size.width)
        assertEquals(200.0f, layout.size.height)
    }

    @Test
    fun rootWithNoSize() {
        val node = Stretch.newNode()

        Stretch
            .computeLayout(
                node,
                Size(width = StretchNumber.from(100.0f), height = StretchNumber.from(100.0f)),
            )

        val layout = Stretch.layout(node)

        assertEquals(0.0f, layout.size.width)
        assertEquals(0.0f, layout.size.height)
    }

    @Test
    fun rootWithLargerSize() {
        val node = Stretch
            .newNode(
                Style(
                    size = Size(
                        width = StretchDimension.Points(200.0f),
                        height = StretchDimension.Points(200.0f),
                    ),
                ),
            )


        Stretch
            .computeLayout(
                node,
                Size(width = StretchNumber.from(100.0f), height = StretchNumber.from(100.0f)),
            )

        val layout = Stretch.layout(node)

        assertEquals(200.0f, layout.size.width)
        assertEquals(200.0f, layout.size.height)
    }

}