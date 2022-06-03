package io.github.orioncraftmc.sprawlkt.tests

import io.github.orioncraftmc.sprawlkt.geometry.Rect
import io.github.orioncraftmc.sprawlkt.geometry.Size
import io.github.orioncraftmc.sprawlkt.node.MeasureFunc
import io.github.orioncraftmc.sprawlkt.node.Sprawl
import io.github.orioncraftmc.sprawlkt.style.Style
import io.github.orioncraftmc.sprawlkt.style.enums.AlignItems
import io.github.orioncraftmc.sprawlkt.style.enums.PositionType
import io.github.orioncraftmc.sprawlkt.style.enums.StretchDimension
import java.util.concurrent.atomic.AtomicInteger
import kotlin.test.Test
import kotlin.test.assertEquals


class MeasureTests {
    @Test
    fun measureRoot() {

        val node = Sprawl.newLeaf(Style()) { constraint ->
            Size(
                width = constraint.width.orElse(100.0f),
                height = constraint.height.orElse(100.0f),
            )
        }


        Sprawl.computeLayout(node, Size.undefinedNumber())

        assertEquals(100.0f, Sprawl.layout(node).size.width)
        assertEquals(100.0f, Sprawl.layout(node).size.height)
    }


    @Test
    fun measureChild() {
        val child = Sprawl.newLeaf(Style()) { constraint ->
            Size(
                width = constraint.width.orElse(100.0f),
                height = constraint.height.orElse(100.0f),
            )
        }


        val node = Sprawl.newNode(Style(), listOf(child))
        Sprawl.computeLayout(node, Size.undefinedNumber())
        assertEquals(100.0f, Sprawl.layout(node).size.width)
        assertEquals(100.0f, Sprawl.layout(node).size.height)

        assertEquals(100.0f, Sprawl.layout(child).size.width)
        assertEquals(100.0f, Sprawl.layout(child).size.height)
    }

    @Test
    fun measureChildConstraint() {
        val child = Sprawl.newLeaf(
            Style(),
        ) { constraint ->
            Size(
                width = constraint.width.orElse(100.0f),
                height = constraint.height.orElse(100.0f),
            )
        }


        val node = Sprawl.newNode(
            Style(
                size = Size(
                    width = StretchDimension.Points(50.0f), height = StretchDimension.Undefined
                )
            ),
            listOf(child),
        )


        Sprawl.computeLayout(node, Size.undefinedNumber())

        assertEquals(50.0f, Sprawl.layout(node).size.width)
        assertEquals(100.0f, Sprawl.layout(node).size.height)

        assertEquals(50.0f, Sprawl.layout(child).size.width)
        assertEquals(100.0f, Sprawl.layout(child).size.height)
    }

    @Test
    fun measureChildConstraintPaddingParent() {

        val child = Sprawl.newLeaf(
            Style(),
            (MeasureFunc { constraint ->
                Size(
                    width = constraint.width.orElse(100.0f),
                    height = constraint.height.orElse(100.0f),
                )
            }),
        )


        val node = Sprawl.newNode(
            Style(
                size = Size(
                    width = StretchDimension.Points(50.0f), height = StretchDimension.Undefined
                ),
                padding = Rect(
                    start = StretchDimension.Points(10.0f),
                    end = StretchDimension.Points(10.0f),
                    top = StretchDimension.Points(10.0f),
                    bottom = StretchDimension.Points(10.0f),
                )

            ),
            listOf(child),
        )

        Sprawl.computeLayout(node, Size.undefinedNumber())

        assertEquals(50.0f, Sprawl.layout(node).size.width)
        assertEquals(120.0f, Sprawl.layout(node).size.height)

        assertEquals(30.0f, Sprawl.layout(child).size.width)
        assertEquals(100.0f, Sprawl.layout(child).size.height)
    }

    @Test
    fun measureChildWithFlexGrow() {

        val child0 = Sprawl.newNode(
            Style(
                size = Size(
                    width = StretchDimension.Points(50.0f),
                    height = StretchDimension.Points(50.0f),
                ),

                )
        )


        val child1 = Sprawl.newLeaf(
            Style(flexGrow = 1.0f),
            (MeasureFunc { constraint ->
                Size(
                    width = constraint.width.orElse(10.0f),
                    height = constraint.height.orElse(50.0f),
                )
            }),
        )


        val node = Sprawl.newNode(
            Style(
                size = Size(
                    width = StretchDimension.Points(100.0f), height = StretchDimension.Undefined
                )

            ), listOf(child0, child1)
        )


        Sprawl.computeLayout(node, Size.undefinedNumber())

        assertEquals(50.0f, Sprawl.layout(child1).size.width)
        assertEquals(50.0f, Sprawl.layout(child1).size.height)
    }

