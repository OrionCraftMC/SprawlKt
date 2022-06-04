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

public class PercentageAbsolutePositionTest {
  @Test
  public fun percentage_absolute_position(): Unit {
    val node1 = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	positionType = PositionType.Absolute,
        	position = io.github.orioncraftmc.sprawlkt.geometry.Rect(
                top = StretchDimension.Percent(0.1f),
                bottom = StretchDimension.Undefined,
                start = StretchDimension.Percent(0.3f),
                end = StretchDimension.Undefined,
            ),
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
        	flexDirection = FlexDirection.Column,
        	size = io.github.orioncraftmc.sprawlkt.geometry.Size(
              width = StretchDimension.Points(200.0f),
              height = StretchDimension.Points(100.0f),
           ),
        ),
            listOf(node1),
        )
    // {"style":{"flexDirection":"column","size":{"width":{"unit":"Points","value":200},"height":{"unit":"Points","value":100}}},"layout":{"width":200,"height":100,"x":0,"y":0},"children":[{"style":{"positionType":"absolute","size":{"width":{"unit":"Points","value":10},"height":{"unit":"Points","value":10}},"position":{"start":{"unit":"Percent","value":0.3},"top":{"unit":"Percent","value":0.1}}},"layout":{"width":10,"height":10,"x":60,"y":10},"children":[]}]}
    Sprawl.computeLayout(rootNode, Size.Companion.undefinedNumber())
    kotlin.test.assertEquals(200.0f, Sprawl.layout(rootNode).size.width)
    kotlin.test.assertEquals(100.0f, Sprawl.layout(rootNode).size.height)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(rootNode).location.x)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(rootNode).location.y)
    kotlin.test.assertEquals(10.0f, Sprawl.layout(node1).size.width)
    kotlin.test.assertEquals(10.0f, Sprawl.layout(node1).size.height)
    kotlin.test.assertEquals(60.0f, Sprawl.layout(node1).location.x)
    kotlin.test.assertEquals(10.0f, Sprawl.layout(node1).location.y)
  }
}
