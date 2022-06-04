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

public class WrapReverseRowTest {
  @Test
  public fun wrap_reverse_row(): Unit {
    val node1 = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	size = io.github.orioncraftmc.sprawlkt.geometry.Size(
              width = StretchDimension.Points(31.0f),
              height = StretchDimension.Points(30.0f),
           ),
        ),
            listOf(),
        )
    val node2 = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	size = io.github.orioncraftmc.sprawlkt.geometry.Size(
              width = StretchDimension.Points(32.0f),
              height = StretchDimension.Points(30.0f),
           ),
        ),
            listOf(),
        )
    val node3 = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	size = io.github.orioncraftmc.sprawlkt.geometry.Size(
              width = StretchDimension.Points(33.0f),
              height = StretchDimension.Points(30.0f),
           ),
        ),
            listOf(),
        )
    val node4 = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	size = io.github.orioncraftmc.sprawlkt.geometry.Size(
              width = StretchDimension.Points(34.0f),
              height = StretchDimension.Points(30.0f),
           ),
        ),
            listOf(),
        )
    val rootNode = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	flexWrap = FlexWrap.WrapReverse,
        	size = io.github.orioncraftmc.sprawlkt.geometry.Size(
              width = StretchDimension.Points(100.0f),
              height = StretchDimension.Auto,
           ),
        ),
            listOf(node1, node2, node3, node4),
        )
    // {"style":{"flexDirection":"row","flexWrap":"wrap-reverse","size":{"width":{"unit":"Points","value":100}}},"layout":{"width":100,"height":60,"x":0,"y":0},"children":[{"style":{"size":{"width":{"unit":"Points","value":31},"height":{"unit":"Points","value":30}}},"layout":{"width":31,"height":30,"x":0,"y":30},"children":[]},{"style":{"size":{"width":{"unit":"Points","value":32},"height":{"unit":"Points","value":30}}},"layout":{"width":32,"height":30,"x":31,"y":30},"children":[]},{"style":{"size":{"width":{"unit":"Points","value":33},"height":{"unit":"Points","value":30}}},"layout":{"width":33,"height":30,"x":63,"y":30},"children":[]},{"style":{"size":{"width":{"unit":"Points","value":34},"height":{"unit":"Points","value":30}}},"layout":{"width":34,"height":30,"x":0,"y":0},"children":[]}]}
    Sprawl.computeLayout(rootNode, Size.Companion.undefinedNumber())
    kotlin.test.assertEquals(100.0f, Sprawl.layout(rootNode).size.width)
    kotlin.test.assertEquals(60.0f, Sprawl.layout(rootNode).size.height)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(rootNode).location.x)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(rootNode).location.y)
    kotlin.test.assertEquals(31.0f, Sprawl.layout(node1).size.width)
    kotlin.test.assertEquals(30.0f, Sprawl.layout(node1).size.height)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node1).location.x)
    kotlin.test.assertEquals(30.0f, Sprawl.layout(node1).location.y)
    kotlin.test.assertEquals(32.0f, Sprawl.layout(node2).size.width)
    kotlin.test.assertEquals(30.0f, Sprawl.layout(node2).size.height)
    kotlin.test.assertEquals(31.0f, Sprawl.layout(node2).location.x)
    kotlin.test.assertEquals(30.0f, Sprawl.layout(node2).location.y)
    kotlin.test.assertEquals(33.0f, Sprawl.layout(node3).size.width)
    kotlin.test.assertEquals(30.0f, Sprawl.layout(node3).size.height)
    kotlin.test.assertEquals(63.0f, Sprawl.layout(node3).location.x)
    kotlin.test.assertEquals(30.0f, Sprawl.layout(node3).location.y)
    kotlin.test.assertEquals(34.0f, Sprawl.layout(node4).size.width)
    kotlin.test.assertEquals(30.0f, Sprawl.layout(node4).size.height)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node4).location.x)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node4).location.y)
  }
}
