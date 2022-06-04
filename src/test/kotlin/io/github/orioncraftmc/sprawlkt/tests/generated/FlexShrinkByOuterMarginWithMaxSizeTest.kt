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

public class FlexShrinkByOuterMarginWithMaxSizeTest {
  @Test
  public fun flex_shrink_by_outer_margin_with_max_size(): Unit {
    val node1 = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	margin = io.github.orioncraftmc.sprawlkt.geometry.Rect(
                top = StretchDimension.Points(100.0f),
                bottom = StretchDimension.Undefined,
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
        	size = io.github.orioncraftmc.sprawlkt.geometry.Size(
              width = StretchDimension.Auto,
              height = StretchDimension.Points(100.0f),
           ),
        	maxSize = io.github.orioncraftmc.sprawlkt.geometry.Size(
              width = StretchDimension.Auto,
              height = StretchDimension.Points(80.0f),
           ),
        ),
            listOf(node1),
        )
    // {"style":{"flexDirection":"column","size":{"height":{"unit":"Points","value":100}},"maxSize":{"height":{"unit":"Points","value":80}}},"layout":{"width":20,"height":80,"x":0,"y":0},"children":[{"style":{"size":{"width":{"unit":"Points","value":20},"height":{"unit":"Points","value":20}},"margin":{"top":{"unit":"Points","value":100}}},"layout":{"width":20,"height":0,"x":0,"y":100},"children":[]}]}
    Sprawl.computeLayout(rootNode, Size.Companion.undefinedNumber())
    kotlin.test.assertEquals(20.0f, Sprawl.layout(rootNode).size.width)
    kotlin.test.assertEquals(80.0f, Sprawl.layout(rootNode).size.height)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(rootNode).location.x)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(rootNode).location.y)
    kotlin.test.assertEquals(20.0f, Sprawl.layout(node1).size.width)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node1).size.height)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node1).location.x)
    kotlin.test.assertEquals(100.0f, Sprawl.layout(node1).location.y)
  }
}
