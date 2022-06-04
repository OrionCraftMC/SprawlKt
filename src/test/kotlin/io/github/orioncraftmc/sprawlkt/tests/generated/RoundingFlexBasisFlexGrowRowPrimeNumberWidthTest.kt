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

public class RoundingFlexBasisFlexGrowRowPrimeNumberWidthTest {
  @Test
  public fun rounding_flex_basis_flex_grow_row_prime_number_width(): Unit {
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
            listOf(),
        )
    val node3 = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	flexGrow = 1.0f,
        ),
            listOf(),
        )
    val node4 = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	flexGrow = 1.0f,
        ),
            listOf(),
        )
    val node5 = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	flexGrow = 1.0f,
        ),
            listOf(),
        )
    val rootNode = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	size = io.github.orioncraftmc.sprawlkt.geometry.Size(
              width = StretchDimension.Points(113.0f),
              height = StretchDimension.Points(100.0f),
           ),
        ),
            listOf(node1, node2, node3, node4, node5),
        )
    // {"style":{"flexDirection":"row","size":{"width":{"unit":"Points","value":113},"height":{"unit":"Points","value":100}}},"layout":{"width":113,"height":100,"x":0,"y":0},"children":[{"style":{"flexGrow":1},"layout":{"width":23,"height":100,"x":0,"y":0},"children":[]},{"style":{"flexGrow":1},"layout":{"width":23,"height":100,"x":23,"y":0},"children":[]},{"style":{"flexGrow":1},"layout":{"width":23,"height":100,"x":45,"y":0},"children":[]},{"style":{"flexGrow":1},"layout":{"width":23,"height":100,"x":68,"y":0},"children":[]},{"style":{"flexGrow":1},"layout":{"width":23,"height":100,"x":90,"y":0},"children":[]}]}
    Sprawl.computeLayout(rootNode, Size.Companion.undefinedNumber())
    kotlin.test.assertEquals(113.0f, Sprawl.layout(rootNode).size.width)
    kotlin.test.assertEquals(100.0f, Sprawl.layout(rootNode).size.height)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(rootNode).location.x)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(rootNode).location.y)
    kotlin.test.assertEquals(23.0f, Sprawl.layout(node1).size.width)
    kotlin.test.assertEquals(100.0f, Sprawl.layout(node1).size.height)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node1).location.x)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node1).location.y)
    kotlin.test.assertEquals(23.0f, Sprawl.layout(node2).size.width)
    kotlin.test.assertEquals(100.0f, Sprawl.layout(node2).size.height)
    kotlin.test.assertEquals(23.0f, Sprawl.layout(node2).location.x)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node2).location.y)
    kotlin.test.assertEquals(23.0f, Sprawl.layout(node3).size.width)
    kotlin.test.assertEquals(100.0f, Sprawl.layout(node3).size.height)
    kotlin.test.assertEquals(45.0f, Sprawl.layout(node3).location.x)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node3).location.y)
    kotlin.test.assertEquals(23.0f, Sprawl.layout(node4).size.width)
    kotlin.test.assertEquals(100.0f, Sprawl.layout(node4).size.height)
    kotlin.test.assertEquals(68.0f, Sprawl.layout(node4).location.x)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node4).location.y)
    kotlin.test.assertEquals(23.0f, Sprawl.layout(node5).size.width)
    kotlin.test.assertEquals(100.0f, Sprawl.layout(node5).size.height)
    kotlin.test.assertEquals(90.0f, Sprawl.layout(node5).location.x)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node5).location.y)
  }
}
