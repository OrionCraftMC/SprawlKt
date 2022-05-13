package io.github.orioncraftmc.stretchkt.tests

import io.github.orioncraftmc.stretchkt.geometry.Rect
import io.github.orioncraftmc.stretchkt.geometry.Size
import io.github.orioncraftmc.stretchkt.node.MeasureFunc
import io.github.orioncraftmc.stretchkt.node.Stretch
import io.github.orioncraftmc.stretchkt.style.Style
import io.github.orioncraftmc.stretchkt.style.enums.AlignItems
import io.github.orioncraftmc.stretchkt.style.enums.PositionType
import io.github.orioncraftmc.stretchkt.style.enums.StretchDimension
import java.util.concurrent.atomic.AtomicInteger
import kotlin.test.Test
import kotlin.test.assertEquals


class MeasureTests {
    @Test
    fun measureRoot() {

        val node = Stretch.newLeaf(Style()) { constraint ->
            Size(
                width = constraint.width.orElse(100.0f),
                height = constraint.height.orElse(100.0f),
            )
        }


        Stretch.computeLayout(node, Size.undefinedNumber())

        assertEquals(100.0f, Stretch.layout(node).size.width)
        assertEquals(100.0f, Stretch.layout(node).size.height)
    }


    @Test
    fun measureChild() {
        val child = Stretch.newLeaf(Style()) { constraint ->
            Size(
                width = constraint.width.orElse(100.0f),
                height = constraint.height.orElse(100.0f),
            )
        }


        val node = Stretch.newNode(Style(), listOf(child))
        Stretch.computeLayout(node, Size.undefinedNumber())
        assertEquals(100.0f, Stretch.layout(node).size.width)
        assertEquals(100.0f, Stretch.layout(node).size.height)

        assertEquals(100.0f, Stretch.layout(child).size.width)
        assertEquals(100.0f, Stretch.layout(child).size.height)
    }

    @Test
    fun measureChildConstraint() {
        val child = Stretch.newLeaf(
            Style(),
        ) { constraint ->
            Size(
                width = constraint.width.orElse(100.0f),
                height = constraint.height.orElse(100.0f),
            )
        }


        val node = Stretch.newNode(
            Style(
                size = Size(
                    width = StretchDimension.Points(50.0f), height = StretchDimension.Undefined
                )
            ),
            listOf(child),
        )


        Stretch.computeLayout(node, Size.undefinedNumber())

        assertEquals(50.0f, Stretch.layout(node).size.width)
        assertEquals(100.0f, Stretch.layout(node).size.height)

        assertEquals(50.0f, Stretch.layout(child).size.width)
        assertEquals(100.0f, Stretch.layout(child).size.height)
    }

