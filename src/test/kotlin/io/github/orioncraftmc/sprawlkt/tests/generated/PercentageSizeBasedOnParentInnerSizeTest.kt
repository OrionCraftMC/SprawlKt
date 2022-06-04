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

public class PercentageSizeBasedOnParentInnerSizeTest {
  @Test
  public fun percentage_size_based_on_parent_inner_size(): Unit {
    val node1 = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	size = io.github.orioncraftmc.sprawlkt.geometry.Size(
              width = StretchDimension.Percent(0.5f),
              height = StretchDimension.Percent(0.5f),
           ),
        ),
            listOf(),
        )
    val rootNode = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	flexDirection = FlexDirection.Column,
        	padding = io.github.orioncraftmc.sprawlkt.geometry.Rect(
                top = StretchDimension.Points(20.0f),
                bottom = StretchDimension.Points(20.0f),
                start = StretchDimension.Points(20.0f),
                end = StretchDimension.Points(20.0f),
            ),
        	size = io.github.orioncraftmc.sprawlkt.geometry.Size(
              width = StretchDimension.Points(200.0f),
              height = StretchDimension.Points(400.0f),
           ),
        ),
            listOf(node1),
        )
    // {"style":{"flexDirection":"column","size":{"width":{"unit":"Points","value":200},"height":{"unit":"Points","value":400}},"padding":{"start":{"unit":"Points","value":20},"end":{"unit":"Points","value":20},"top":{"unit":"Points","value":20},"bottom":{"unit":"Points","value":20}}},"layout":{"width":200,"height":400,"x":0,"y":0},"children":[{"style":{"size":{"width":{"unit":"Percent","value":0.5},"height":{"unit":"Percent","value":0.5}}},"layout":{"width":80,"height":180,"x":20,"y":20},"children":[]}]}
    Sprawl.computeLayout(rootNode, Size.Companion.undefinedNumber())
    kotlin.test.assertEquals(200.0f, Sprawl.layout(rootNode).size.width)
    kotlin.test.assertEquals(400.0f, Sprawl.layout(rootNode).size.height)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(rootNode).location.x)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(rootNode).location.y)
    kotlin.test.assertEquals(80.0f, Sprawl.layout(node1).size.width)
    kotlin.test.assertEquals(180.0f, Sprawl.layout(node1).size.height)
    kotlin.test.assertEquals(20.0f, Sprawl.layout(node1).location.x)
    kotlin.test.assertEquals(20.0f, Sprawl.layout(node1).location.y)
  }
}
