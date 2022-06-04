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

public class WrapNodesWithContentSizingOverflowingMarginTest {
  @Test
  public fun wrap_nodes_with_content_sizing_overflowing_margin(): Unit {
    val node1 = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	size = io.github.orioncraftmc.sprawlkt.geometry.Size(
              width = StretchDimension.Points(40.0f),
              height = StretchDimension.Points(40.0f),
           ),
        ),
            listOf(),
        )
    val node2 = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	flexDirection = FlexDirection.Column,
        ),
            listOf(node1),
        )
    val node3 = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	size = io.github.orioncraftmc.sprawlkt.geometry.Size(
              width = StretchDimension.Points(40.0f),
              height = StretchDimension.Points(40.0f),
           ),
        ),
            listOf(),
        )
    val node4 = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	flexDirection = FlexDirection.Column,
        	margin = io.github.orioncraftmc.sprawlkt.geometry.Rect(
                top = StretchDimension.Undefined,
                bottom = StretchDimension.Undefined,
                start = StretchDimension.Undefined,
                end = StretchDimension.Points(10.0f),
            ),
        ),
            listOf(node3),
        )
    val node5 = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	flexWrap = FlexWrap.Wrap,
        	size = io.github.orioncraftmc.sprawlkt.geometry.Size(
              width = StretchDimension.Points(85.0f),
              height = StretchDimension.Auto,
           ),
        ),
            listOf(node2, node4),
        )
    val rootNode = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	flexDirection = FlexDirection.Column,
        	size = io.github.orioncraftmc.sprawlkt.geometry.Size(
              width = StretchDimension.Points(500.0f),
              height = StretchDimension.Points(500.0f),
           ),
        ),
            listOf(node5),
        )
    // {"style":{"flexDirection":"column","size":{"width":{"unit":"Points","value":500},"height":{"unit":"Points","value":500}}},"layout":{"width":500,"height":500,"x":0,"y":0},"children":[{"style":{"flexDirection":"row","flexWrap":"wrap","size":{"width":{"unit":"Points","value":85}}},"layout":{"width":85,"height":80,"x":0,"y":0},"children":[{"style":{"flexDirection":"column"},"layout":{"width":40,"height":40,"x":0,"y":0},"children":[{"style":{"size":{"width":{"unit":"Points","value":40},"height":{"unit":"Points","value":40}}},"layout":{"width":40,"height":40,"x":0,"y":0},"children":[]}]},{"style":{"flexDirection":"column","margin":{"end":{"unit":"Points","value":10}}},"layout":{"width":40,"height":40,"x":0,"y":40},"children":[{"style":{"size":{"width":{"unit":"Points","value":40},"height":{"unit":"Points","value":40}}},"layout":{"width":40,"height":40,"x":0,"y":0},"children":[]}]}]}]}
    Sprawl.computeLayout(rootNode, Size.Companion.undefinedNumber())
    kotlin.test.assertEquals(500.0f, Sprawl.layout(rootNode).size.width)
    kotlin.test.assertEquals(500.0f, Sprawl.layout(rootNode).size.height)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(rootNode).location.x)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(rootNode).location.y)
    kotlin.test.assertEquals(85.0f, Sprawl.layout(node5).size.width)
    kotlin.test.assertEquals(80.0f, Sprawl.layout(node5).size.height)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node5).location.x)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node5).location.y)
    kotlin.test.assertEquals(40.0f, Sprawl.layout(node2).size.width)
    kotlin.test.assertEquals(40.0f, Sprawl.layout(node2).size.height)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node2).location.x)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node2).location.y)
    kotlin.test.assertEquals(40.0f, Sprawl.layout(node1).size.width)
    kotlin.test.assertEquals(40.0f, Sprawl.layout(node1).size.height)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node1).location.x)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node1).location.y)
    kotlin.test.assertEquals(40.0f, Sprawl.layout(node4).size.width)
    kotlin.test.assertEquals(40.0f, Sprawl.layout(node4).size.height)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node4).location.x)
    kotlin.test.assertEquals(40.0f, Sprawl.layout(node4).location.y)
    kotlin.test.assertEquals(40.0f, Sprawl.layout(node3).size.width)
    kotlin.test.assertEquals(40.0f, Sprawl.layout(node3).size.height)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node3).location.x)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node3).location.y)
  }
}
