package io.github.orioncraftmc.stretchkt.tests

import io.github.orioncraftmc.stretchkt.geometry.Size
import io.github.orioncraftmc.stretchkt.node.Stretch
import io.github.orioncraftmc.stretchkt.number.StretchNumber
import io.github.orioncraftmc.stretchkt.style.Style
import io.github.orioncraftmc.stretchkt.style.enums.AlignSelf
import io.github.orioncraftmc.stretchkt.style.enums.StretchDimension
import kotlin.test.Test
import kotlin.test.assertEquals

class RelayoutTests {
    @Test
    fun relayout() {
        val node1 = Stretch.newNode(
            Style(
                size = Size(width = StretchDimension.Points(8f), height = StretchDimension.Points(80f))
            ),
            listOf()
        )
        val node0 = Stretch.newNode(
            Style(
                alignSelf = AlignSelf.Center,
                size = Size(width = StretchDimension.Auto, height = StretchDimension.Auto),
            ),
            listOf(node1)
        )
        val node = Stretch.newNode(
            Style(
                size = Size(width = StretchDimension.Percent(1f), height = StretchDimension.Percent(1f)),
            ),
            listOf(node0)
        )
        Stretch.computeLayout(
            node,
            Size(
                width = StretchNumber.from(100f),
                height = StretchNumber.from(100f),
            )
        )

        val initial = Stretch.layout(node).location
        val initial0 = Stretch.layout(node0).location
        val initial1 = Stretch.layout(node1).location
        for (i in 1..10) {
            Stretch.computeLayout(
                node,
                Size(
                    width = StretchNumber.Defined(100f),
                    height = StretchNumber.Defined(100f),
                )
            )
            assertEquals(initial, Stretch.layout(node).location)
            assertEquals(initial0, Stretch.layout(node0).location)
            assertEquals(initial1, Stretch.layout(node1).location)
        }
    }

}