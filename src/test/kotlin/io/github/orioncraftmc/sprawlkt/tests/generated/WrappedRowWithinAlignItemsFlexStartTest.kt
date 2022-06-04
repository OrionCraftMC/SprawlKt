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

public class WrappedRowWithinAlignItemsFlexStartTest {
  @Test
  public fun wrapped_row_within_align_items_flex_start(): Unit {
    val node1 = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	size = io.github.orioncraftmc.sprawlkt.geometry.Size(
              width = StretchDimension.Points(150.0f),
              height = StretchDimension.Points(80.0f),
           ),
        ),
            listOf(),
        )
    val node2 = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	size = io.github.orioncraftmc.sprawlkt.geometry.Size(
              width = StretchDimension.Points(80.0f),
              height = StretchDimension.Points(80.0f),
           ),
        ),
            listOf(),
        )
    val node3 = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	flexWrap = FlexWrap.Wrap,
        ),
            listOf(node1, node2),
        )
    val rootNode = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	flexDirection = FlexDirection.Column,
        	alignItems = AlignItems.FlexStart,
        	size = io.github.orioncraftmc.sprawlkt.geometry.Size(
              width = StretchDimension.Points(200.0f),
              height = StretchDimension.Points(200.0f),
           ),
        ),
            listOf(node3),
        )
    // {"style":{"flexDirection":"column","alignItems":"flex-start","size":{"width":{"unit":"Points","value":200},"height":{"unit":"Points","value":200}}},"layout":{"width":200,"height":200,"x":0,"y":0},"children":[{"style":{"flexDirection":"row","flexWrap":"wrap"},"layout":{"width":200,"height":160,"x":0,"y":0},"children":[{"style":{"size":{"width":{"unit":"Points","value":150},"height":{"unit":"Points","value":80}}},"layout":{"width":150,"height":80,"x":0,"y":0},"children":[]},{"style":{"size":{"width":{"unit":"Points","value":80},"height":{"unit":"Points","value":80}}},"layout":{"width":80,"height":80,"x":0,"y":80},"children":[]}]}]}
    Sprawl.computeLayout(rootNode, Size.Companion.undefinedNumber())
    kotlin.test.assertEquals(200.0f, Sprawl.layout(rootNode).size.width)
    kotlin.test.assertEquals(200.0f, Sprawl.layout(rootNode).size.height)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(rootNode).location.x)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(rootNode).location.y)
    kotlin.test.assertEquals(200.0f, Sprawl.layout(node3).size.width)
    kotlin.test.assertEquals(160.0f, Sprawl.layout(node3).size.height)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node3).location.x)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node3).location.y)
    kotlin.test.assertEquals(150.0f, Sprawl.layout(node1).size.width)
    kotlin.test.assertEquals(80.0f, Sprawl.layout(node1).size.height)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node1).location.x)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node1).location.y)
    kotlin.test.assertEquals(80.0f, Sprawl.layout(node2).size.width)
    kotlin.test.assertEquals(80.0f, Sprawl.layout(node2).size.height)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node2).location.x)
    kotlin.test.assertEquals(80.0f, Sprawl.layout(node2).location.y)
  }
}
