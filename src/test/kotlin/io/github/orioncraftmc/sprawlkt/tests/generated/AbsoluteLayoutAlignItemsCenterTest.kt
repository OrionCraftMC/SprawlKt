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

public class AbsoluteLayoutAlignItemsCenterTest {
  @Test
  public fun absolute_layout_align_items_center(): Unit {
    val node1 = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	positionType = PositionType.Absolute,
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
        	size = io.github.orioncraftmc.sprawlkt.geometry.Size(
              width = StretchDimension.Points(110.0f),
              height = StretchDimension.Points(100.0f),
           ),
        ),
            listOf(node1),
        )
    // {"style":{"alignItems":"center","size":{"width":{"unit":"Points","value":110},"height":{"unit":"Points","value":100}}},"layout":{"width":110,"height":100,"x":0,"y":0},"children":[{"style":{"positionType":"absolute","size":{"width":{"unit":"Points","value":60},"height":{"unit":"Points","value":40}}},"layout":{"width":60,"height":40,"x":0,"y":30},"children":[]}]}
    Sprawl.computeLayout(rootNode, Size.Companion.undefinedNumber())
    kotlin.test.assertEquals(110.0f, Sprawl.layout(rootNode).size.width)
    kotlin.test.assertEquals(100.0f, Sprawl.layout(rootNode).size.height)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(rootNode).location.x)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(rootNode).location.y)
    kotlin.test.assertEquals(60.0f, Sprawl.layout(node1).size.width)
    kotlin.test.assertEquals(40.0f, Sprawl.layout(node1).size.height)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node1).location.x)
    kotlin.test.assertEquals(30.0f, Sprawl.layout(node1).location.y)
  }
}
