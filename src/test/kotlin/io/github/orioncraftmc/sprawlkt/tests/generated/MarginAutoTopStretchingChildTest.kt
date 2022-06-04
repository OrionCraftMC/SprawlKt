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

public class MarginAutoTopStretchingChildTest {
  @Test
  public fun margin_auto_top_stretching_child(): Unit {
    val node1 = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	margin = io.github.orioncraftmc.sprawlkt.geometry.Rect(
                top = StretchDimension.Auto,
                bottom = StretchDimension.Undefined,
                start = StretchDimension.Undefined,
                end = StretchDimension.Undefined,
            ),
        	flexGrow = 1.0f,
        	flexBasis = StretchDimension.Percent(0.0f),
        ),
            listOf(),
        )
    val node2 = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	size = io.github.orioncraftmc.sprawlkt.geometry.Size(
              width = StretchDimension.Points(50.0f),
              height = StretchDimension.Points(50.0f),
           ),
        ),
            listOf(),
        )
    val rootNode = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	alignItems = AlignItems.Center,
        	size = io.github.orioncraftmc.sprawlkt.geometry.Size(
              width = StretchDimension.Points(200.0f),
              height = StretchDimension.Points(200.0f),
           ),
        ),
            listOf(node1, node2),
        )
    // {"style":{"alignItems":"center","size":{"width":{"unit":"Points","value":200},"height":{"unit":"Points","value":200}}},"layout":{"width":200,"height":200,"x":0,"y":0},"children":[{"style":{"flexGrow":1,"flexShrink":1,"flexBasis":{"unit":"Percent","value":0},"margin":{"top":{"unit":"Auto"}}},"layout":{"width":150,"height":0,"x":0,"y":200},"children":[]},{"style":{"size":{"width":{"unit":"Points","value":50},"height":{"unit":"Points","value":50}}},"layout":{"width":50,"height":50,"x":150,"y":75},"children":[]}]}
    Sprawl.computeLayout(rootNode, Size.Companion.undefinedNumber())
    kotlin.test.assertEquals(200.0f, Sprawl.layout(rootNode).size.width)
    kotlin.test.assertEquals(200.0f, Sprawl.layout(rootNode).size.height)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(rootNode).location.x)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(rootNode).location.y)
    kotlin.test.assertEquals(150.0f, Sprawl.layout(node1).size.width)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node1).size.height)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node1).location.x)
    kotlin.test.assertEquals(200.0f, Sprawl.layout(node1).location.y)
    kotlin.test.assertEquals(50.0f, Sprawl.layout(node2).size.width)
    kotlin.test.assertEquals(50.0f, Sprawl.layout(node2).size.height)
    kotlin.test.assertEquals(150.0f, Sprawl.layout(node2).location.x)
    kotlin.test.assertEquals(75.0f, Sprawl.layout(node2).location.y)
  }
}
