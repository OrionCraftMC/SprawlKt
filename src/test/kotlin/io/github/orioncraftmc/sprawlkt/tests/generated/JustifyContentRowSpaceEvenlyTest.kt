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

public class JustifyContentRowSpaceEvenlyTest {
  @Test
  public fun justify_content_row_space_evenly(): Unit {
    val node1 = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	size = io.github.orioncraftmc.sprawlkt.geometry.Size(
              width = StretchDimension.Auto,
              height = StretchDimension.Points(10.0f),
           ),
        ),
            listOf(),
        )
    val node2 = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
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
        	size = io.github.orioncraftmc.sprawlkt.geometry.Size(
              width = StretchDimension.Auto,
              height = StretchDimension.Points(10.0f),
           ),
        ),
            listOf(),
        )
    val rootNode = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	justifyContent = JustifyContent.SpaceEvenly,
        	size = io.github.orioncraftmc.sprawlkt.geometry.Size(
              width = StretchDimension.Points(100.0f),
              height = StretchDimension.Points(100.0f),
           ),
        ),
            listOf(node1, node2, node3),
        )
    // {"style":{"flexDirection":"row","justifyContent":"space-evenly","size":{"width":{"unit":"Points","value":100},"height":{"unit":"Points","value":100}}},"layout":{"width":100,"height":100,"x":0,"y":0},"children":[{"style":{"size":{"height":{"unit":"Points","value":10}}},"layout":{"width":0,"height":10,"x":25,"y":0},"children":[]},{"style":{"size":{"height":{"unit":"Points","value":10}}},"layout":{"width":0,"height":10,"x":50,"y":0},"children":[]},{"style":{"size":{"height":{"unit":"Points","value":10}}},"layout":{"width":0,"height":10,"x":75,"y":0},"children":[]}]}
    Sprawl.computeLayout(rootNode, Size.Companion.undefinedNumber())
    kotlin.test.assertEquals(100.0f, Sprawl.layout(rootNode).size.width)
    kotlin.test.assertEquals(100.0f, Sprawl.layout(rootNode).size.height)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(rootNode).location.x)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(rootNode).location.y)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node1).size.width)
    kotlin.test.assertEquals(10.0f, Sprawl.layout(node1).size.height)
    kotlin.test.assertEquals(25.0f, Sprawl.layout(node1).location.x)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node1).location.y)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node2).size.width)
    kotlin.test.assertEquals(10.0f, Sprawl.layout(node2).size.height)
    kotlin.test.assertEquals(50.0f, Sprawl.layout(node2).location.x)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node2).location.y)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node3).size.width)
    kotlin.test.assertEquals(10.0f, Sprawl.layout(node3).size.height)
    kotlin.test.assertEquals(75.0f, Sprawl.layout(node3).location.x)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node3).location.y)
  }
}
