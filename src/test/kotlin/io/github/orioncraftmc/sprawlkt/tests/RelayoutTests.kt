package io.github.orioncraftmc.sprawlkt.tests

import io.github.orioncraftmc.sprawlkt.geometry.Size
import io.github.orioncraftmc.sprawlkt.node.Sprawl
import io.github.orioncraftmc.sprawlkt.number.StretchNumber
import io.github.orioncraftmc.sprawlkt.style.Style
import io.github.orioncraftmc.sprawlkt.style.enums.AlignSelf
import io.github.orioncraftmc.sprawlkt.style.enums.StretchDimension
import kotlin.test.Test
import kotlin.test.assertEquals

class RelayoutTests {
    @Test
    fun relayout() {
        val node1 = Sprawl.newNode(
            Style(
                size = Size(width = StretchDimension.Points(8f), height = StretchDimension.Points(80f))
            ),
            listOf()
        )
        val node0 = Sprawl.newNode(
            Style(
                alignSelf = AlignSelf.Center,
                size = Size(width = StretchDimension.Auto, height = StretchDimension.Auto),
            ),
            listOf(node1)
        )
        val node = Sprawl.newNode(
            Style(
                size = Size(width = StretchDimension.Percent(1f), height = StretchDimension.Percent(1f)),
            ),
            listOf(node0)
        )
        Sprawl.computeLayout(
            node,
            Size(
                width = StretchNumber.from(100f),
                height = StretchNumber.from(100f),
            )
        )

        val initial = Sprawl.layout(node).location
        val initial0 = Sprawl.layout(node0).location
        val initial1 = Sprawl.layout(node1).location
        for (i in 1..10) {
            Sprawl.computeLayout(
                node,
                Size(
                    width = StretchNumber.Defined(100f),
                    height = StretchNumber.Defined(100f),
                )
            )
            assertEquals(initial, Sprawl.layout(node).location)
            assertEquals(initial0, Sprawl.layout(node0).location)
            assertEquals(initial1, Sprawl.layout(node1).location)
        }
    }

}