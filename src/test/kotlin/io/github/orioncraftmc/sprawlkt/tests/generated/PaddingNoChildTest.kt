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

public class PaddingNoChildTest {
  @Test
  public fun padding_no_child(): Unit {
    val rootNode = newNode(
            io.github.orioncraftmc.sprawlkt.style.Style
        (
        	padding = io.github.orioncraftmc.sprawlkt.geometry.Rect(
                top = StretchDimension.Points(10.0f),
                bottom = StretchDimension.Points(10.0f),
                start = StretchDimension.Points(10.0f),
                end = StretchDimension.Points(10.0f),
            ),
        ),
            listOf(),
        )
    // {"style":{"padding":{"start":{"unit":"Points","value":10},"end":{"unit":"Points","value":10},"top":{"unit":"Points","value":10},"bottom":{"unit":"Points","value":10}}},"layout":{"width":20,"height":20,"x":0,"y":0},"children":[]}
    Sprawl.computeLayout(rootNode, Size.Companion.undefinedNumber())
    kotlin.test.assertEquals(20.0f, Sprawl.layout(rootNode).size.width)
    kotlin.test.assertEquals(20.0f, Sprawl.layout(rootNode).size.height)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(rootNode).location.x)
    kotlin.test.assertEquals(0.0f, Sprawl.layout(rootNode).location.y)
  }
}
