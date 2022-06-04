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

public class PaddingAlignEndChildTest {
  @Test
  public fun padding_align_end_child(): Unit {
    val node1 = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	padding = io.github.orioncraftmc.sprawlkt.geometry.Rect(
                top = StretchDimension.Points(20.0f),
                bottom = StretchDimension.Points(20.0f),
                start = StretchDimension.Points(20.0f),
                end = StretchDimension.Points(20.0f),
            ),
        	size = io.github.orioncraftmc.sprawlkt.geometry.Size(
              width = StretchDimension.Points(100.0f),
              height = StretchDimension.Points(100.0f),
           ),
        ),
            listOf(),
        )
    val rootNode = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	alignItems = AlignItems.FlexEnd,
        	justifyContent = JustifyContent.FlexEnd,
        	size = io.github.orioncraftmc.sprawlkt.geometry.Size(
              width = StretchDimension.Points(200.0f),
              height = StretchDimension.Points(200.0f),
           ),
        ),
            listOf(node1),
        )
    // {"style":{"alignItems":"flex-end","justifyContent":"flex-end","size":{"width":{"unit":"Points","value":200},"height":{"unit":"Points","value":200}}},"layout":{"width":200,"height":200,"x":0,"y":0},"children":[{"style":{"size":{"width":{"unit":"Points","value":100},"height":{"unit":"Points","value":100}},"padding":{"start":{"unit":"Points","value":20},"end":{"unit":"Points","value":20},"top":{"unit":"Points","value":20},"bottom":{"unit":"Points","value":20}}},"layout":{"width":100,"height":100,"x":100,"y":100},"children":[]}]}
    Sprawl.computeLayout(rootNode, Size.Companion.undefinedNumber())
    kotlin.test.assertEquals(200.0f, Sprawl.layout(rootNode).size.width)
    kotlin.test.assertEquals(200.0f, Sprawl.layout(rootNode).size.height)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(rootNode).location.x)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(rootNode).location.y)
    kotlin.test.assertEquals(100.0f, Sprawl.layout(node1).size.width)
    kotlin.test.assertEquals(100.0f, Sprawl.layout(node1).size.height)
    kotlin.test.assertEquals(100.0f, Sprawl.layout(node1).location.x)
    kotlin.test.assertEquals(100.0f, Sprawl.layout(node1).location.y)
  }
}
