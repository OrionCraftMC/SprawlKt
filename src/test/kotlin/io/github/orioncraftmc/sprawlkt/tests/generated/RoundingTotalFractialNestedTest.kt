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

public class RoundingTotalFractialNestedTest {
  @Test
  public fun rounding_total_fractial_nested(): Unit {
    val node1 = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	position = io.github.orioncraftmc.sprawlkt.geometry.Rect(
                top = StretchDimension.Undefined,
                bottom = StretchDimension.Points(13.3f),
                start = StretchDimension.Undefined,
                end = StretchDimension.Undefined,
            ),
        	flexGrow = 1.0f,
        	flexBasis = StretchDimension.Points(0.3f),
        	size = io.github.orioncraftmc.sprawlkt.geometry.Size(
              width = StretchDimension.Auto,
              height = StretchDimension.Points(9.9f),
           ),
        ),
            listOf(),
        )
    val node2 = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	position = io.github.orioncraftmc.sprawlkt.geometry.Rect(
                top = StretchDimension.Points(13.3f),
                bottom = StretchDimension.Undefined,
                start = StretchDimension.Undefined,
                end = StretchDimension.Undefined,
            ),
        	flexGrow = 4.0f,
        	flexBasis = StretchDimension.Points(0.3f),
        	size = io.github.orioncraftmc.sprawlkt.geometry.Size(
              width = StretchDimension.Auto,
              height = StretchDimension.Points(1.1f),
           ),
        ),
            listOf(),
        )
    val node3 = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	flexDirection = FlexDirection.Column,
        	flexGrow = 0.7f,
        	flexBasis = StretchDimension.Points(50.3f),
        	size = io.github.orioncraftmc.sprawlkt.geometry.Size(
              width = StretchDimension.Auto,
              height = StretchDimension.Points(20.3f),
           ),
        ),
            listOf(node1, node2),
        )
    val node4 = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	flexGrow = 1.6f,
        	size = io.github.orioncraftmc.sprawlkt.geometry.Size(
              width = StretchDimension.Auto,
              height = StretchDimension.Points(10.0f),
           ),
        ),
            listOf(),
        )
    val node5 = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	flexGrow = 1.1f,
        	size = io.github.orioncraftmc.sprawlkt.geometry.Size(
              width = StretchDimension.Auto,
              height = StretchDimension.Points(10.7f),
           ),
        ),
            listOf(),
        )
    val rootNode = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	flexDirection = FlexDirection.Column,
        	size = io.github.orioncraftmc.sprawlkt.geometry.Size(
              width = StretchDimension.Points(87.4f),
              height = StretchDimension.Points(113.4f),
           ),
        ),
            listOf(node3, node4, node5),
        )
    // {"style":{"flexDirection":"column","size":{"width":{"unit":"Points","value":87.4},"height":{"unit":"Points","value":113.4}}},"layout":{"width":87,"height":113,"x":0,"y":0},"children":[{"style":{"flexDirection":"column","flexGrow":0.7,"flexBasis":{"unit":"Points","value":50.3},"size":{"height":{"unit":"Points","value":20.3}}},"layout":{"width":87,"height":59,"x":0,"y":0},"children":[{"style":{"flexGrow":1,"flexBasis":{"unit":"Points","value":0.3},"size":{"height":{"unit":"Points","value":9.9}},"position":{"bottom":{"unit":"Points","value":13.3}}},"layout":{"width":87,"height":12,"x":0,"y":-13},"children":[]},{"style":{"flexGrow":4,"flexBasis":{"unit":"Points","value":0.3},"size":{"height":{"unit":"Points","value":1.1}},"position":{"top":{"unit":"Points","value":13.3}}},"layout":{"width":87,"height":47,"x":0,"y":25},"children":[]}]},{"style":{"flexGrow":1.6,"size":{"height":{"unit":"Points","value":10}}},"layout":{"width":87,"height":30,"x":0,"y":59},"children":[]},{"style":{"flexGrow":1.1,"size":{"height":{"unit":"Points","value":10.7}}},"layout":{"width":87,"height":24,"x":0,"y":89},"children":[]}]}
    Sprawl.computeLayout(rootNode, Size.Companion.undefinedNumber())
    kotlin.test.assertEquals(87.0f, Sprawl.layout(rootNode).size.width)
    kotlin.test.assertEquals(113.0f, Sprawl.layout(rootNode).size.height)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(rootNode).location.x)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(rootNode).location.y)
    kotlin.test.assertEquals(87.0f, Sprawl.layout(node3).size.width)
    kotlin.test.assertEquals(59.0f, Sprawl.layout(node3).size.height)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node3).location.x)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node3).location.y)
    kotlin.test.assertEquals(87.0f, Sprawl.layout(node1).size.width)
    kotlin.test.assertEquals(12.0f, Sprawl.layout(node1).size.height)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node1).location.x)
    kotlin.test.assertEquals(-13.0f, Sprawl.layout(node1).location.y)
    kotlin.test.assertEquals(87.0f, Sprawl.layout(node2).size.width)
    kotlin.test.assertEquals(47.0f, Sprawl.layout(node2).size.height)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node2).location.x)
    kotlin.test.assertEquals(25.0f, Sprawl.layout(node2).location.y)
    kotlin.test.assertEquals(87.0f, Sprawl.layout(node4).size.width)
    kotlin.test.assertEquals(30.0f, Sprawl.layout(node4).size.height)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node4).location.x)
    kotlin.test.assertEquals(59.0f, Sprawl.layout(node4).location.y)
    kotlin.test.assertEquals(87.0f, Sprawl.layout(node5).size.width)
    kotlin.test.assertEquals(24.0f, Sprawl.layout(node5).size.height)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node5).location.x)
    kotlin.test.assertEquals(89.0f, Sprawl.layout(node5).location.y)
  }
}
