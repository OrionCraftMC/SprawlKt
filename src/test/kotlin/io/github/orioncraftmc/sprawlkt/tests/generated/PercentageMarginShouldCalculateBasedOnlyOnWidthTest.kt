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

public class PercentageMarginShouldCalculateBasedOnlyOnWidthTest {
  @Test
  public fun percentage_margin_should_calculate_based_only_on_width(): Unit {
    val node1 = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
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
        	flexDirection = FlexDirection.Column,
        	margin = io.github.orioncraftmc.sprawlkt.geometry.Rect(
                top = StretchDimension.Percent(0.1f),
                bottom = StretchDimension.Percent(0.1f),
                start = StretchDimension.Percent(0.1f),
                end = StretchDimension.Percent(0.1f),
            ),
        	flexGrow = 1.0f,
        ),
            listOf(node1),
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
            listOf(node2),
        )
    // {"style":{"flexDirection":"column","size":{"width":{"unit":"Points","value":200},"height":{"unit":"Points","value":100}}},"layout":{"width":200,"height":100,"x":0,"y":0},"children":[{"style":{"flexDirection":"column","flexGrow":1,"margin":{"start":{"unit":"Percent","value":0.1},"end":{"unit":"Percent","value":0.1},"top":{"unit":"Percent","value":0.1},"bottom":{"unit":"Percent","value":0.1}}},"layout":{"width":160,"height":60,"x":20,"y":20},"children":[{"style":{"size":{"width":{"unit":"Points","value":10},"height":{"unit":"Points","value":10}}},"layout":{"width":10,"height":10,"x":0,"y":0},"children":[]}]}]}
    Sprawl.computeLayout(rootNode, Size.Companion.undefinedNumber())
    kotlin.test.assertEquals(200.0f, Sprawl.layout(rootNode).size.width)
    kotlin.test.assertEquals(100.0f, Sprawl.layout(rootNode).size.height)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(rootNode).location.x)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(rootNode).location.y)
    kotlin.test.assertEquals(160.0f, Sprawl.layout(node2).size.width)
    kotlin.test.assertEquals(60.0f, Sprawl.layout(node2).size.height)
    kotlin.test.assertEquals(20.0f, Sprawl.layout(node2).location.x)
    kotlin.test.assertEquals(20.0f, Sprawl.layout(node2).location.y)
    kotlin.test.assertEquals(10.0f, Sprawl.layout(node1).size.width)
    kotlin.test.assertEquals(10.0f, Sprawl.layout(node1).size.height)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node1).location.x)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node1).location.y)
  }
}
