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

public class WrapReverseColumnFixedSizeTest {
  @Test
  public fun wrap_reverse_column_fixed_size(): Unit {
    val node1 = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	size = io.github.orioncraftmc.sprawlkt.geometry.Size(
              width = StretchDimension.Points(30.0f),
              height = StretchDimension.Points(10.0f),
           ),
        ),
            listOf(),
        )
    val node2 = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	size = io.github.orioncraftmc.sprawlkt.geometry.Size(
              width = StretchDimension.Points(30.0f),
              height = StretchDimension.Points(20.0f),
           ),
        ),
            listOf(),
        )
    val node3 = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	size = io.github.orioncraftmc.sprawlkt.geometry.Size(
              width = StretchDimension.Points(30.0f),
              height = StretchDimension.Points(30.0f),
           ),
        ),
            listOf(),
        )
    val node4 = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	size = io.github.orioncraftmc.sprawlkt.geometry.Size(
              width = StretchDimension.Points(30.0f),
              height = StretchDimension.Points(40.0f),
           ),
        ),
            listOf(),
        )
    val node5 = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	size = io.github.orioncraftmc.sprawlkt.geometry.Size(
              width = StretchDimension.Points(30.0f),
              height = StretchDimension.Points(50.0f),
           ),
        ),
            listOf(),
        )
    val rootNode = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	flexDirection = FlexDirection.Column,
        	flexWrap = FlexWrap.WrapReverse,
        	alignItems = AlignItems.Center,
        	size = io.github.orioncraftmc.sprawlkt.geometry.Size(
              width = StretchDimension.Points(200.0f),
              height = StretchDimension.Points(100.0f),
           ),
        ),
            listOf(node1, node2, node3, node4, node5),
        )
    // {"style":{"flexDirection":"column","flexWrap":"wrap-reverse","alignItems":"center","size":{"width":{"unit":"Points","value":200},"height":{"unit":"Points","value":100}}},"layout":{"width":200,"height":100,"x":0,"y":0},"children":[{"style":{"size":{"width":{"unit":"Points","value":30},"height":{"unit":"Points","value":10}}},"layout":{"width":30,"height":10,"x":135,"y":0},"children":[]},{"style":{"size":{"width":{"unit":"Points","value":30},"height":{"unit":"Points","value":20}}},"layout":{"width":30,"height":20,"x":135,"y":10},"children":[]},{"style":{"size":{"width":{"unit":"Points","value":30},"height":{"unit":"Points","value":30}}},"layout":{"width":30,"height":30,"x":135,"y":30},"children":[]},{"style":{"size":{"width":{"unit":"Points","value":30},"height":{"unit":"Points","value":40}}},"layout":{"width":30,"height":40,"x":135,"y":60},"children":[]},{"style":{"size":{"width":{"unit":"Points","value":30},"height":{"unit":"Points","value":50}}},"layout":{"width":30,"height":50,"x":35,"y":0},"children":[]}]}
    Sprawl.computeLayout(rootNode, Size.Companion.undefinedNumber())
    kotlin.test.assertEquals(200.0f, Sprawl.layout(rootNode).size.width)
    kotlin.test.assertEquals(100.0f, Sprawl.layout(rootNode).size.height)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(rootNode).location.x)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(rootNode).location.y)
    kotlin.test.assertEquals(30.0f, Sprawl.layout(node1).size.width)
    kotlin.test.assertEquals(10.0f, Sprawl.layout(node1).size.height)
    kotlin.test.assertEquals(135.0f, Sprawl.layout(node1).location.x)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node1).location.y)
    kotlin.test.assertEquals(30.0f, Sprawl.layout(node2).size.width)
    kotlin.test.assertEquals(20.0f, Sprawl.layout(node2).size.height)
    kotlin.test.assertEquals(135.0f, Sprawl.layout(node2).location.x)
    kotlin.test.assertEquals(10.0f, Sprawl.layout(node2).location.y)
    kotlin.test.assertEquals(30.0f, Sprawl.layout(node3).size.width)
    kotlin.test.assertEquals(30.0f, Sprawl.layout(node3).size.height)
    kotlin.test.assertEquals(135.0f, Sprawl.layout(node3).location.x)
    kotlin.test.assertEquals(30.0f, Sprawl.layout(node3).location.y)
    kotlin.test.assertEquals(30.0f, Sprawl.layout(node4).size.width)
    kotlin.test.assertEquals(40.0f, Sprawl.layout(node4).size.height)
    kotlin.test.assertEquals(135.0f, Sprawl.layout(node4).location.x)
    kotlin.test.assertEquals(60.0f, Sprawl.layout(node4).location.y)
    kotlin.test.assertEquals(30.0f, Sprawl.layout(node5).size.width)
    kotlin.test.assertEquals(50.0f, Sprawl.layout(node5).size.height)
    kotlin.test.assertEquals(35.0f, Sprawl.layout(node5).location.x)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node5).location.y)
  }
}
