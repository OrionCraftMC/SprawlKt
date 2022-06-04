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

public class AbsoluteLayoutWithinBorderTest {
  @Test
  public fun absolute_layout_within_border(): Unit {
    val node1 = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	positionType = PositionType.Absolute,
        	position = io.github.orioncraftmc.sprawlkt.geometry.Rect(
                top = StretchDimension.Points(0.0f),
                bottom = StretchDimension.Undefined,
                start = StretchDimension.Points(0.0f),
                end = StretchDimension.Undefined,
            ),
        	size = io.github.orioncraftmc.sprawlkt.geometry.Size(
              width = StretchDimension.Points(50.0f),
              height = StretchDimension.Points(50.0f),
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
                bottom = StretchDimension.Points(0.0f),
                start = StretchDimension.Undefined,
                end = StretchDimension.Points(0.0f),
            ),
        	size = io.github.orioncraftmc.sprawlkt.geometry.Size(
              width = StretchDimension.Points(50.0f),
              height = StretchDimension.Points(50.0f),
           ),
        ),
            listOf(),
        )
    val node3 = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	positionType = PositionType.Absolute,
        	position = io.github.orioncraftmc.sprawlkt.geometry.Rect(
                top = StretchDimension.Points(0.0f),
                bottom = StretchDimension.Undefined,
                start = StretchDimension.Points(0.0f),
                end = StretchDimension.Undefined,
            ),
        	margin = io.github.orioncraftmc.sprawlkt.geometry.Rect(
                top = StretchDimension.Points(10.0f),
                bottom = StretchDimension.Points(10.0f),
                start = StretchDimension.Points(10.0f),
                end = StretchDimension.Points(10.0f),
            ),
        	size = io.github.orioncraftmc.sprawlkt.geometry.Size(
              width = StretchDimension.Points(50.0f),
              height = StretchDimension.Points(50.0f),
           ),
        ),
            listOf(),
        )
    val node4 = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	positionType = PositionType.Absolute,
        	position = io.github.orioncraftmc.sprawlkt.geometry.Rect(
                top = StretchDimension.Undefined,
                bottom = StretchDimension.Points(0.0f),
                start = StretchDimension.Undefined,
                end = StretchDimension.Points(0.0f),
            ),
        	margin = io.github.orioncraftmc.sprawlkt.geometry.Rect(
                top = StretchDimension.Points(10.0f),
                bottom = StretchDimension.Points(10.0f),
                start = StretchDimension.Points(10.0f),
                end = StretchDimension.Points(10.0f),
            ),
        	size = io.github.orioncraftmc.sprawlkt.geometry.Size(
              width = StretchDimension.Points(50.0f),
              height = StretchDimension.Points(50.0f),
           ),
        ),
            listOf(),
        )
    val rootNode = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	padding = io.github.orioncraftmc.sprawlkt.geometry.Rect(
                top = StretchDimension.Points(10.0f),
                bottom = StretchDimension.Points(10.0f),
                start = StretchDimension.Points(10.0f),
                end = StretchDimension.Points(10.0f),
            ),
        	border = io.github.orioncraftmc.sprawlkt.geometry.Rect(
                top = StretchDimension.Points(10.0f),
                bottom = StretchDimension.Points(10.0f),
                start = StretchDimension.Points(10.0f),
                end = StretchDimension.Points(10.0f),
            ),
        	size = io.github.orioncraftmc.sprawlkt.geometry.Size(
              width = StretchDimension.Points(100.0f),
              height = StretchDimension.Points(100.0f),
           ),
        ),
            listOf(node1, node2, node3, node4),
        )
    // {"style":{"size":{"width":{"unit":"Points","value":100},"height":{"unit":"Points","value":100}},"padding":{"start":{"unit":"Points","value":10},"end":{"unit":"Points","value":10},"top":{"unit":"Points","value":10},"bottom":{"unit":"Points","value":10}},"border":{"start":{"unit":"Points","value":10},"end":{"unit":"Points","value":10},"top":{"unit":"Points","value":10},"bottom":{"unit":"Points","value":10}}},"layout":{"width":100,"height":100,"x":0,"y":0},"children":[{"style":{"positionType":"absolute","size":{"width":{"unit":"Points","value":50},"height":{"unit":"Points","value":50}},"position":{"start":{"unit":"Points","value":0},"top":{"unit":"Points","value":0}}},"layout":{"width":50,"height":50,"x":10,"y":10},"children":[]},{"style":{"positionType":"absolute","size":{"width":{"unit":"Points","value":50},"height":{"unit":"Points","value":50}},"position":{"end":{"unit":"Points","value":0},"bottom":{"unit":"Points","value":0}}},"layout":{"width":50,"height":50,"x":40,"y":40},"children":[]},{"style":{"positionType":"absolute","size":{"width":{"unit":"Points","value":50},"height":{"unit":"Points","value":50}},"margin":{"start":{"unit":"Points","value":10},"end":{"unit":"Points","value":10},"top":{"unit":"Points","value":10},"bottom":{"unit":"Points","value":10}},"position":{"start":{"unit":"Points","value":0},"top":{"unit":"Points","value":0}}},"layout":{"width":50,"height":50,"x":20,"y":20},"children":[]},{"style":{"positionType":"absolute","size":{"width":{"unit":"Points","value":50},"height":{"unit":"Points","value":50}},"margin":{"start":{"unit":"Points","value":10},"end":{"unit":"Points","value":10},"top":{"unit":"Points","value":10},"bottom":{"unit":"Points","value":10}},"position":{"end":{"unit":"Points","value":0},"bottom":{"unit":"Points","value":0}}},"layout":{"width":50,"height":50,"x":30,"y":30},"children":[]}]}
    Sprawl.computeLayout(rootNode, Size.Companion.undefinedNumber())
    kotlin.test.assertEquals(100.0f, Sprawl.layout(rootNode).size.width)
    kotlin.test.assertEquals(100.0f, Sprawl.layout(rootNode).size.height)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(rootNode).location.x)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(rootNode).location.y)
    kotlin.test.assertEquals(50.0f, Sprawl.layout(node1).size.width)
    kotlin.test.assertEquals(50.0f, Sprawl.layout(node1).size.height)
    kotlin.test.assertEquals(10.0f, Sprawl.layout(node1).location.x)
    kotlin.test.assertEquals(10.0f, Sprawl.layout(node1).location.y)
    kotlin.test.assertEquals(50.0f, Sprawl.layout(node2).size.width)
    kotlin.test.assertEquals(50.0f, Sprawl.layout(node2).size.height)
    kotlin.test.assertEquals(40.0f, Sprawl.layout(node2).location.x)
    kotlin.test.assertEquals(40.0f, Sprawl.layout(node2).location.y)
    kotlin.test.assertEquals(50.0f, Sprawl.layout(node3).size.width)
    kotlin.test.assertEquals(50.0f, Sprawl.layout(node3).size.height)
    kotlin.test.assertEquals(20.0f, Sprawl.layout(node3).location.x)
    kotlin.test.assertEquals(20.0f, Sprawl.layout(node3).location.y)
    kotlin.test.assertEquals(50.0f, Sprawl.layout(node4).size.width)
    kotlin.test.assertEquals(50.0f, Sprawl.layout(node4).size.height)
    kotlin.test.assertEquals(30.0f, Sprawl.layout(node4).location.x)
    kotlin.test.assertEquals(30.0f, Sprawl.layout(node4).location.y)
  }
}
