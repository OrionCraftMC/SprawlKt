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

public class PercentageWidthHeightUndefinedParentSizeTest {
  @Test
  public fun percentage_width_height_undefined_parent_size(): Unit {
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
        ),
            listOf(node1),
        )
    // {"style":{"flexDirection":"column"},"layout":{"width":0,"height":0,"x":0,"y":0},"children":[{"style":{"size":{"width":{"unit":"Percent","value":0.5},"height":{"unit":"Percent","value":0.5}}},"layout":{"width":0,"height":0,"x":0,"y":0},"children":[]}]}
    Sprawl.computeLayout(rootNode, Size.Companion.undefinedNumber())
    kotlin.test.assertEquals(0.0f, Sprawl.layout(rootNode).size.width)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(rootNode).size.height)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(rootNode).location.x)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(rootNode).location.y)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node1).size.width)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node1).size.height)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node1).location.x)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node1).location.y)
  }
}
