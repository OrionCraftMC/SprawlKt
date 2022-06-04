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

public class WrappedColumnMaxHeightTest {
  @Test
  public fun wrapped_column_max_height(): Unit {
    val node1 = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	size = io.github.orioncraftmc.sprawlkt.geometry.Size(
              width = StretchDimension.Points(100.0f),
              height = StretchDimension.Points(500.0f),
           ),
        	maxSize = io.github.orioncraftmc.sprawlkt.geometry.Size(
              width = StretchDimension.Auto,
              height = StretchDimension.Points(200.0f),
           ),
        ),
            listOf(),
        )
    val node2 = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	margin = io.github.orioncraftmc.sprawlkt.geometry.Rect(
                top = StretchDimension.Points(20.0f),
                bottom = StretchDimension.Points(20.0f),
                start = StretchDimension.Points(20.0f),
                end = StretchDimension.Points(20.0f),
            ),
        	size = io.github.orioncraftmc.sprawlkt.geometry.Size(
              width = StretchDimension.Points(200.0f),
              height = StretchDimension.Points(200.0f),
           ),
        ),
            listOf(),
        )
    val node3 = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	size = io.github.orioncraftmc.sprawlkt.geometry.Size(
              width = StretchDimension.Points(100.0f),
              height = StretchDimension.Points(100.0f),
           ),
        ),
            listOf(),
        )
    val rootNode = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	flexDirection = FlexDirection.Column,
        	flexWrap = FlexWrap.Wrap,
        	alignItems = AlignItems.Center,
        	alignContent = AlignContent.Center,
        	justifyContent = JustifyContent.Center,
        	size = io.github.orioncraftmc.sprawlkt.geometry.Size(
              width = StretchDimension.Points(700.0f),
              height = StretchDimension.Points(500.0f),
           ),
        ),
            listOf(node1, node2, node3),
        )
    // {"style":{"flexDirection":"column","flexWrap":"wrap","alignItems":"center","alignContent":"center","justifyContent":"center","size":{"width":{"unit":"Points","value":700},"height":{"unit":"Points","value":500}}},"layout":{"width":700,"height":500,"x":0,"y":0},"children":[{"style":{"size":{"width":{"unit":"Points","value":100},"height":{"unit":"Points","value":500}},"maxSize":{"height":{"unit":"Points","value":200}}},"layout":{"width":100,"height":200,"x":250,"y":30},"children":[]},{"style":{"size":{"width":{"unit":"Points","value":200},"height":{"unit":"Points","value":200}},"margin":{"start":{"unit":"Points","value":20},"end":{"unit":"Points","value":20},"top":{"unit":"Points","value":20},"bottom":{"unit":"Points","value":20}}},"layout":{"width":200,"height":200,"x":200,"y":250},"children":[]},{"style":{"size":{"width":{"unit":"Points","value":100},"height":{"unit":"Points","value":100}}},"layout":{"width":100,"height":100,"x":420,"y":200},"children":[]}]}
    Sprawl.computeLayout(rootNode, Size.Companion.undefinedNumber())
    kotlin.test.assertEquals(700.0f, Sprawl.layout(rootNode).size.width)
    kotlin.test.assertEquals(500.0f, Sprawl.layout(rootNode).size.height)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(rootNode).location.x)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(rootNode).location.y)
    kotlin.test.assertEquals(100.0f, Sprawl.layout(node1).size.width)
    kotlin.test.assertEquals(200.0f, Sprawl.layout(node1).size.height)
    kotlin.test.assertEquals(250.0f, Sprawl.layout(node1).location.x)
    kotlin.test.assertEquals(30.0f, Sprawl.layout(node1).location.y)
    kotlin.test.assertEquals(200.0f, Sprawl.layout(node2).size.width)
    kotlin.test.assertEquals(200.0f, Sprawl.layout(node2).size.height)
    kotlin.test.assertEquals(200.0f, Sprawl.layout(node2).location.x)
    kotlin.test.assertEquals(250.0f, Sprawl.layout(node2).location.y)
    kotlin.test.assertEquals(100.0f, Sprawl.layout(node3).size.width)
    kotlin.test.assertEquals(100.0f, Sprawl.layout(node3).size.height)
    kotlin.test.assertEquals(420.0f, Sprawl.layout(node3).location.x)
    kotlin.test.assertEquals(200.0f, Sprawl.layout(node3).location.y)
  }
}
