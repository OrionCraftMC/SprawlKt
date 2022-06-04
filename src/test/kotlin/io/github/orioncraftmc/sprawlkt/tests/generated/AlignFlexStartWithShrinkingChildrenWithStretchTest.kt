package io.github.orioncraftmc.sprawlkt.tests.generated

import io.github.orioncraftmc.sprawlkt.geometry.Size
import io.github.orioncraftmc.sprawlkt.geometry.Size.Companion.undefinedNumber
import io.github.orioncraftmc.sprawlkt.node.Sprawl
import io.github.orioncraftmc.sprawlkt.node.Sprawl.computeLayout
import io.github.orioncraftmc.sprawlkt.node.Sprawl.layout
import io.github.orioncraftmc.sprawlkt.node.Sprawl.newNode
import io.github.orioncraftmc.sprawlkt.style.enums.*
import kotlin.Unit
import kotlin.test.Test

public class AlignFlexStartWithShrinkingChildrenWithStretchTest {
  @Test
  public fun align_flex_start_with_shrinking_children_with_stretch(): Unit {
    val node1 = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	flexGrow = 1.0f,
        ),
            listOf(),
        )
    val node2 = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	flexGrow = 1.0f,
        ),
            listOf(node1),
        )
    val node3 = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	alignItems = AlignItems.FlexStart,
        ),
            listOf(node2),
        )
    val rootNode = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	size = io.github.orioncraftmc.sprawlkt.geometry.Size(
              width = StretchDimension.Points(500.0f),
              height = StretchDimension.Points(500.0f),
           ),
        ),
            listOf(node3),
        )
    // {"style":{"size":{"width":{"unit":"Points","value":500},"height":{"unit":"Points","value":500}}},"layout":{"width":500,"height":500,"x":0,"y":0},"children":[{"style":{"alignItems":"flex-start"},"layout":{"width":0,"height":500,"x":0,"y":0},"children":[{"style":{"alignItems":"stretch","flexGrow":1,"flexShrink":1},"layout":{"width":0,"height":0,"x":0,"y":0},"children":[{"style":{"flexGrow":1,"flexShrink":1},"layout":{"width":0,"height":0,"x":0,"y":0},"children":[]}]}]}]}
    Sprawl.computeLayout(rootNode, Size.Companion.undefinedNumber())
    kotlin.test.assertEquals(500.0f, Sprawl.layout(rootNode).size.width)
    kotlin.test.assertEquals(500.0f, Sprawl.layout(rootNode).size.height)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(rootNode).location.x)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(rootNode).location.y)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node3).size.width)
    kotlin.test.assertEquals(500.0f, Sprawl.layout(node3).size.height)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node3).location.x)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node3).location.y)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node2).size.width)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node2).size.height)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node2).location.x)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node2).location.y)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node1).size.width)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node1).size.height)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node1).location.x)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node1).location.y)
  }
}
