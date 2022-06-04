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

public class WrapReverseColumnTest {
  @Test
  public fun wrap_reverse_column(): Unit {
    val node1 = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	size = io.github.orioncraftmc.sprawlkt.geometry.Size(
              width = StretchDimension.Points(30.0f),
              height = StretchDimension.Points(31.0f),
           ),
        ),
            listOf(),
        )
    val node2 = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	size = io.github.orioncraftmc.sprawlkt.geometry.Size(
              width = StretchDimension.Points(30.0f),
              height = StretchDimension.Points(32.0f),
           ),
        ),
            listOf(),
        )
    val node3 = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	size = io.github.orioncraftmc.sprawlkt.geometry.Size(
              width = StretchDimension.Points(30.0f),
              height = StretchDimension.Points(33.0f),
           ),
        ),
            listOf(),
        )
    val node4 = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	size = io.github.orioncraftmc.sprawlkt.geometry.Size(
              width = StretchDimension.Points(30.0f),
              height = StretchDimension.Points(34.0f),
           ),
        ),
            listOf(),
        )
    val rootNode = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	flexDirection = FlexDirection.Column,
        	flexWrap = FlexWrap.WrapReverse,
        	size = io.github.orioncraftmc.sprawlkt.geometry.Size(
              width = StretchDimension.Points(100.0f),
              height = StretchDimension.Points(100.0f),
           ),
        ),
            listOf(node1, node2, node3, node4),
        )
    // {"style":{"flexDirection":"column","flexWrap":"wrap-reverse","size":{"width":{"unit":"Points","value":100},"height":{"unit":"Points","value":100}}},"layout":{"width":100,"height":100,"x":0,"y":0},"children":[{"style":{"size":{"width":{"unit":"Points","value":30},"height":{"unit":"Points","value":31}}},"layout":{"width":30,"height":31,"x":70,"y":0},"children":[]},{"style":{"size":{"width":{"unit":"Points","value":30},"height":{"unit":"Points","value":32}}},"layout":{"width":30,"height":32,"x":70,"y":31},"children":[]},{"style":{"size":{"width":{"unit":"Points","value":30},"height":{"unit":"Points","value":33}}},"layout":{"width":30,"height":33,"x":70,"y":63},"children":[]},{"style":{"size":{"width":{"unit":"Points","value":30},"height":{"unit":"Points","value":34}}},"layout":{"width":30,"height":34,"x":20,"y":0},"children":[]}]}
    Sprawl.computeLayout(rootNode, Size.Companion.undefinedNumber())
    kotlin.test.assertEquals(100.0f, Sprawl.layout(rootNode).size.width)
    kotlin.test.assertEquals(100.0f, Sprawl.layout(rootNode).size.height)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(rootNode).location.x)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(rootNode).location.y)
    kotlin.test.assertEquals(30.0f, Sprawl.layout(node1).size.width)
    kotlin.test.assertEquals(31.0f, Sprawl.layout(node1).size.height)
    kotlin.test.assertEquals(70.0f, Sprawl.layout(node1).location.x)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node1).location.y)
    kotlin.test.assertEquals(30.0f, Sprawl.layout(node2).size.width)
    kotlin.test.assertEquals(32.0f, Sprawl.layout(node2).size.height)
    kotlin.test.assertEquals(70.0f, Sprawl.layout(node2).location.x)
    kotlin.test.assertEquals(31.0f, Sprawl.layout(node2).location.y)
    kotlin.test.assertEquals(30.0f, Sprawl.layout(node3).size.width)
    kotlin.test.assertEquals(33.0f, Sprawl.layout(node3).size.height)
    kotlin.test.assertEquals(70.0f, Sprawl.layout(node3).location.x)
    kotlin.test.assertEquals(63.0f, Sprawl.layout(node3).location.y)
    kotlin.test.assertEquals(30.0f, Sprawl.layout(node4).size.width)
    kotlin.test.assertEquals(34.0f, Sprawl.layout(node4).size.height)
    kotlin.test.assertEquals(20.0f, Sprawl.layout(node4).location.x)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node4).location.y)
  }
}
