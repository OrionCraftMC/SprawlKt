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

public class JustifyContentColumnMinHeightAndMarginBottomTest {
  @Test
  public fun justify_content_column_min_height_and_margin_bottom(): Unit {
    val node1 = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	margin = io.github.orioncraftmc.sprawlkt.geometry.Rect(
                top = StretchDimension.Undefined,
                bottom = StretchDimension.Points(10.0f),
                start = StretchDimension.Undefined,
                end = StretchDimension.Undefined,
            ),
        	size = io.github.orioncraftmc.sprawlkt.geometry.Size(
              width = StretchDimension.Points(20.0f),
              height = StretchDimension.Points(20.0f),
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
              height = StretchDimension.Points(50.0f),
           ),
        ),
            listOf(node1),
        )
    // {"style":{"flexDirection":"column","justifyContent":"center","minSize":{"height":{"unit":"Points","value":50}}},"layout":{"width":20,"height":50,"x":0,"y":0},"children":[{"style":{"size":{"width":{"unit":"Points","value":20},"height":{"unit":"Points","value":20}},"margin":{"bottom":{"unit":"Points","value":10}}},"layout":{"width":20,"height":20,"x":0,"y":10},"children":[]}]}
    Sprawl.computeLayout(rootNode, Size.Companion.undefinedNumber())
    kotlin.test.assertEquals(20.0f, Sprawl.layout(rootNode).size.width)
    kotlin.test.assertEquals(50.0f, Sprawl.layout(rootNode).size.height)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(rootNode).location.x)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(rootNode).location.y)
    kotlin.test.assertEquals(20.0f, Sprawl.layout(node1).size.width)
    kotlin.test.assertEquals(20.0f, Sprawl.layout(node1).size.height)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node1).location.x)
    kotlin.test.assertEquals(10.0f, Sprawl.layout(node1).location.y)
  }
}
