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

public class RoundingTotalFractialTest {
  @Test
  public fun rounding_total_fractial(): Unit {
    val node1 = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	flexGrow = 0.7f,
        	flexBasis = StretchDimension.Points(50.3f),
        	size = io.github.orioncraftmc.sprawlkt.geometry.Size(
              width = StretchDimension.Auto,
              height = StretchDimension.Points(20.3f),
           ),
        ),
            listOf(),
        )
    val node2 = newNode(
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
    val node3 = newNode(
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
            listOf(node1, node2, node3),
        )
    // {"style":{"flexDirection":"column","size":{"width":{"unit":"Points","value":87.4},"height":{"unit":"Points","value":113.4}}},"layout":{"width":87,"height":113,"x":0,"y":0},"children":[{"style":{"flexGrow":0.7,"flexBasis":{"unit":"Points","value":50.3},"size":{"height":{"unit":"Points","value":20.3}}},"layout":{"width":87,"height":59,"x":0,"y":0},"children":[]},{"style":{"flexGrow":1.6,"size":{"height":{"unit":"Points","value":10}}},"layout":{"width":87,"height":30,"x":0,"y":59},"children":[]},{"style":{"flexGrow":1.1,"size":{"height":{"unit":"Points","value":10.7}}},"layout":{"width":87,"height":24,"x":0,"y":89},"children":[]}]}
    Sprawl.computeLayout(rootNode, Size.Companion.undefinedNumber())
    kotlin.test.assertEquals(87.0f, Sprawl.layout(rootNode).size.width)
    kotlin.test.assertEquals(113.0f, Sprawl.layout(rootNode).size.height)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(rootNode).location.x)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(rootNode).location.y)
    kotlin.test.assertEquals(87.0f, Sprawl.layout(node1).size.width)
    kotlin.test.assertEquals(59.0f, Sprawl.layout(node1).size.height)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node1).location.x)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node1).location.y)
    kotlin.test.assertEquals(87.0f, Sprawl.layout(node2).size.width)
    kotlin.test.assertEquals(30.0f, Sprawl.layout(node2).size.height)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node2).location.x)
    kotlin.test.assertEquals(59.0f, Sprawl.layout(node2).location.y)
    kotlin.test.assertEquals(87.0f, Sprawl.layout(node3).size.width)
    kotlin.test.assertEquals(24.0f, Sprawl.layout(node3).size.height)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node3).location.x)
    kotlin.test.assertEquals(89.0f, Sprawl.layout(node3).location.y)
  }
}
