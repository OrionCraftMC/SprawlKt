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

public class AbsoluteLayoutAlignItemsAndJustifyContentCenterAndBottomPositionTest {
  @Test
  public fun absolute_layout_align_items_and_justify_content_center_and_bottom_position(): Unit {
    val node1 = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	positionType = PositionType.Absolute,
        	position = io.github.orioncraftmc.sprawlkt.geometry.Rect(
                top = StretchDimension.Undefined,
                bottom = StretchDimension.Points(10.0f),
                start = StretchDimension.Undefined,
                end = StretchDimension.Undefined,
            ),
        	size = io.github.orioncraftmc.sprawlkt.geometry.Size(
              width = StretchDimension.Points(60.0f),
              height = StretchDimension.Points(40.0f),
           ),
        ),
            listOf(),
        )
    val rootNode = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	alignItems = AlignItems.Center,
        	justifyContent = JustifyContent.Center,
        	size = io.github.orioncraftmc.sprawlkt.geometry.Size(
              width = StretchDimension.Points(110.0f),
              height = StretchDimension.Points(100.0f),
           ),
        ),
            listOf(node1),
        )
    // {"style":{"alignItems":"center","justifyContent":"center","size":{"width":{"unit":"Points","value":110},"height":{"unit":"Points","value":100}}},"layout":{"width":110,"height":100,"x":0,"y":0},"children":[{"style":{"positionType":"absolute","size":{"width":{"unit":"Points","value":60},"height":{"unit":"Points","value":40}},"position":{"bottom":{"unit":"Points","value":10}}},"layout":{"width":60,"height":40,"x":25,"y":50},"children":[]}]}
    Sprawl.computeLayout(rootNode, Size.Companion.undefinedNumber())
    kotlin.test.assertEquals(110.0f, Sprawl.layout(rootNode).size.width)
    kotlin.test.assertEquals(100.0f, Sprawl.layout(rootNode).size.height)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(rootNode).location.x)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(rootNode).location.y)
    kotlin.test.assertEquals(60.0f, Sprawl.layout(node1).size.width)
    kotlin.test.assertEquals(40.0f, Sprawl.layout(node1).size.height)
    kotlin.test.assertEquals(25.0f, Sprawl.layout(node1).location.x)
    kotlin.test.assertEquals(50.0f, Sprawl.layout(node1).location.y)
  }
}
