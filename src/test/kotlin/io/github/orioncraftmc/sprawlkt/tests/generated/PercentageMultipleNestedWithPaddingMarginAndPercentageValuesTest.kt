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

public class PercentageMultipleNestedWithPaddingMarginAndPercentageValuesTest {
  @Test
  public fun percentage_multiple_nested_with_padding_margin_and_percentage_values(): Unit {
    val node1 = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	margin = io.github.orioncraftmc.sprawlkt.geometry.Rect(
                top = StretchDimension.Percent(0.05f),
                bottom = StretchDimension.Percent(0.05f),
                start = StretchDimension.Percent(0.05f),
                end = StretchDimension.Percent(0.05f),
            ),
        	padding = io.github.orioncraftmc.sprawlkt.geometry.Rect(
                top = StretchDimension.Points(3.0f),
                bottom = StretchDimension.Points(3.0f),
                start = StretchDimension.Points(3.0f),
                end = StretchDimension.Points(3.0f),
            ),
        	size = io.github.orioncraftmc.sprawlkt.geometry.Size(
              width = StretchDimension.Percent(0.45f),
              height = StretchDimension.Auto,
           ),
        ),
            listOf(),
        )
    val node2 = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	flexDirection = FlexDirection.Column,
        	margin = io.github.orioncraftmc.sprawlkt.geometry.Rect(
                top = StretchDimension.Points(5.0f),
                bottom = StretchDimension.Points(5.0f),
                start = StretchDimension.Points(5.0f),
                end = StretchDimension.Points(5.0f),
            ),
        	padding = io.github.orioncraftmc.sprawlkt.geometry.Rect(
                top = StretchDimension.Percent(0.03f),
                bottom = StretchDimension.Percent(0.03f),
                start = StretchDimension.Percent(0.03f),
                end = StretchDimension.Percent(0.03f),
            ),
        	size = io.github.orioncraftmc.sprawlkt.geometry.Size(
              width = StretchDimension.Percent(0.5f),
              height = StretchDimension.Auto,
           ),
        ),
            listOf(node1),
        )
    val node3 = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	flexDirection = FlexDirection.Column,
        	margin = io.github.orioncraftmc.sprawlkt.geometry.Rect(
                top = StretchDimension.Points(5.0f),
                bottom = StretchDimension.Points(5.0f),
                start = StretchDimension.Points(5.0f),
                end = StretchDimension.Points(5.0f),
            ),
        	padding = io.github.orioncraftmc.sprawlkt.geometry.Rect(
                top = StretchDimension.Points(3.0f),
                bottom = StretchDimension.Points(3.0f),
                start = StretchDimension.Points(3.0f),
                end = StretchDimension.Points(3.0f),
            ),
        	flexGrow = 1.0f,
        	flexBasis = StretchDimension.Percent(0.1f),
        	minSize = io.github.orioncraftmc.sprawlkt.geometry.Size(
              width = StretchDimension.Percent(0.6f),
              height = StretchDimension.Auto,
           ),
        ),
            listOf(node2),
        )
    val node4 = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	flexGrow = 4.0f,
        	flexBasis = StretchDimension.Percent(0.15f),
        	minSize = io.github.orioncraftmc.sprawlkt.geometry.Size(
              width = StretchDimension.Percent(0.2f),
              height = StretchDimension.Auto,
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
              height = StretchDimension.Points(200.0f),
           ),
        ),
            listOf(node3, node4),
        )
    // {"style":{"flexDirection":"column","size":{"width":{"unit":"Points","value":200},"height":{"unit":"Points","value":200}}},"layout":{"width":200,"height":200,"x":0,"y":0},"children":[{"style":{"flexDirection":"column","flexGrow":1,"flexBasis":{"unit":"Percent","value":0.1},"minSize":{"width":{"unit":"Percent","value":0.6}},"margin":{"start":{"unit":"Points","value":5},"end":{"unit":"Points","value":5},"top":{"unit":"Points","value":5},"bottom":{"unit":"Points","value":5}},"padding":{"start":{"unit":"Points","value":3},"end":{"unit":"Points","value":3},"top":{"unit":"Points","value":3},"bottom":{"unit":"Points","value":3}}},"layout":{"width":190,"height":48,"x":5,"y":5},"children":[{"style":{"flexDirection":"column","size":{"width":{"unit":"Percent","value":0.5}},"margin":{"start":{"unit":"Points","value":5},"end":{"unit":"Points","value":5},"top":{"unit":"Points","value":5},"bottom":{"unit":"Points","value":5}},"padding":{"start":{"unit":"Percent","value":0.03},"end":{"unit":"Percent","value":0.03},"top":{"unit":"Percent","value":0.03},"bottom":{"unit":"Percent","value":0.03}}},"layout":{"width":92,"height":25,"x":8,"y":8},"children":[{"style":{"size":{"width":{"unit":"Percent","value":0.45}},"margin":{"start":{"unit":"Percent","value":0.05},"end":{"unit":"Percent","value":0.05},"top":{"unit":"Percent","value":0.05},"bottom":{"unit":"Percent","value":0.05}},"padding":{"start":{"unit":"Points","value":3},"end":{"unit":"Points","value":3},"top":{"unit":"Points","value":3},"bottom":{"unit":"Points","value":3}}},"layout":{"width":36,"height":6,"x":10,"y":10},"children":[]}]}]},{"style":{"flexGrow":4,"flexBasis":{"unit":"Percent","value":0.15},"minSize":{"width":{"unit":"Percent","value":0.2}}},"layout":{"width":200,"height":142,"x":0,"y":58},"children":[]}]}
    Sprawl.computeLayout(rootNode, Size.Companion.undefinedNumber())
    kotlin.test.assertEquals(200.0f, Sprawl.layout(rootNode).size.width)
    kotlin.test.assertEquals(200.0f, Sprawl.layout(rootNode).size.height)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(rootNode).location.x)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(rootNode).location.y)
    kotlin.test.assertEquals(190.0f, Sprawl.layout(node3).size.width)
    kotlin.test.assertEquals(48.0f, Sprawl.layout(node3).size.height)
    kotlin.test.assertEquals(5.0f, Sprawl.layout(node3).location.x)
    kotlin.test.assertEquals(5.0f, Sprawl.layout(node3).location.y)
    kotlin.test.assertEquals(92.0f, Sprawl.layout(node2).size.width)
    kotlin.test.assertEquals(25.0f, Sprawl.layout(node2).size.height)
    kotlin.test.assertEquals(8.0f, Sprawl.layout(node2).location.x)
    kotlin.test.assertEquals(8.0f, Sprawl.layout(node2).location.y)
    kotlin.test.assertEquals(36.0f, Sprawl.layout(node1).size.width)
    kotlin.test.assertEquals(6.0f, Sprawl.layout(node1).size.height)
    kotlin.test.assertEquals(10.0f, Sprawl.layout(node1).location.x)
    kotlin.test.assertEquals(10.0f, Sprawl.layout(node1).location.y)
    kotlin.test.assertEquals(200.0f, Sprawl.layout(node4).size.width)
    kotlin.test.assertEquals(142.0f, Sprawl.layout(node4).size.height)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node4).location.x)
    kotlin.test.assertEquals(58.0f, Sprawl.layout(node4).location.y)
  }
}
