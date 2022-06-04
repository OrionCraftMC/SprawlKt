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

public class RoundingFlexBasisFlexShrinkRowTest {
  @Test
  public fun rounding_flex_basis_flex_shrink_row(): Unit {
    val node1 = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	flexBasis = StretchDimension.Points(100.0f),
        ),
            listOf(),
        )
    val node2 = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	flexBasis = StretchDimension.Points(25.0f),
        ),
            listOf(),
        )
    val node3 = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	flexBasis = StretchDimension.Points(25.0f),
        ),
            listOf(),
        )
    val rootNode = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	size = io.github.orioncraftmc.sprawlkt.geometry.Size(
              width = StretchDimension.Points(101.0f),
              height = StretchDimension.Points(100.0f),
           ),
        ),
            listOf(node1, node2, node3),
        )
    // {"style":{"flexDirection":"row","size":{"width":{"unit":"Points","value":101},"height":{"unit":"Points","value":100}}},"layout":{"width":101,"height":100,"x":0,"y":0},"children":[{"style":{"flexShrink":1,"flexBasis":{"unit":"Points","value":100}},"layout":{"width":67,"height":100,"x":0,"y":0},"children":[]},{"style":{"flexBasis":{"unit":"Points","value":25}},"layout":{"width":17,"height":100,"x":67,"y":0},"children":[]},{"style":{"flexBasis":{"unit":"Points","value":25}},"layout":{"width":17,"height":100,"x":84,"y":0},"children":[]}]}
    Sprawl.computeLayout(rootNode, Size.Companion.undefinedNumber())
    kotlin.test.assertEquals(101.0f, Sprawl.layout(rootNode).size.width)
    kotlin.test.assertEquals(100.0f, Sprawl.layout(rootNode).size.height)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(rootNode).location.x)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(rootNode).location.y)
    kotlin.test.assertEquals(67.0f, Sprawl.layout(node1).size.width)
    kotlin.test.assertEquals(100.0f, Sprawl.layout(node1).size.height)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node1).location.x)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node1).location.y)
    kotlin.test.assertEquals(17.0f, Sprawl.layout(node2).size.width)
    kotlin.test.assertEquals(100.0f, Sprawl.layout(node2).size.height)
    kotlin.test.assertEquals(67.0f, Sprawl.layout(node2).location.x)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node2).location.y)
    kotlin.test.assertEquals(17.0f, Sprawl.layout(node3).size.width)
    kotlin.test.assertEquals(100.0f, Sprawl.layout(node3).size.height)
    kotlin.test.assertEquals(84.0f, Sprawl.layout(node3).location.x)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node3).location.y)
  }
}
