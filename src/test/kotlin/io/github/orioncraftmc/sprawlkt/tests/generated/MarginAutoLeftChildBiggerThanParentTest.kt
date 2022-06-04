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

public class MarginAutoLeftChildBiggerThanParentTest {
  @Test
  public fun margin_auto_left_child_bigger_than_parent(): Unit {
    val node1 = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	margin = io.github.orioncraftmc.sprawlkt.geometry.Rect(
                top = StretchDimension.Undefined,
                bottom = StretchDimension.Undefined,
                start = StretchDimension.Auto,
                end = StretchDimension.Undefined,
            ),
        	size = io.github.orioncraftmc.sprawlkt.geometry.Size(
              width = StretchDimension.Points(72.0f),
              height = StretchDimension.Points(72.0f),
           ),
        ),
            listOf(),
        )
    val rootNode = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	justifyContent = JustifyContent.Center,
        	size = io.github.orioncraftmc.sprawlkt.geometry.Size(
              width = StretchDimension.Points(52.0f),
              height = StretchDimension.Points(52.0f),
           ),
        ),
            listOf(node1),
        )
    // {"style":{"justifyContent":"center","size":{"width":{"unit":"Points","value":52},"height":{"unit":"Points","value":52}}},"layout":{"width":52,"height":52,"x":0,"y":0},"children":[{"style":{"size":{"width":{"unit":"Points","value":72},"height":{"unit":"Points","value":72}},"margin":{"start":{"unit":"Auto"}}},"layout":{"width":52,"height":72,"x":0,"y":0},"children":[]}]}
    Sprawl.computeLayout(rootNode, Size.Companion.undefinedNumber())
    kotlin.test.assertEquals(52.0f, Sprawl.layout(rootNode).size.width)
    kotlin.test.assertEquals(52.0f, Sprawl.layout(rootNode).size.height)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(rootNode).location.x)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(rootNode).location.y)
    kotlin.test.assertEquals(52.0f, Sprawl.layout(node1).size.width)
    kotlin.test.assertEquals(72.0f, Sprawl.layout(node1).size.height)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node1).location.x)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node1).location.y)
  }
}
