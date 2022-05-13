package io.github.orioncraftmc.stretchkt.tests

import io.github.orioncraftmc.stretchkt.geometry.Point
import io.github.orioncraftmc.stretchkt.geometry.Size
import io.github.orioncraftmc.stretchkt.node.Stretch
import io.github.orioncraftmc.stretchkt.style.Style
import io.github.orioncraftmc.stretchkt.style.enums.AlignSelf
import kotlin.test.Test
import kotlin.test.assertEquals

class SimpleChildTest {

    @Test
    fun simpleChild() {
        val node0_0 = Stretch.newNode(
            Style(
                alignSelf = AlignSelf.Center,
                size = Size.ofPoints(10f, 10f)
            )
        )

        val node0 = Stretch.newNode(
            Style(
                size = Size.ofPoints(10f, 10f)
            ),
            listOf(node0_0)
        )

        val node1_0 = Stretch.newNode(
            Style(
                alignSelf = AlignSelf.Center,
                size = Size.ofPoints(10f, 10f)
            )
        )

        val node1_1 = Stretch.newNode(
            Style(
                alignSelf = AlignSelf.Center,
                size = Size.ofPoints(10f, 10f)
            )
        )

        val node1 = Stretch.newNode(
            Style(
                size = Size.undefinedDimension()
            ),
            listOf(node1_0, node1_1)
        )

        val node = Stretch.newNode(
            Style(
                size = Size.ofPercent(100f, 100f)
            ),
            listOf(node0, node1)
        )

        Stretch.computeLayout(node, Size.of(100f, 100f))

        assertEquals(Point(0f, 0f), Stretch.layout(node).location)
        assertEquals(Point(0f, 0f), Stretch.layout(node0).location)
        assertEquals(Point(10f, 0f), Stretch.layout(node1).location)
        assertEquals(Point(0f, 0f), Stretch.layout(node0_0).location)
        // Layout is relative so node1_0 location starts at (0,0) and is not ofset by it's parent location
        assertEquals(Point(0f, 0f), Stretch.layout(node1_0).location)
        assertEquals(Point(10f, 0f), Stretch.layout(node1_1).location)
    }

}