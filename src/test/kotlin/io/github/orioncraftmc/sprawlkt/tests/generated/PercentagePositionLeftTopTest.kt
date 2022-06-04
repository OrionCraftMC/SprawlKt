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

public class PercentagePositionLeftTopTest {
  @Test
  public fun percentage_position_left_top(): Unit {
    val node1 = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	position = io.github.orioncraftmc.sprawlkt.geometry.Rect(
                top = StretchDimension.Percent(0.2f),
                bottom = StretchDimension.Undefined,
                start = StretchDimension.Percent(0.1f),
                end = StretchDimension.Undefined,
            ),
        	size = io.github.orioncraftmc.sprawlkt.geometry.Size(
              width = StretchDimension.Percent(0.45f),
              height = StretchDimension.Percent(0.55f),
           ),
        ),
            listOf(),
        )
    val rootNode = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	size = io.github.orioncraftmc.sprawlkt.geometry.Size(
              width = StretchDimension.Points(400.0f),
              height = StretchDimension.Points(400.0f),
           ),
        ),
            listOf(node1),
        )
    // {"style":{"flexDirection":"row","size":{"width":{"unit":"Points","value":400},"height":{"unit":"Points","value":400}}},"layout":{"width":400,"height":400,"x":0,"y":0},"children":[{"style":{"size":{"width":{"unit":"Percent","value":0.45},"height":{"unit":"Percent","value":0.55}},"position":{"start":{"unit":"Percent","value":0.1},"top":{"unit":"Percent","value":0.2}}},"layout":{"width":180,"height":220,"x":40,"y":80},"children":[]}]}
    Sprawl.computeLayout(rootNode, Size.Companion.undefinedNumber())
    kotlin.test.assertEquals(400.0f, Sprawl.layout(rootNode).size.width)
    kotlin.test.assertEquals(400.0f, Sprawl.layout(rootNode).size.height)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(rootNode).location.x)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(rootNode).location.y)
    kotlin.test.assertEquals(180.0f, Sprawl.layout(node1).size.width)
    kotlin.test.assertEquals(220.0f, Sprawl.layout(node1).size.height)
    kotlin.test.assertEquals(40.0f, Sprawl.layout(node1).location.x)
    kotlin.test.assertEquals(80.0f, Sprawl.layout(node1).location.y)
  }
}
