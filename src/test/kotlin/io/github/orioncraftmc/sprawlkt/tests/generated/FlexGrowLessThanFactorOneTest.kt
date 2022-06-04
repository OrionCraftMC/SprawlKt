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

public class FlexGrowLessThanFactorOneTest {
  @Test
  public fun flex_grow_less_than_factor_one(): Unit {
    val node1 = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	flexGrow = 0.2f,
        	flexShrink = 0.0f,
        	flexBasis = StretchDimension.Points(40.0f),
        ),
            listOf(),
        )
    val node2 = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	flexGrow = 0.2f,
        	flexShrink = 0.0f,
        ),
            listOf(),
        )
    val node3 = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	flexGrow = 0.4f,
        	flexShrink = 0.0f,
        ),
            listOf(),
        )
    val rootNode = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	size = io.github.orioncraftmc.sprawlkt.geometry.Size(
              width = StretchDimension.Points(500.0f),
              height = StretchDimension.Points(200.0f),
           ),
        ),
            listOf(node1, node2, node3),
        )
    // {"style":{"size":{"width":{"unit":"Points","value":500},"height":{"unit":"Points","value":200}}},"layout":{"width":500,"height":200,"x":0,"y":0},"children":[{"style":{"flexGrow":0.2,"flexShrink":0,"flexBasis":{"unit":"Points","value":40}},"layout":{"width":132,"height":200,"x":0,"y":0},"children":[]},{"style":{"flexGrow":0.2,"flexShrink":0},"layout":{"width":92,"height":200,"x":132,"y":0},"children":[]},{"style":{"flexGrow":0.4,"flexShrink":0},"layout":{"width":184,"height":200,"x":224,"y":0},"children":[]}]}
    Sprawl.computeLayout(rootNode, Size.Companion.undefinedNumber())
    kotlin.test.assertEquals(500.0f, Sprawl.layout(rootNode).size.width)
    kotlin.test.assertEquals(200.0f, Sprawl.layout(rootNode).size.height)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(rootNode).location.x)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(rootNode).location.y)
    kotlin.test.assertEquals(132.0f, Sprawl.layout(node1).size.width)
    kotlin.test.assertEquals(200.0f, Sprawl.layout(node1).size.height)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node1).location.x)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node1).location.y)
    kotlin.test.assertEquals(92.0f, Sprawl.layout(node2).size.width)
    kotlin.test.assertEquals(200.0f, Sprawl.layout(node2).size.height)
    kotlin.test.assertEquals(132.0f, Sprawl.layout(node2).location.x)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node2).location.y)
    kotlin.test.assertEquals(184.0f, Sprawl.layout(node3).size.width)
    kotlin.test.assertEquals(200.0f, Sprawl.layout(node3).size.height)
    kotlin.test.assertEquals(224.0f, Sprawl.layout(node3).location.x)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node3).location.y)
  }
}
