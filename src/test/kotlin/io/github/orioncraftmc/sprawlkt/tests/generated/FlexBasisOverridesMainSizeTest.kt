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

public class FlexBasisOverridesMainSizeTest {
  @Test
  public fun flex_basis_overrides_main_size(): Unit {
    val node1 = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	flexGrow = 1.0f,
        	flexBasis = StretchDimension.Points(50.0f),
        	size = io.github.orioncraftmc.sprawlkt.geometry.Size(
              width = StretchDimension.Points(20.0f),
              height = StretchDimension.Auto,
           ),
        ),
            listOf(),
        )
    val node2 = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	flexGrow = 1.0f,
        	size = io.github.orioncraftmc.sprawlkt.geometry.Size(
              width = StretchDimension.Points(10.0f),
              height = StretchDimension.Auto,
           ),
        ),
            listOf(),
        )
    val node3 = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	flexGrow = 1.0f,
        	size = io.github.orioncraftmc.sprawlkt.geometry.Size(
              width = StretchDimension.Points(10.0f),
              height = StretchDimension.Auto,
           ),
        ),
            listOf(),
        )
    val rootNode = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	size = io.github.orioncraftmc.sprawlkt.geometry.Size(
              width = StretchDimension.Points(100.0f),
              height = StretchDimension.Points(100.0f),
           ),
        ),
            listOf(node1, node2, node3),
        )
    // {"style":{"size":{"width":{"unit":"Points","value":100},"height":{"unit":"Points","value":100}}},"layout":{"width":100,"height":100,"x":0,"y":0},"children":[{"style":{"flexGrow":1,"flexBasis":{"unit":"Points","value":50},"size":{"width":{"unit":"Points","value":20}}},"layout":{"width":60,"height":100,"x":0,"y":0},"children":[]},{"style":{"flexGrow":1,"size":{"width":{"unit":"Points","value":10}}},"layout":{"width":20,"height":100,"x":60,"y":0},"children":[]},{"style":{"flexGrow":1,"size":{"width":{"unit":"Points","value":10}}},"layout":{"width":20,"height":100,"x":80,"y":0},"children":[]}]}
    Sprawl.computeLayout(rootNode, Size.Companion.undefinedNumber())
    kotlin.test.assertEquals(100.0f, Sprawl.layout(rootNode).size.width)
    kotlin.test.assertEquals(100.0f, Sprawl.layout(rootNode).size.height)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(rootNode).location.x)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(rootNode).location.y)
    kotlin.test.assertEquals(60.0f, Sprawl.layout(node1).size.width)
    kotlin.test.assertEquals(100.0f, Sprawl.layout(node1).size.height)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node1).location.x)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node1).location.y)
    kotlin.test.assertEquals(20.0f, Sprawl.layout(node2).size.width)
    kotlin.test.assertEquals(100.0f, Sprawl.layout(node2).size.height)
    kotlin.test.assertEquals(60.0f, Sprawl.layout(node2).location.x)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node2).location.y)
    kotlin.test.assertEquals(20.0f, Sprawl.layout(node3).size.width)
    kotlin.test.assertEquals(100.0f, Sprawl.layout(node3).size.height)
    kotlin.test.assertEquals(80.0f, Sprawl.layout(node3).location.x)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node3).location.y)
  }
}
