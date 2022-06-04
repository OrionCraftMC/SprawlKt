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

public class JustifyContentMinWidthWithPaddingChildWidthLowerThanParentTest {
  @Test
  public fun justify_content_min_width_with_padding_child_width_lower_than_parent(): Unit {
    val node1 = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	size = io.github.orioncraftmc.sprawlkt.geometry.Size(
              width = StretchDimension.Points(199.0f),
              height = StretchDimension.Points(100.0f),
           ),
        ),
            listOf(),
        )
    val node2 = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	justifyContent = JustifyContent.Center,
        	padding = io.github.orioncraftmc.sprawlkt.geometry.Rect(
                top = StretchDimension.Undefined,
                bottom = StretchDimension.Undefined,
                start = StretchDimension.Points(100.0f),
                end = StretchDimension.Points(100.0f),
            ),
        	minSize = io.github.orioncraftmc.sprawlkt.geometry.Size(
              width = StretchDimension.Points(400.0f),
              height = StretchDimension.Auto,
           ),
        ),
            listOf(node1),
        )
    val node3 = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (

        ),
            listOf(node2),
        )
    val rootNode = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	flexDirection = FlexDirection.Column,
        	size = io.github.orioncraftmc.sprawlkt.geometry.Size(
              width = StretchDimension.Points(1080.0f),
              height = StretchDimension.Points(1584.0f),
           ),
        ),
            listOf(node3),
        )
    // {"style":{"flexDirection":"column","alignContent":"stretch","size":{"width":{"unit":"Points","value":1080},"height":{"unit":"Points","value":1584}}},"layout":{"width":1080,"height":1584,"x":0,"y":0},"children":[{"style":{"flexDirection":"row","alignContent":"stretch"},"layout":{"width":1080,"height":100,"x":0,"y":0},"children":[{"style":{"flexDirection":"row","alignContent":"stretch","justifyContent":"center","minSize":{"width":{"unit":"Points","value":400}},"padding":{"start":{"unit":"Points","value":100},"end":{"unit":"Points","value":100}}},"layout":{"width":400,"height":100,"x":0,"y":0},"children":[{"style":{"flexDirection":"row","alignContent":"stretch","size":{"width":{"unit":"Points","value":199},"height":{"unit":"Points","value":100}}},"layout":{"width":199,"height":100,"x":101,"y":0},"children":[]}]}]}]}
    Sprawl.computeLayout(rootNode, Size.Companion.undefinedNumber())
    kotlin.test.assertEquals(1080.0f, Sprawl.layout(rootNode).size.width)
    kotlin.test.assertEquals(1584.0f, Sprawl.layout(rootNode).size.height)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(rootNode).location.x)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(rootNode).location.y)
    kotlin.test.assertEquals(1080.0f, Sprawl.layout(node3).size.width)
    kotlin.test.assertEquals(100.0f, Sprawl.layout(node3).size.height)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node3).location.x)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node3).location.y)
    kotlin.test.assertEquals(400.0f, Sprawl.layout(node2).size.width)
    kotlin.test.assertEquals(100.0f, Sprawl.layout(node2).size.height)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node2).location.x)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node2).location.y)
    kotlin.test.assertEquals(199.0f, Sprawl.layout(node1).size.width)
    kotlin.test.assertEquals(100.0f, Sprawl.layout(node1).size.height)
    kotlin.test.assertEquals(101.0f, Sprawl.layout(node1).location.x)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node1).location.y)
  }
}
