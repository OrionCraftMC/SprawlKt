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

public class PercentagePositionBottomRightTest {
  @Test
  public fun percentage_position_bottom_right(): Unit {
    val node1 = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	position = io.github.orioncraftmc.sprawlkt.geometry.Rect(
                top = StretchDimension.Undefined,
                bottom = StretchDimension.Percent(0.1f),
                start = StretchDimension.Undefined,
                end = StretchDimension.Percent(0.2f),
            ),
        	size = io.github.orioncraftmc.sprawlkt.geometry.Size(
              width = StretchDimension.Percent(0.55f),
              height = StretchDimension.Percent(0.15f),
           ),
        ),
            listOf(),
        )
    val rootNode = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	size = io.github.orioncraftmc.sprawlkt.geometry.Size(
              width = StretchDimension.Points(500.0f),
              height = StretchDimension.Points(500.0f),
           ),
        ),
            listOf(node1),
        )
    // {"style":{"flexDirection":"row","size":{"width":{"unit":"Points","value":500},"height":{"unit":"Points","value":500}}},"layout":{"width":500,"height":500,"x":0,"y":0},"children":[{"style":{"size":{"width":{"unit":"Percent","value":0.55},"height":{"unit":"Percent","value":0.15}},"position":{"end":{"unit":"Percent","value":0.2},"bottom":{"unit":"Percent","value":0.1}}},"layout":{"width":275,"height":75,"x":-100,"y":-50},"children":[]}]}
    Sprawl.computeLayout(rootNode, Size.Companion.undefinedNumber())
    kotlin.test.assertEquals(500.0f, Sprawl.layout(rootNode).size.width)
    kotlin.test.assertEquals(500.0f, Sprawl.layout(rootNode).size.height)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(rootNode).location.x)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(rootNode).location.y)
    kotlin.test.assertEquals(275.0f, Sprawl.layout(node1).size.width)
    kotlin.test.assertEquals(75.0f, Sprawl.layout(node1).size.height)
    kotlin.test.assertEquals(-100.0f, Sprawl.layout(node1).location.x)
    kotlin.test.assertEquals(-50.0f, Sprawl.layout(node1).location.y)
  }
}
