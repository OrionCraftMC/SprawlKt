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

public class AlignBaselineChildMultilineTest {
  @Test
  public fun align_baseline_child_multiline(): Unit {
    val node1 = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	size = io.github.orioncraftmc.sprawlkt.geometry.Size(
              width = StretchDimension.Points(50.0f),
              height = StretchDimension.Points(60.0f),
           ),
        ),
            listOf(),
        )
    val node2 = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	size = io.github.orioncraftmc.sprawlkt.geometry.Size(
              width = StretchDimension.Points(25.0f),
              height = StretchDimension.Points(20.0f),
           ),
        ),
            listOf(),
        )
    val node3 = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	size = io.github.orioncraftmc.sprawlkt.geometry.Size(
              width = StretchDimension.Points(25.0f),
              height = StretchDimension.Points(10.0f),
           ),
        ),
            listOf(),
        )
    val node4 = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	size = io.github.orioncraftmc.sprawlkt.geometry.Size(
              width = StretchDimension.Points(25.0f),
              height = StretchDimension.Points(20.0f),
           ),
        ),
            listOf(),
        )
    val node5 = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	size = io.github.orioncraftmc.sprawlkt.geometry.Size(
              width = StretchDimension.Points(25.0f),
              height = StretchDimension.Points(10.0f),
           ),
        ),
            listOf(),
        )
    val node6 = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	flexWrap = FlexWrap.Wrap,
        	size = io.github.orioncraftmc.sprawlkt.geometry.Size(
              width = StretchDimension.Points(50.0f),
              height = StretchDimension.Auto,
           ),
        ),
            listOf(node2, node3, node4, node5),
        )
    val rootNode = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	alignItems = AlignItems.Baseline,
        	size = io.github.orioncraftmc.sprawlkt.geometry.Size(
              width = StretchDimension.Points(100.0f),
              height = StretchDimension.Auto,
           ),
        ),
            listOf(node1, node6),
        )
    // {"style":{"alignItems":"baseline","size":{"width":{"unit":"Points","value":100}}},"layout":{"width":100,"height":80,"x":0,"y":0},"children":[{"style":{"size":{"width":{"unit":"Points","value":50},"height":{"unit":"Points","value":60}}},"layout":{"width":50,"height":60,"x":0,"y":0},"children":[]},{"style":{"flexWrap":"wrap","size":{"width":{"unit":"Points","value":50}}},"layout":{"width":50,"height":40,"x":50,"y":40},"children":[{"style":{"size":{"width":{"unit":"Points","value":25},"height":{"unit":"Points","value":20}}},"layout":{"width":25,"height":20,"x":0,"y":0},"children":[]},{"style":{"size":{"width":{"unit":"Points","value":25},"height":{"unit":"Points","value":10}}},"layout":{"width":25,"height":10,"x":25,"y":0},"children":[]},{"style":{"size":{"width":{"unit":"Points","value":25},"height":{"unit":"Points","value":20}}},"layout":{"width":25,"height":20,"x":0,"y":20},"children":[]},{"style":{"size":{"width":{"unit":"Points","value":25},"height":{"unit":"Points","value":10}}},"layout":{"width":25,"height":10,"x":25,"y":20},"children":[]}]}]}
    Sprawl.computeLayout(rootNode, Size.Companion.undefinedNumber())
    kotlin.test.assertEquals(100.0f, Sprawl.layout(rootNode).size.width)
    kotlin.test.assertEquals(80.0f, Sprawl.layout(rootNode).size.height)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(rootNode).location.x)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(rootNode).location.y)
    kotlin.test.assertEquals(50.0f, Sprawl.layout(node1).size.width)
    kotlin.test.assertEquals(60.0f, Sprawl.layout(node1).size.height)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node1).location.x)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node1).location.y)
    kotlin.test.assertEquals(50.0f, Sprawl.layout(node6).size.width)
    kotlin.test.assertEquals(40.0f, Sprawl.layout(node6).size.height)
    kotlin.test.assertEquals(50.0f, Sprawl.layout(node6).location.x)
    kotlin.test.assertEquals(40.0f, Sprawl.layout(node6).location.y)
    kotlin.test.assertEquals(25.0f, Sprawl.layout(node2).size.width)
    kotlin.test.assertEquals(20.0f, Sprawl.layout(node2).size.height)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node2).location.x)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node2).location.y)
    kotlin.test.assertEquals(25.0f, Sprawl.layout(node3).size.width)
    kotlin.test.assertEquals(10.0f, Sprawl.layout(node3).size.height)
    kotlin.test.assertEquals(25.0f, Sprawl.layout(node3).location.x)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node3).location.y)
    kotlin.test.assertEquals(25.0f, Sprawl.layout(node4).size.width)
    kotlin.test.assertEquals(20.0f, Sprawl.layout(node4).size.height)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node4).location.x)
    kotlin.test.assertEquals(20.0f, Sprawl.layout(node4).location.y)
    kotlin.test.assertEquals(25.0f, Sprawl.layout(node5).size.width)
    kotlin.test.assertEquals(10.0f, Sprawl.layout(node5).size.height)
    kotlin.test.assertEquals(25.0f, Sprawl.layout(node5).location.x)
    kotlin.test.assertEquals(20.0f, Sprawl.layout(node5).location.y)
  }
}
