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

public class JustifyContentOverflowMinMaxTest {
  @Test
  public fun justify_content_overflow_min_max(): Unit {
    val node1 = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	flexShrink = 0.0f,
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
        	flexShrink = 0.0f,
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
        	flexShrink = 0.0f,
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
        	flexDirection = FlexDirection.Column,
        	justifyContent = JustifyContent.Center,
        	minSize = io.github.orioncraftmc.sprawlkt.geometry.Size(
              width = StretchDimension.Auto,
              height = StretchDimension.Points(100.0f),
           ),
        	maxSize = io.github.orioncraftmc.sprawlkt.geometry.Size(
              width = StretchDimension.Auto,
              height = StretchDimension.Points(110.0f),
           ),
        ),
            listOf(node1, node2, node3),
        )
    // {"style":{"flexDirection":"column","justifyContent":"center","minSize":{"height":{"unit":"Points","value":100}},"maxSize":{"height":{"unit":"Points","value":110}}},"layout":{"width":50,"height":110,"x":0,"y":0},"children":[{"style":{"flexShrink":0,"size":{"width":{"unit":"Points","value":50},"height":{"unit":"Points","value":50}}},"layout":{"width":50,"height":50,"x":0,"y":-20},"children":[]},{"style":{"flexShrink":0,"size":{"width":{"unit":"Points","value":50},"height":{"unit":"Points","value":50}}},"layout":{"width":50,"height":50,"x":0,"y":30},"children":[]},{"style":{"flexShrink":0,"size":{"width":{"unit":"Points","value":50},"height":{"unit":"Points","value":50}}},"layout":{"width":50,"height":50,"x":0,"y":80},"children":[]}]}
    Sprawl.computeLayout(rootNode, Size.Companion.undefinedNumber())
    kotlin.test.assertEquals(50.0f, Sprawl.layout(rootNode).size.width)
    kotlin.test.assertEquals(110.0f, Sprawl.layout(rootNode).size.height)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(rootNode).location.x)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(rootNode).location.y)
    kotlin.test.assertEquals(50.0f, Sprawl.layout(node1).size.width)
    kotlin.test.assertEquals(50.0f, Sprawl.layout(node1).size.height)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node1).location.x)
    kotlin.test.assertEquals(-20.0f, Sprawl.layout(node1).location.y)
    kotlin.test.assertEquals(50.0f, Sprawl.layout(node2).size.width)
    kotlin.test.assertEquals(50.0f, Sprawl.layout(node2).size.height)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node2).location.x)
    kotlin.test.assertEquals(30.0f, Sprawl.layout(node2).location.y)
    kotlin.test.assertEquals(50.0f, Sprawl.layout(node3).size.width)
    kotlin.test.assertEquals(50.0f, Sprawl.layout(node3).size.height)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node3).location.x)
    kotlin.test.assertEquals(80.0f, Sprawl.layout(node3).location.y)
  }
}
