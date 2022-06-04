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

public class BorderCenterChildTest {
  @Test
  public fun border_center_child(): Unit {
    val node1 = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	size = io.github.orioncraftmc.sprawlkt.geometry.Size(
              width = StretchDimension.Points(10.0f),
              height = StretchDimension.Points(10.0f),
           ),
        ),
            listOf(),
        )
    val rootNode = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	alignItems = AlignItems.Center,
        	justifyContent = JustifyContent.Center,
        	border = io.github.orioncraftmc.sprawlkt.geometry.Rect(
                top = StretchDimension.Points(10.0f),
                bottom = StretchDimension.Points(20.0f),
                start = StretchDimension.Undefined,
                end = StretchDimension.Undefined,
            ),
        	size = io.github.orioncraftmc.sprawlkt.geometry.Size(
              width = StretchDimension.Points(100.0f),
              height = StretchDimension.Points(100.0f),
           ),
        ),
            listOf(node1),
        )
    // {"style":{"alignItems":"center","justifyContent":"center","size":{"width":{"unit":"Points","value":100},"height":{"unit":"Points","value":100}},"border":{"top":{"unit":"Points","value":10},"bottom":{"unit":"Points","value":20}}},"layout":{"width":100,"height":100,"x":0,"y":0},"children":[{"style":{"size":{"width":{"unit":"Points","value":10},"height":{"unit":"Points","value":10}}},"layout":{"width":10,"height":10,"x":45,"y":40},"children":[]}]}
    Sprawl.computeLayout(rootNode, Size.Companion.undefinedNumber())
    kotlin.test.assertEquals(100.0f, Sprawl.layout(rootNode).size.width)
    kotlin.test.assertEquals(100.0f, Sprawl.layout(rootNode).size.height)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(rootNode).location.x)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(rootNode).location.y)
    kotlin.test.assertEquals(10.0f, Sprawl.layout(node1).size.width)
    kotlin.test.assertEquals(10.0f, Sprawl.layout(node1).size.height)
    kotlin.test.assertEquals(45.0f, Sprawl.layout(node1).location.x)
    kotlin.test.assertEquals(40.0f, Sprawl.layout(node1).location.y)
  }
}
