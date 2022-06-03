package io.github.orioncraftmc.sprawlkt.tests

import io.github.orioncraftmc.sprawlkt.geometry.Point
import io.github.orioncraftmc.sprawlkt.geometry.Size
import io.github.orioncraftmc.sprawlkt.node.Sprawl
import io.github.orioncraftmc.sprawlkt.style.Style
import io.github.orioncraftmc.sprawlkt.style.enums.AlignSelf
import kotlin.test.Test
import kotlin.test.assertEquals

class SimpleChildTest {

    @Test
    fun simpleChild() {
        val node0_0 = Sprawl.newNode(
            Style(
                alignSelf = AlignSelf.Center,
                size = Size.ofPoints(10f, 10f)
            )
        )

        val node0 = Sprawl.newNode(
            Style(
                size = Size.ofPoints(10f, 10f)
            ),
            listOf(node0_0)
        )

        val node1_0 = Sprawl.newNode(
            Style(
                alignSelf = AlignSelf.Center,
                size = Size.ofPoints(10f, 10f)
            )
        )

        val node1_1 = Sprawl.newNode(
            Style(
                alignSelf = AlignSelf.Center,
                size = Size.ofPoints(10f, 10f)
            )
        )

        val node1 = Sprawl.newNode(
            Style(
                size = Size.undefinedDimension()
            ),
            listOf(node1_0, node1_1)
        )

        val node = Sprawl.newNode(
            Style(
                size = Size.ofPercent(100f, 100f)
            ),
            listOf(node0, node1)
        )

        Sprawl.computeLayout(node, Size.of(100f, 100f))

        assertEquals(Point(0f, 0f), Sprawl.layout(node).location)
        assertEquals(Point(0f, 0f), Sprawl.layout(node0).location)
        assertEquals(Point(10f, 0f), Sprawl.layout(node1).location)
        assertEquals(Point(0f, 0f), Sprawl.layout(node0_0).location)
        // Layout is relative so node1_0 location starts at (0,0) and is not ofset by it's parent location
        assertEquals(Point(0f, 0f), Sprawl.layout(node1_0).location)
        assertEquals(Point(10f, 0f), Sprawl.layout(node1_1).location)
    }

}