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

public class FlexRootIgnoredTest {
  @Test
  public fun flex_root_ignored(): Unit {
    val node1 = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	flexGrow = 1.0f,
        	flexBasis = StretchDimension.Points(200.0f),
        ),
            listOf(),
        )
    val node2 = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	size = io.github.orioncraftmc.sprawlkt.geometry.Size(
              width = StretchDimension.Auto,
              height = StretchDimension.Points(100.0f),
           ),
        ),
            listOf(),
        )
    val rootNode = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	flexDirection = FlexDirection.Column,
        	size = io.github.orioncraftmc.sprawlkt.geometry.Size(
              width = StretchDimension.Points(100.0f),
              height = StretchDimension.Auto,
           ),
        	minSize = io.github.orioncraftmc.sprawlkt.geometry.Size(
              width = StretchDimension.Auto,
              height = StretchDimension.Points(100.0f),
           ),
        	maxSize = io.github.orioncraftmc.sprawlkt.geometry.Size(
              width = StretchDimension.Auto,
              height = StretchDimension.Points(500.0f),
           ),
        ),
            listOf(node1, node2),
        )
    // {"style":{"flexDirection":"column","size":{"width":{"unit":"Points","value":100}},"minSize":{"height":{"unit":"Points","value":100}},"maxSize":{"height":{"unit":"Points","value":500}}},"layout":{"width":100,"height":300,"x":0,"y":0},"children":[{"style":{"flexGrow":1,"flexBasis":{"unit":"Points","value":200}},"layout":{"width":100,"height":200,"x":0,"y":0},"children":[]},{"style":{"size":{"height":{"unit":"Points","value":100}}},"layout":{"width":100,"height":100,"x":0,"y":200},"children":[]}]}
    Sprawl.computeLayout(rootNode, Size.Companion.undefinedNumber())
    kotlin.test.assertEquals(100.0f, Sprawl.layout(rootNode).size.width)
    kotlin.test.assertEquals(300.0f, Sprawl.layout(rootNode).size.height)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(rootNode).location.x)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(rootNode).location.y)
    kotlin.test.assertEquals(100.0f, Sprawl.layout(node1).size.width)
    kotlin.test.assertEquals(200.0f, Sprawl.layout(node1).size.height)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node1).location.x)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node1).location.y)
    kotlin.test.assertEquals(100.0f, Sprawl.layout(node2).size.width)
    kotlin.test.assertEquals(100.0f, Sprawl.layout(node2).size.height)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node2).location.x)
    kotlin.test.assertEquals(200.0f, Sprawl.layout(node2).location.y)
  }
}