    @Test
    fun measureChildConstraintPaddingParent() {

        val child = Stretch.newLeaf(
            Style(),
            (MeasureFunc { constraint ->
                Size(
                    width = constraint.width.orElse(100.0f),
                    height = constraint.height.orElse(100.0f),
                )
            }),
        )


        val node = Stretch.newNode(
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

        Stretch.computeLayout(node, Size.undefinedNumber())

        assertEquals(50.0f, Stretch.layout(node).size.width)
        assertEquals(120.0f, Stretch.layout(node).size.height)

        assertEquals(30.0f, Stretch.layout(child).size.width)
        assertEquals(100.0f, Stretch.layout(child).size.height)
    }

    @Test
    fun measureChildWithFlexGrow() {

        val child0 = Stretch.newNode(
            Style(
                size = Size(
                    width = StretchDimension.Points(50.0f),
                    height = StretchDimension.Points(50.0f),
                ),

                )
        )


        val child1 = Stretch.newLeaf(
            Style(flexGrow = 1.0f),
            (MeasureFunc { constraint ->
                Size(
                    width = constraint.width.orElse(10.0f),
                    height = constraint.height.orElse(50.0f),
                )
            }),
        )


        val node = Stretch.newNode(
            Style(
                size = Size(
                    width = StretchDimension.Points(100.0f), height = StretchDimension.Undefined
                )

            ), listOf(child0, child1)
        )


        Stretch.computeLayout(node, Size.undefinedNumber())

        assertEquals(50.0f, Stretch.layout(child1).size.width)
        assertEquals(50.0f, Stretch.layout(child1).size.height)
    }

    @Test
    fun measureChildWithFlexShrink() {

        val child0 = Stretch.newNode(
            Style(
                size = Size(
                    width = StretchDimension.Points(50.0f),
                    height = StretchDimension.Points(50.0f),
                ),
                flexShrink = 0.0f,

                )
        )


        val child1 = Stretch.newLeaf(
            Style(),
            (MeasureFunc { constraint ->
                Size(
                    width = constraint.width.orElse(100.0f),
                    height = constraint.height.orElse(50.0f),
                )
            }),
        )


        val node = Stretch.newNode(
            Style(
                size = Size(
                    width = StretchDimension.Points(100.0f), height = StretchDimension.Undefined
                )

            ), listOf(child0, child1)
        )


        Stretch.computeLayout(node, Size.undefinedNumber())

        assertEquals(50.0f, Stretch.layout(child1).size.width)
        assertEquals(50.0f, Stretch.layout(child1).size.height)
    }

    @Test
    fun remeasureChildAfterGrowing() {

        val child0 = Stretch.newNode(
            Style(
                size = Size(
                    width = StretchDimension.Points(50.0f),
                    height = StretchDimension.Points(50.0f),
                ),

                )
        )


        val child1 = Stretch.newLeaf(
            Style(flexGrow = 1.0f),
            (MeasureFunc { constraint ->
                val width = constraint.width.orElse(10.0f)
                val height = constraint.height.orElse(width * 2.0f)
                Size(width, height)
            }),
        )


        val node = Stretch.newNode(
            Style(
                size = Size(
                    width = StretchDimension.Points(100.0f), height = StretchDimension.Undefined
                ),
                alignItems = AlignItems.FlexStart,

                ), listOf(child0, child1)
        )


        Stretch.computeLayout(node, Size.undefinedNumber())

        assertEquals(50.0f, Stretch.layout(child1).size.width)
        assertEquals(100.0f, Stretch.layout(child1).size.height)
    }

    @Test
    fun remeasureChildAfterShrinking() {

        val child0 = Stretch.newNode(
            Style(
                size = Size(
                    width = StretchDimension.Points(50.0f),
                    height = StretchDimension.Points(50.0f),
                ),
                flexShrink = 0.0f,

                )
        )


        val child1 = Stretch.newLeaf(
            Style(),
            (MeasureFunc { constraint ->
                val width = constraint.width.orElse(100.0f)
                val height = constraint.height.orElse(width * 2.0f)
                Size(width, height)
            }),
        )


        val node = Stretch.newNode(
            Style(
                size = Size(
                    width = StretchDimension.Points(100.0f), height = StretchDimension.Undefined
                ),
                alignItems = AlignItems.FlexStart,

                ), listOf(child0, child1)
        )


        Stretch.computeLayout(node, Size.undefinedNumber())

        assertEquals(50.0f, Stretch.layout(child1).size.width)
        assertEquals(100.0f, Stretch.layout(child1).size.height)
    }

    @Test
    fun remeasureChildAfterStretching() {


        val child = Stretch.newLeaf(
            Style(),
            (MeasureFunc { constraint ->
                val height = constraint.height.orElse(50.0f)
                val width = constraint.width.orElse(height)
                Size(width, height)
            }),
        )


        val node = Stretch.newNode(
            Style(
                size = Size(
                    width = StretchDimension.Points(100.0f),
                    height = StretchDimension.Points(100.0f),
                ),
            ),
            listOf(child),
        )


        Stretch.computeLayout(node, Size.undefinedNumber())

        assertEquals(100.0f, Stretch.layout(child).size.width)
        assertEquals(100.0f, Stretch.layout(child).size.height)
    }

    @Test
    fun widthOverridesMeasure() {

        val child = Stretch.newLeaf(
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


        val node = Stretch.newNode(Style(), listOf(child))
        Stretch.computeLayout(node, Size.undefinedNumber())

        assertEquals(50.0f, Stretch.layout(child).size.width)
        assertEquals(100.0f, Stretch.layout(child).size.height)
    }


    @Test
    fun heightOverridesMeasure() {

        val child = Stretch.newLeaf(
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


        val node = Stretch.newNode(Style(), listOf(child))
        Stretch.computeLayout(node, Size.undefinedNumber())

        assertEquals(100.0f, Stretch.layout(child).size.width)
        assertEquals(50.0f, Stretch.layout(child).size.height)
    }

    @Test
    fun flexBasisOverridesMeasure() {

        val child0 = Stretch.newNode(
            Style(
                flexBasis = StretchDimension.Points(50.0f),
                flexGrow = 1.0f,
            )
        )


        val child1 = Stretch.newLeaf(
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


        val node = Stretch.newNode(
            Style(
                size = Size(
                    width = StretchDimension.Points(200.0f),
                    height = StretchDimension.Points(100.0f),
                )

            ), listOf(child0, child1)
        )


        Stretch.computeLayout(node, Size.undefinedNumber())

        assertEquals(100.0f, Stretch.layout(child0).size.width)
        assertEquals(100.0f, Stretch.layout(child0).size.height)
        assertEquals(100.0f, Stretch.layout(child1).size.width)
        assertEquals(100.0f, Stretch.layout(child1).size.height)
    }

    @Test
    fun stretchOverridesMeasure() {

        val child = Stretch.newLeaf(
            Style(),
            (MeasureFunc { constraint ->
                Size(
                    width = constraint.width.orElse(50.0f),
                    height = constraint.height.orElse(50.0f),
                )
            }),
        )


        val node = Stretch.newNode(
            Style(
                size = Size(
                    width = StretchDimension.Points(100.0f),
                    height = StretchDimension.Points(100.0f),
                ),
            ),
            listOf(child),
        )


        Stretch.computeLayout(node, Size.undefinedNumber())

        assertEquals(50.0f, Stretch.layout(child).size.width)
        assertEquals(100.0f, Stretch.layout(child).size.height)
    }

    @Test
    fun measureAbsoluteChild() {
        val child = Stretch.newLeaf(
            Style(positionType = PositionType.Absolute),
            (MeasureFunc { constraint ->
                Size(
                    width = constraint.width.orElse(50.0f),
                    height = constraint.height.orElse(50.0f),
                )
            }),
        )


        val node = Stretch.newNode(
            Style(
                size = Size(
                    width = StretchDimension.Points(100.0f),
                    height = StretchDimension.Points(100.0f),
                ),
            ),
            listOf(child),
        )


        Stretch.computeLayout(node, Size.undefinedNumber())

        assertEquals(50.0f, Stretch.layout(child).size.width)
        assertEquals(50.0f, Stretch.layout(child).size.height)
    }

    @Test
    fun ignoreInvalidMeasure() {

        val child = Stretch.newLeaf(
            Style(flexGrow = 1.0f),
        ) { Size(width = 200.0f, height = 200.0f) }


        val node = Stretch.newNode(
            Style(
                size = Size(
                    width = StretchDimension.Points(100.0f),
                    height = StretchDimension.Points(100.0f),
                )
            ),
            listOf(child),
        )


        Stretch.computeLayout(node, Size.undefinedNumber())

        assertEquals(100.0f, Stretch.layout(child).size.width)
        assertEquals(100.0f, Stretch.layout(child).size.height)
    }

    @Test
    fun onlyMeasureOnce() {
        val numMeasures = AtomicInteger(0)

        val grandchild = Stretch.newLeaf(
            Style(),
            (MeasureFunc { constraint ->
                numMeasures.getAndIncrement()
                Size(
                    width = constraint.width.orElse(50.0f),
                    height = constraint.height.orElse(50.0f),
                )
            }),
        )


        val child = Stretch.newNode(Style(), listOf(grandchild))

        val node = Stretch.newNode(Style(), listOf(child))
        Stretch.computeLayout(node, Size.undefinedNumber())

        assertEquals(2, numMeasures.get())
    }
}
