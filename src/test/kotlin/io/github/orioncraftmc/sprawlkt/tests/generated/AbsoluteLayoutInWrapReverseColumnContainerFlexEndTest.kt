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

public class AbsoluteLayoutInWrapReverseColumnContainerFlexEndTest {
  @Test
  public fun absolute_layout_in_wrap_reverse_column_container_flex_end(): Unit {
    val node1 = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	positionType = PositionType.Absolute,
        	alignSelf = AlignSelf.FlexEnd,
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
        	flexWrap = FlexWrap.WrapReverse,
        	size = io.github.orioncraftmc.sprawlkt.geometry.Size(
              width = StretchDimension.Points(100.0f),
              height = StretchDimension.Points(100.0f),
           ),
        ),
            listOf(node1),
        )
    // {"style":{"flexDirection":"column","flexWrap":"wrap-reverse","size":{"width":{"unit":"Points","value":100},"height":{"unit":"Points","value":100}}},"layout":{"width":100,"height":100,"x":0,"y":0},"children":[{"style":{"positionType":"absolute","alignSelf":"flex-end","size":{"width":{"unit":"Points","value":20},"height":{"unit":"Points","value":20}}},"layout":{"width":20,"height":20,"x":0,"y":0},"children":[]}]}
    Sprawl.computeLayout(rootNode, Size.Companion.undefinedNumber())
    kotlin.test.assertEquals(100.0f, Sprawl.layout(rootNode).size.width)
    kotlin.test.assertEquals(100.0f, Sprawl.layout(rootNode).size.height)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(rootNode).location.x)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(rootNode).location.y)
    kotlin.test.assertEquals(20.0f, Sprawl.layout(node1).size.width)
    kotlin.test.assertEquals(20.0f, Sprawl.layout(node1).size.height)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node1).location.x)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node1).location.y)
  }
}