    @Test
    fun measureChildWithFlexShrink() {

        val child0 = Sprawl.newNode(
            Style(
                size = Size(
                    width = StretchDimension.Points(50.0f),
                    height = StretchDimension.Points(50.0f),
                ),
                flexShrink = 0.0f,

                )
        )


        val child1 = Sprawl.newLeaf(
            Style(),
            (MeasureFunc { constraint ->
                Size(
                    width = constraint.width.orElse(100.0f),
                    height = constraint.height.orElse(50.0f),
                )
            }),
        )


        val node = Sprawl.newNode(
            Style(
                size = Size(
                    width = StretchDimension.Points(100.0f), height = StretchDimension.Undefined
                )

            ), listOf(child0, child1)
        )


        Sprawl.computeLayout(node, Size.undefinedNumber())

        assertEquals(50.0f, Sprawl.layout(child1).size.width)
        assertEquals(50.0f, Sprawl.layout(child1).size.height)
    }

    @Test
    fun remeasureChildAfterGrowing() {

        val child0 = Sprawl.newNode(
            Style(
                size = Size(
                    width = StretchDimension.Points(50.0f),
                    height = StretchDimension.Points(50.0f),
                ),

                )
        )


        val child1 = Sprawl.newLeaf(
            Style(flexGrow = 1.0f),
            (MeasureFunc { constraint ->
                val width = constraint.width.orElse(10.0f)
                val height = constraint.height.orElse(width * 2.0f)
                Size(width, height)
            }),
        )


        val node = Sprawl.newNode(
            Style(
                size = Size(
                    width = StretchDimension.Points(100.0f), height = StretchDimension.Undefined
                ),
                alignItems = AlignItems.FlexStart,

                ), listOf(child0, child1)
        )


        Sprawl.computeLayout(node, Size.undefinedNumber())

        assertEquals(50.0f, Sprawl.layout(child1).size.width)
        assertEquals(100.0f, Sprawl.layout(child1).size.height)
    }

    @Test
    fun remeasureChildAfterShrinking() {

        val child0 = Sprawl.newNode(
            Style(
                size = Size(
                    width = StretchDimension.Points(50.0f),
                    height = StretchDimension.Points(50.0f),
                ),
                flexShrink = 0.0f,

                )
        )


        val child1 = Sprawl.newLeaf(
            Style(),
            (MeasureFunc { constraint ->
                val width = constraint.width.orElse(100.0f)
                val height = constraint.height.orElse(width * 2.0f)
                Size(width, height)
            }),
        )


        val node = Sprawl.newNode(
            Style(
                size = Size(
                    width = StretchDimension.Points(100.0f), height = StretchDimension.Undefined
                ),
                alignItems = AlignItems.FlexStart,

                ), listOf(child0, child1)
        )


        Sprawl.computeLayout(node, Size.undefinedNumber())

        assertEquals(50.0f, Sprawl.layout(child1).size.width)
        assertEquals(100.0f, Sprawl.layout(child1).size.height)
    }

    @Test
    fun remeasureChildAfterStretching() {


        val child = Sprawl.newLeaf(
            Style(),
            (MeasureFunc { constraint ->
                val height = constraint.height.orElse(50.0f)
                val width = constraint.width.orElse(height)
                Size(width, height)
            }),
        )


        val node = Sprawl.newNode(
            Style(
                size = Size(
                    width = StretchDimension.Points(100.0f),
                    height = StretchDimension.Points(100.0f),
                ),
            ),
            listOf(child),
        )


        Sprawl.computeLayout(node, Size.undefinedNumber())

        assertEquals(100.0f, Sprawl.layout(child).size.width)
        assertEquals(100.0f, Sprawl.layout(child).size.height)
    }

    @Test
    fun widthOverridesMeasure() {

        val child = Sprawl.newLeaf(
            Style(
                size = Size(
                    width = StretchDimension.Points(50.0f), height = StretchDimension.Undefined
                )
            ),
            (MeasureFunc { constraint ->
                Size(
                    width = constraint.width.orElse(100.0f),
                    height = constraint.height.orElse(100.0f),
                )
            }),
        )


        val node = Sprawl.newNode(Style(), listOf(child))
        Sprawl.computeLayout(node, Size.undefinedNumber())

        assertEquals(50.0f, Sprawl.layout(child).size.width)
        assertEquals(100.0f, Sprawl.layout(child).size.height)
    }


