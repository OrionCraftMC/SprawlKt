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

public class AbsoluteLayoutPercentageBottomBasedOnParentHeightTest {
  @Test
  public fun absolute_layout_percentage_bottom_based_on_parent_height(): Unit {
    val node1 = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	positionType = PositionType.Absolute,
        	position = io.github.orioncraftmc.sprawlkt.geometry.Rect(
                top = StretchDimension.Percent(0.5f),
                bottom = StretchDimension.Undefined,
                start = StretchDimension.Undefined,
                end = StretchDimension.Undefined,
            ),
        	size = io.github.orioncraftmc.sprawlkt.geometry.Size(
              width = StretchDimension.Points(10.0f),
              height = StretchDimension.Points(10.0f),
           ),
        ),
            listOf(),
        )
    val node2 = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	positionType = PositionType.Absolute,
        	position = io.github.orioncraftmc.sprawlkt.geometry.Rect(
                top = StretchDimension.Undefined,
                bottom = StretchDimension.Percent(0.5f),
                start = StretchDimension.Undefined,
                end = StretchDimension.Undefined,
            ),
        	size = io.github.orioncraftmc.sprawlkt.geometry.Size(
              width = StretchDimension.Points(10.0f),
              height = StretchDimension.Points(10.0f),
           ),
        ),
            listOf(),
        )
    val node3 = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	positionType = PositionType.Absolute,
        	position = io.github.orioncraftmc.sprawlkt.geometry.Rect(
                top = StretchDimension.Percent(0.1f),
                bottom = StretchDimension.Percent(0.1f),
                start = StretchDimension.Undefined,
                end = StretchDimension.Undefined,
            ),
        	size = io.github.orioncraftmc.sprawlkt.geometry.Size(
              width = StretchDimension.Points(10.0f),
              height = StretchDimension.Auto,
           ),
        ),
            listOf(),
        )
    val rootNode = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	size = io.github.orioncraftmc.sprawlkt.geometry.Size(
              width = StretchDimension.Points(100.0f),
              height = StretchDimension.Points(200.0f),
           ),
        ),
            listOf(node1, node2, node3),
        )
    // {"style":{"size":{"width":{"unit":"Points","value":100},"height":{"unit":"Points","value":200}}},"layout":{"width":100,"height":200,"x":0,"y":0},"children":[{"style":{"positionType":"absolute","size":{"width":{"unit":"Points","value":10},"height":{"unit":"Points","value":10}},"position":{"top":{"unit":"Percent","value":0.5}}},"layout":{"width":10,"height":10,"x":0,"y":100},"children":[]},{"style":{"positionType":"absolute","size":{"width":{"unit":"Points","value":10},"height":{"unit":"Points","value":10}},"position":{"bottom":{"unit":"Percent","value":0.5}}},"layout":{"width":10,"height":10,"x":0,"y":90},"children":[]},{"style":{"positionType":"absolute","size":{"width":{"unit":"Points","value":10}},"position":{"top":{"unit":"Percent","value":0.1},"bottom":{"unit":"Percent","value":0.1}}},"layout":{"width":10,"height":160,"x":0,"y":20},"children":[]}]}
    Sprawl.computeLayout(rootNode, Size.Companion.undefinedNumber())
    kotlin.test.assertEquals(100.0f, Sprawl.layout(rootNode).size.width)
    kotlin.test.assertEquals(200.0f, Sprawl.layout(rootNode).size.height)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(rootNode).location.x)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(rootNode).location.y)
    kotlin.test.assertEquals(10.0f, Sprawl.layout(node1).size.width)
    kotlin.test.assertEquals(10.0f, Sprawl.layout(node1).size.height)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node1).location.x)
    kotlin.test.assertEquals(100.0f, Sprawl.layout(node1).location.y)
    kotlin.test.assertEquals(10.0f, Sprawl.layout(node2).size.width)
    kotlin.test.assertEquals(10.0f, Sprawl.layout(node2).size.height)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node2).location.x)
    kotlin.test.assertEquals(90.0f, Sprawl.layout(node2).location.y)
    kotlin.test.assertEquals(10.0f, Sprawl.layout(node3).size.width)
    kotlin.test.assertEquals(160.0f, Sprawl.layout(node3).size.height)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node3).location.x)
    kotlin.test.assertEquals(20.0f, Sprawl.layout(node3).location.y)
  }
}
