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

public class AlignItemsFlexEndChildWithoutMarginBiggerThanParentTest {
  @Test
  public fun align_items_flex_end_child_without_margin_bigger_than_parent(): Unit {
    val node1 = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	size = io.github.orioncraftmc.sprawlkt.geometry.Size(
              width = StretchDimension.Points(70.0f),
              height = StretchDimension.Points(70.0f),
           ),
        ),
            listOf(),
        )
    val node2 = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	alignItems = AlignItems.FlexEnd,
        ),
            listOf(node1),
        )
    val rootNode = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	alignItems = AlignItems.Center,
        	justifyContent = JustifyContent.Center,
        	size = io.github.orioncraftmc.sprawlkt.geometry.Size(
              width = StretchDimension.Points(50.0f),
              height = StretchDimension.Points(50.0f),
           ),
        ),
            listOf(node2),
        )
    // {"style":{"alignItems":"center","justifyContent":"center","size":{"width":{"unit":"Points","value":50},"height":{"unit":"Points","value":50}}},"layout":{"width":50,"height":50,"x":0,"y":0},"children":[{"style":{"alignItems":"flex-end"},"layout":{"width":70,"height":70,"x":-10,"y":-10},"children":[{"style":{"size":{"width":{"unit":"Points","value":70},"height":{"unit":"Points","value":70}}},"layout":{"width":70,"height":70,"x":0,"y":0},"children":[]}]}]}
    Sprawl.computeLayout(rootNode, Size.Companion.undefinedNumber())
    kotlin.test.assertEquals(50.0f, Sprawl.layout(rootNode).size.width)
    kotlin.test.assertEquals(50.0f, Sprawl.layout(rootNode).size.height)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(rootNode).location.x)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(rootNode).location.y)
    kotlin.test.assertEquals(70.0f, Sprawl.layout(node2).size.width)
    kotlin.test.assertEquals(70.0f, Sprawl.layout(node2).size.height)
    kotlin.test.assertEquals(-10.0f, Sprawl.layout(node2).location.x)
    kotlin.test.assertEquals(-10.0f, Sprawl.layout(node2).location.y)
    kotlin.test.assertEquals(70.0f, Sprawl.layout(node1).size.width)
    kotlin.test.assertEquals(70.0f, Sprawl.layout(node1).size.height)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node1).location.x)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node1).location.y)
  }
}