    @Test
    fun heightOverridesMeasure() {

        val child = Sprawl.newLeaf(
            Style(
                size = Size(
                    width = StretchDimension.Undefined, height = StretchDimension.Points(50.0f)
                )

            ),
            (MeasureFunc { constraint ->
                Size(
                    width = constraint.width.orElse(100.0f),
                    height = constraint.height.orElse(100.0f),
                )
            }),
        )


        val node = Sprawl.newNode(Style(), listOf(child))
        Sprawl.computeLayout(node, Size.undefinedNumber())

        assertEquals(100.0f, Sprawl.layout(child).size.width)
        assertEquals(50.0f, Sprawl.layout(child).size.height)
    }

    @Test
    fun flexBasisOverridesMeasure() {

        val child0 = Sprawl.newNode(
            Style(
                flexBasis = StretchDimension.Points(50.0f),
                flexGrow = 1.0f,
            )
        )


        val child1 = Sprawl.newLeaf(
            Style(
                flexBasis = StretchDimension.Points(50.0f),
                flexGrow = 1.0f,
            ),
            (MeasureFunc { constraint ->
                Size(
                    width = constraint.width.orElse(100.0f),
                    height = constraint.height.orElse(100.0f),
                )
            }),
        )


        val node = Sprawl.newNode(
            Style(
                size = Size(
                    width = StretchDimension.Points(200.0f),
                    height = StretchDimension.Points(100.0f),
                )

            ), listOf(child0, child1)
        )


        Sprawl.computeLayout(node, Size.undefinedNumber())

        assertEquals(100.0f, Sprawl.layout(child0).size.width)
        assertEquals(100.0f, Sprawl.layout(child0).size.height)
        assertEquals(100.0f, Sprawl.layout(child1).size.width)
        assertEquals(100.0f, Sprawl.layout(child1).size.height)
    }

    @Test
    fun stretchOverridesMeasure() {

        val child = Sprawl.newLeaf(
            Style(),
            (MeasureFunc { constraint ->
                Size(
                    width = constraint.width.orElse(50.0f),
                    height = constraint.height.orElse(50.0f),
                )
            }),
        )


        val node = Sprawl.newNode(
            Style(
                size = Size(
                    width = StretchDimension.Points(100.0f),
                    height = StretchDimension.Points(100.0f),
                ),
            ),
            listOf(child),
        )


        Sprawl.computeLayout(node, Size.undefinedNumber())

        assertEquals(50.0f, Sprawl.layout(child).size.width)
        assertEquals(100.0f, Sprawl.layout(child).size.height)
    }

    @Test
    fun measureAbsoluteChild() {
        val child = Sprawl.newLeaf(
            Style(positionType = PositionType.Absolute),
            (MeasureFunc { constraint ->
                Size(
                    width = constraint.width.orElse(50.0f),
                    height = constraint.height.orElse(50.0f),
                )
            }),
        )


        val node = Sprawl.newNode(
            Style(
                size = Size(
                    width = StretchDimension.Points(100.0f),
                    height = StretchDimension.Points(100.0f),
                ),
            ),
            listOf(child),
        )


        Sprawl.computeLayout(node, Size.undefinedNumber())

        assertEquals(50.0f, Sprawl.layout(child).size.width)
        assertEquals(50.0f, Sprawl.layout(child).size.height)
    }

    @Test
    fun ignoreInvalidMeasure() {

        val child = Sprawl.newLeaf(
            Style(flexGrow = 1.0f),
        ) { Size(width = 200.0f, height = 200.0f) }


        val node = Sprawl.newNode(
            Style(
                size = Size(
                    width = StretchDimension.Points(100.0f),
                    height = StretchDimension.Points(100.0f),
                )
            ),
            listOf(child),
        )


        Sprawl.computeLayout(node, Size.undefinedNumber())

        assertEquals(100.0f, Sprawl.layout(child).size.width)
        assertEquals(100.0f, Sprawl.layout(child).size.height)
    }

    @Test
    fun onlyMeasureOnce() {
        val numMeasures = AtomicInteger(0)

        val grandchild = Sprawl.newLeaf(
            Style(),
            (MeasureFunc { constraint ->
                numMeasures.getAndIncrement()
                Size(
                    width = constraint.width.orElse(50.0f),
                    height = constraint.height.orElse(50.0f),
                )
            }),
        )


        val child = Sprawl.newNode(Style(), listOf(grandchild))

        val node = Sprawl.newNode(Style(), listOf(child))
        Sprawl.computeLayout(node, Size.undefinedNumber())

        assertEquals(2, numMeasures.get())
    }
}
