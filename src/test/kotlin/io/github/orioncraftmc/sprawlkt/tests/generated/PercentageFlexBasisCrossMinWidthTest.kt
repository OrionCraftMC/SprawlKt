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

public class PercentageFlexBasisCrossMinWidthTest {
  @Test
  public fun percentage_flex_basis_cross_min_width(): Unit {
    val node1 = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	flexGrow = 1.0f,
        	flexBasis = StretchDimension.Percent(0.1f),
        	minSize = io.github.orioncraftmc.sprawlkt.geometry.Size(
              width = StretchDimension.Percent(0.6f),
              height = StretchDimension.Auto,
           ),
        ),
            listOf(),
        )
    val node2 = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	flexGrow = 4.0f,
        	flexBasis = StretchDimension.Percent(0.15f),
        	minSize = io.github.orioncraftmc.sprawlkt.geometry.Size(
              width = StretchDimension.Percent(0.2f),
              height = StretchDimension.Auto,
           ),
        ),
            listOf(),
        )
    val rootNode = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	flexDirection = FlexDirection.Column,
        	size = io.github.orioncraftmc.sprawlkt.geometry.Size(
              width = StretchDimension.Points(200.0f),
              height = StretchDimension.Points(400.0f),
           ),
        ),
            listOf(node1, node2),
        )
    // {"style":{"flexDirection":"column","size":{"width":{"unit":"Points","value":200},"height":{"unit":"Points","value":400}}},"layout":{"width":200,"height":400,"x":0,"y":0},"children":[{"style":{"flexGrow":1,"flexBasis":{"unit":"Percent","value":0.1},"minSize":{"width":{"unit":"Percent","value":0.6}}},"layout":{"width":200,"height":100,"x":0,"y":0},"children":[]},{"style":{"flexGrow":4,"flexBasis":{"unit":"Percent","value":0.15},"minSize":{"width":{"unit":"Percent","value":0.2}}},"layout":{"width":200,"height":300,"x":0,"y":100},"children":[]}]}
    Sprawl.computeLayout(rootNode, Size.Companion.undefinedNumber())
    kotlin.test.assertEquals(200.0f, Sprawl.layout(rootNode).size.width)
    kotlin.test.assertEquals(400.0f, Sprawl.layout(rootNode).size.height)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(rootNode).location.x)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(rootNode).location.y)
    kotlin.test.assertEquals(200.0f, Sprawl.layout(node1).size.width)
    kotlin.test.assertEquals(100.0f, Sprawl.layout(node1).size.height)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node1).location.x)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node1).location.y)
    kotlin.test.assertEquals(200.0f, Sprawl.layout(node2).size.width)
    kotlin.test.assertEquals(300.0f, Sprawl.layout(node2).size.height)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node2).location.x)
    kotlin.test.assertEquals(100.0f, Sprawl.layout(node2).location.y)
  }
}
