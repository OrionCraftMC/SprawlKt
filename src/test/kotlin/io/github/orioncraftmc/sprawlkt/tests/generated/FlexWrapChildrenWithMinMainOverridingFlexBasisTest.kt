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

public class FlexWrapChildrenWithMinMainOverridingFlexBasisTest {
  @Test
  public fun flex_wrap_children_with_min_main_overriding_flex_basis(): Unit {
    val node1 = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	flexBasis = StretchDimension.Points(50.0f),
        	size = io.github.orioncraftmc.sprawlkt.geometry.Size(
              width = StretchDimension.Auto,
              height = StretchDimension.Points(50.0f),
           ),
        	minSize = io.github.orioncraftmc.sprawlkt.geometry.Size(
              width = StretchDimension.Points(55.0f),
              height = StretchDimension.Auto,
           ),
        ),
            listOf(),
        )
    val node2 = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	flexBasis = StretchDimension.Points(50.0f),
        	size = io.github.orioncraftmc.sprawlkt.geometry.Size(
              width = StretchDimension.Auto,
              height = StretchDimension.Points(50.0f),
           ),
        	minSize = io.github.orioncraftmc.sprawlkt.geometry.Size(
              width = StretchDimension.Points(55.0f),
              height = StretchDimension.Auto,
           ),
        ),
            listOf(),
        )
    val rootNode = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	flexWrap = FlexWrap.Wrap,
        	size = io.github.orioncraftmc.sprawlkt.geometry.Size(
              width = StretchDimension.Points(100.0f),
              height = StretchDimension.Auto,
           ),
        ),
            listOf(node1, node2),
        )
    // {"style":{"flexDirection":"row","flexWrap":"wrap","size":{"width":{"unit":"Points","value":100}}},"layout":{"width":100,"height":100,"x":0,"y":0},"children":[{"style":{"flexBasis":{"unit":"Points","value":50},"size":{"height":{"unit":"Points","value":50}},"minSize":{"width":{"unit":"Points","value":55}}},"layout":{"width":55,"height":50,"x":0,"y":0},"children":[]},{"style":{"flexBasis":{"unit":"Points","value":50},"size":{"height":{"unit":"Points","value":50}},"minSize":{"width":{"unit":"Points","value":55}}},"layout":{"width":55,"height":50,"x":0,"y":50},"children":[]}]}
    Sprawl.computeLayout(rootNode, Size.Companion.undefinedNumber())
    kotlin.test.assertEquals(100.0f, Sprawl.layout(rootNode).size.width)
    kotlin.test.assertEquals(100.0f, Sprawl.layout(rootNode).size.height)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(rootNode).location.x)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(rootNode).location.y)
    kotlin.test.assertEquals(55.0f, Sprawl.layout(node1).size.width)
    kotlin.test.assertEquals(50.0f, Sprawl.layout(node1).size.height)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node1).location.x)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node1).location.y)
    kotlin.test.assertEquals(55.0f, Sprawl.layout(node2).size.width)
    kotlin.test.assertEquals(50.0f, Sprawl.layout(node2).size.height)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(node2).location.x)
    kotlin.test.assertEquals(50.0f, Sprawl.layout(node2).location.y)
  }
}
