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

public class ChildMinMaxWidthFlexingTest {
  @Test
  public fun child_min_max_width_flexing(): Unit {
    val node1 = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	flexGrow = 1.0f,
        	flexShrink = 0.0f,
        	flexBasis = StretchDimension.Points(0.0f),
        	minSize = io.github.orioncraftmc.sprawlkt.geometry.Size(
              width = StretchDimension.Points(60.0f),
              height = StretchDimension.Auto,
           ),
        ),
            listOf(),
        )
    val node2 = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	flexGrow = 1.0f,
        	flexShrink = 0.0f,
        	flexBasis = StretchDimension.Percent(0.5f),
        	maxSize = io.github.orioncraftmc.sprawlkt.geometry.Size(
              width = StretchDimension.Points(20.0f),
              height = StretchDimension.Auto,
           ),
        ),
            listOf(),
        )
    val rootNode = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	size = io.github.orioncraftmc.sprawlkt.geometry.Size(
              width = StretchDimension.Points(120.0f),
              height = StretchDimension.Points(50.0f),
           ),
        ),
            listOf(node1, node2),
        )
    // {"style":{"flexDirection":"row","alignItems":"stretch","size":{"width":{"unit":"Points","value":120},"height":{"unit":"Points","value":50}}},"layout":{"width":120,"height":50,"x":0,"y":0},"children":[{"style":{"flexGrow":1,"flexShrink":0,"flexBasis":{"unit":"Points","value":0},"minSize":{"width":{"unit":"Points","value":60}}},"layout":{"width":100,"height":50,"x":0,"y":0},"children":[]},{"style":{"flexGrow":1,"flexShrink":0,"flexBasis":{"unit":"Percent","value":0.5},"maxSize":{"width":{"unit":"Points","value":20}}},"layout":{"width":20,"height":50,"x":100,"y":0},"children":[]}]}
    Sprawl.computeLayout(rootNode, Size.Companion.undefinedNumber())
    kotlin.test.assertEquals(120.0f, Sprawl.layout(rootNode).size.width)
    kotlin.test.assertEquals(50.0f, Sprawl.layout(rootNode).size.height)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(rootNode).location.x)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(rootNode).location.y)
    kotlin.test.assertEquals(100.0f, Sprawl.layout(node1).size.width)
    kotlin.test.assertEquals(50.0f, Sprawl.layout(node1).size.height)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node1).location.x)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node1).location.y)
    kotlin.test.assertEquals(20.0f, Sprawl.layout(node2).size.width)
    kotlin.test.assertEquals(50.0f, Sprawl.layout(node2).size.height)
    kotlin.test.assertEquals(100.0f, Sprawl.layout(node2).location.x)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node2).location.y)
  }
}
