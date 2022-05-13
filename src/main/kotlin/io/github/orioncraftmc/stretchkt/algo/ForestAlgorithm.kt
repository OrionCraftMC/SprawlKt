package io.github.orioncraftmc.stretchkt.algo

import io.github.orioncraftmc.stretchkt.extensions.*
import io.github.orioncraftmc.stretchkt.forest.Forest
import io.github.orioncraftmc.stretchkt.forest.NodeData
import io.github.orioncraftmc.stretchkt.geometry.*
import io.github.orioncraftmc.stretchkt.node.NodeId
import io.github.orioncraftmc.stretchkt.number.StretchNumber
import io.github.orioncraftmc.stretchkt.result.Cache
import io.github.orioncraftmc.stretchkt.result.Layout
import io.github.orioncraftmc.stretchkt.style.enums.*
import kotlin.math.abs

internal fun Forest.computeInternal(
    node: NodeData,
    nodeSize: Size<StretchNumber>,
    parentSize: Size<StretchNumber>,
    performLayout: Boolean,
    mainSize: Boolean,
): ComputeResult {
    node.isDirty = false

    val cache = cache(node, mainSize)
    // First we check if we have a result for the given input

    if (cache != null) {
        if (cache.performLayout || !performLayout) {

            val widthCompatible = if (nodeSize.width is StretchNumber.Defined) {
                abs(nodeSize.width - cache.result.size.width) < Math.ulp(1f)
            } else {
                cache.nodeSize.width.isUndefined
            }

            val heightCompatible = if (nodeSize.height is StretchNumber.Defined) {
                abs(nodeSize.height - cache.result.size.height) < Math.ulp(1f)
            } else {
                cache.nodeSize.height.isUndefined
            }

            if (widthCompatible && heightCompatible) {
                return cache.result.clone()
            }

            if (cache.nodeSize == nodeSize && cache.parentSize == parentSize) {
                return cache.result.clone()
            }

        }
    }


    // Define some general constants we will need for the remainder
    // of the algorithm.

    var dir = node.style.flexDirection
    var isRow = dir.isRow()
    var isColumn = dir.isColumn()
    var isWrapReverse = node.style.flexWrap == FlexWrap.WrapReverse

    var margin = node.style.margin.map { it.resolve(parentSize.width).orElse(0.0f) }
    var padding = node.style.padding.map { it.resolve(parentSize.width).orElse(0.0f) }
    var border = node.style.border.map { it.resolve(parentSize.width).orElse(0.0f) }

    var paddingBorder = Rect(
        start = padding.start + border.start,
        end = padding.end + border.end,
        top = padding.top + border.top,
        bottom = padding.bottom + border.bottom,
    )


    var nodeInnerSize = Size(
        width = nodeSize.width - paddingBorder.horizontal,
        height = nodeSize.height - paddingBorder.vertical,
    )

    var containerSize = Size.zero()
    var innerContainerSize = Size.zero()

    // If this is a leaf node we can skip a lot of this function in some cases
    if (node.children.isEmpty()) {
        if (nodeSize.width.isDefined && nodeSize.height.isDefined) {
            return ComputeResult(size = nodeSize.map { s -> s.orElse(0.0f) })
        }

        if (node.measure != null) {
            val result = ComputeResult(node.measure.measure(nodeSize))
            setCache(node, mainSize, Cache(nodeSize, parentSize, performLayout, result = result.clone()))
            return result
        }

        return ComputeResult(
            size = Size(
                width = (nodeSize.width.orElse(StretchNumber.zero) + paddingBorder.horizontal),
                height = (nodeSize.height.orElse(StretchNumber.zero) + paddingBorder.vertical),
            ),
        )
    }

    // 9.2. Line Length Determination

    // 1. Generate anonymous flex items as described in §4 Flex Items.

    // 2. Determine the available main and cross space for the flex items.
    //    For each dimension, if that dimension of the flex container’s content box
    //    is a definite size, use that; if that dimension of the flex container is
    //    being sized under a min or max-content constraint, the available space in
    //    that dimension is that constraint; otherwise, subtract the flex container’s
    //    margin, border, and padding from the space available to the flex container
    //    in that dimension and use that value. This might result in an infinite value.

    var availableSpace = Size(
        width = nodeSize.width.orElse(parentSize.width - margin.horizontal) - paddingBorder.horizontal,
        height = nodeSize.height.orElse(parentSize.height - margin.vertical) - paddingBorder.vertical,
    )

    var flexItems = node.children
        .asSequence()
        .map { child -> child to child.style }
        .filter { (nodeData, style) -> style.positionType != PositionType.Absolute }
        .filter { (nodeData, style) -> style.display != Display.None }
        .map { (child, childStyle) ->
            FlexItem(
                node = child,
                size = childStyle.size.resolve(nodeInnerSize),
                minSize = childStyle.minSize.resolve(nodeInnerSize),
                maxSize = childStyle.maxSize.resolve(nodeInnerSize),

                position = childStyle.position.zipSize(nodeInnerSize) { p, s -> p.resolve(s) },
                margin = childStyle.margin.map { m -> m.resolve(nodeInnerSize.width).orElse(0.0f) },
                padding = childStyle.padding.map { p -> p.resolve(nodeInnerSize.width).orElse(0.0f) },
                border = childStyle.border.map { b -> b.resolve(nodeInnerSize.width).orElse(0.0f) },

                flexBasis = 0.0f,
                innerFlexBasis = 0.0f,
                violation = 0.0f,
                frozen = false,

                hypotheticalInnerSize = Size.zero(),
                hypotheticalOuterSize = Size.zero(),
                targetSize = Size.zero(),
                outerTargetSize = Size.zero(),

                baseline = 0.0f,

                offsetMain = 0.0f,
                offsetCross = 0.0f,
            )

        }.toList()

    var hasBaselineChild = flexItems
        .any { child ->
            child.node.style.alignSelf(node.style) == AlignSelf.Baseline
        }

    // TODO - this does not follow spec. See commented out code below
    // 3. Determine the flex base size and hypothetical main size of each item:

    for (child in flexItems) {

        var childStyle = child.node.style

        // A. If the item has a definite used flex basis, that’s the flex base size.

        var flexBasis = childStyle.flexBasis.resolve(nodeInnerSize.main(dir))
        if (flexBasis.isDefined) {
            child.flexBasis = flexBasis.orElse(0.0f)
            continue
        }


        // B. If the flex item has an intrinsic aspect ratio,
        //    a used flex basis of content, and a definite cross size,
        //    then the flex base size is calculated from its inner
        //    cross size and the flex item’s intrinsic aspect ratio.

        val ratio = childStyle.aspectRatio
        if (ratio is StretchNumber.Defined) {
            var cross = nodeSize.cross(dir)
            if (cross is StretchNumber.Defined) {
                if (childStyle.flexBasis == StretchDimension.Auto) {
                    child.flexBasis = (cross * ratio).asFloat()
                    continue
                }
            }
        }

        // C. If the used flex basis is content or depends on its available space,
        //    and the flex container is being sized under a min-content or max-content
        //    constraint (e.g. when performing automatic table layout [CSS21]),
        //    size the item under that constraint. The flex base size is the item’s
        //    resulting main size.

        // TODO - Probably need to cover this case in future

        // D. Otherwise, if the used flex basis is content or depends on its
        //    available space, the available main size is infinite, and the flex item’s
        //    inline axis is parallel to the main axis, lay the item out using the rules
        //    for a box in an orthogonal flow [CSS3-WRITING-MODES]. The flex base size
        //    is the item’s max-content main size.

        // TODO - Probably need to cover this case in future

        // E. Otherwise, size the item into the available space using its used flex basis
        //    in place of its main size, treating a value of content as max-content.
        //    If a cross size is needed to determine the main size (e.g. when the
        //    flex item’s main size is in its block axis) and the flex item’s cross size
        //    is auto and not definite, in this calculation use fit-content as the
        //    flex item’s cross size. The flex base size is the item’s resulting main size.

        var width: StretchNumber = if (!child.size.width.isDefined
            && childStyle.alignSelf(node.style) == AlignSelf.Stretch
            && isColumn
        ) {
            StretchNumber.from(availableSpace.width)
        } else {
            child.size.width
        }

        var height: StretchNumber = if (!child.size.height.isDefined
            && childStyle.alignSelf(node.style) == AlignSelf.Stretch
            && isRow
        ) {
            StretchNumber.from(availableSpace.height)
        } else {
            child.size.height
        }

        child.flexBasis = computeInternal(
            child.node,
            Size(
                width = width.maybeMax(child.minSize.width).maybeMin(child.maxSize.width),
                height = height.maybeMax(child.minSize.height).maybeMin(child.maxSize.height),
            ),
            availableSpace.toStretchNumberSize(),
            performLayout = false,
            mainSize = true,
        )
            .size
            .main(dir)
            .maybeMax(child.minSize.main(dir))
            .maybeMin(child.maxSize.main(dir))


    }

    // The hypothetical main size is the item’s flex base size clamped according to its
    // used min and max main sizes (and flooring the content box size at zero).

    for (child in flexItems) {
        child.innerFlexBasis = child.flexBasis - child.padding.main(dir)
        -child.border.main(dir)

        // TODO - not really spec abiding but needs to be done somewhere. probably somewhere else though.
        // The following logic was developed not from the spec but by trial and error looking into how
        // webkit handled various scenarios. Can probably be solved better by passing in
        // min-content max-content constraints from the top
        var minMain =
            computeInternal(
                node = child.node,
                nodeSize = Size.undefinedNumber(),
                parentSize = availableSpace.toStretchNumberSize(),
                performLayout = false,
                mainSize = false
            )
                .size
                .main(dir)
                .maybeMax(child.minSize.main(dir))
                .maybeMin(child.size.main(dir))

        child
            .hypotheticalInnerSize
            .setMain(
                dir,
                child.flexBasis.maybeMax(minMain).maybeMin(child.maxSize.main(dir))
            )

        child
            .hypotheticalOuterSize
            .setMain(
                dir,
                child.hypotheticalInnerSize.main(dir) + child.margin.main(dir)
            )
    }


    // 9.3. Main Size Determination

    // 5. Collect flex items into flex lines:
    //    - If the flex container is single-line, collect all the flex items into
    //      a single flex line.
    //    - Otherwise, starting from the first uncollected item, collect consecutive
    //      items one by one until the first time that the next collected item would
    //      not fit into the flex container’s inner main size (or until a forced break
    //      is encountered, see §10 Fragmenting Flex Layout). If the very first
    //      uncollected item wouldn’t fit, collect just it into the line.
    //
    //      For this step, the size of a flex item is its outer hypothetical main size. (Note: This can be negative.)
    //      Repeat until all flex items have been collected into flex lines
    //
    //      Note that the "collect as many" line will collect zero-sized flex items onto
    //      the end of the previous line even if the last non-zero item exactly "filled up" the line.

    val flexLines = buildList {
        if (node.style.flexWrap == FlexWrap.NoWrap) {
            add(FlexLine(items = flexItems, crossSize = 0.0f, offsetCross = 0.0f))
        } else {
            var flexItemsCopy: List<FlexItem> = ArrayList(flexItems)

            while (flexItemsCopy.isNotEmpty()) {
                var lineLength = 0.0f
                val index = flexItems.withIndex()
                    .find { (idx, child) ->
                        lineLength += child.hypotheticalOuterSize.main(dir)
                        val main = availableSpace.main(dir)

                        if (main.isDefined()) {
                            lineLength > main && idx != 0
                        } else {
                            false
                        }
                    }?.index ?: flexItems.size

                val (items, rest) = flexItemsCopy.splitAtIndex(index)
                add(FlexLine(items, crossSize = 0.0f, offsetCross = 0.0f))
                flexItemsCopy = rest
            }
        }
    }
    // 6. Resolve the flexible lengths of all the flex items to find their used main size.
    //    See §9.7 Resolving Flexible Lengths.
    //
    // 9.7. Resolving Flexible Lengths

    for (line in flexLines) {

        // 1. Determine the used flex factor. Sum the outer hypothetical main sizes of all
        //    items on the line. If the sum is less than the flex container’s inner main size,
        //    use the flex grow factor for the rest of this algorithm; otherwise, use the
        //    flex shrink factor.

        var usedFlexFactor: Float = line.items.map { it.hypotheticalOuterSize.main(dir) }.sum()
        var growing = usedFlexFactor < nodeInnerSize.main(dir).orElse(0.0f)
        var shrinking = !growing


        // 2. Size inflexible items. Freeze, setting its target main size to its hypothetical main size
        //    - Any item that has a flex factor of zero
        //    - If using the flex grow factor: any item that has a flex base size
        //      greater than its hypothetical main size
        //    - If using the flex shrink factor: any item that has a flex base size
        //      smaller than its hypothetical main size

        for (child in line.items) {
            // TODO - This is not found by reading the spec. Maybe this can be done in some other place
            // instead. This was found by trial and error fixing tests to align with webkit output.
            if (nodeInnerSize.main(dir).isUndefined() && isRow) {
                child.targetSize.setMain(
                    dir,
                    computeInternal(
                        node = child.node,
                        nodeSize = Size(
                            width = child.size.width.maybeMax(child.minSize.width).maybeMin(child.maxSize.width),
                            height = child
                                .size
                                .height
                                .maybeMax(child.minSize.height)
                                .maybeMin(child.maxSize.height),
                        ),
                        parentSize = availableSpace.toStretchNumberSize(),
                        performLayout = false,
                        mainSize = false,
                    )
                        .size
                        .main(dir)
                        .maybeMax(child.minSize.main(dir))
                        .maybeMin(child.maxSize.main(dir))
                )
            } else {
                child.targetSize.setMain(dir, child.hypotheticalInnerSize.main(dir))
            }

            // TODO this should really only be set inside the if-statement below but
            // that causes the targetMainSize to never be set for some items

            child.outerTargetSize.setMain(
                dir,
                (child.targetSize.main(dir) + child.margin.main(dir))
            )

            var childStyle = child.node.style
            if ((childStyle.flexGrow == 0.0f && childStyle.flexShrink == 0.0f)
                || (growing && child.flexBasis > child.hypotheticalInnerSize.main(dir))
                || (shrinking && child.flexBasis < child.hypotheticalInnerSize.main(dir))
            ) {
                child.frozen = true
            }
        }

        // 3. Calculate initial free space. Sum the outer sizes of all items on the line,
        //    and subtract this from the flex container’s inner main size. For frozen items,
        //    use their outer target main size; for other items, use their outer flex base size.

        var usedSpace: Float = line.items
            .map { child ->
                (child.margin.main(dir) + (if (child.frozen) child.targetSize.main(dir) else child.flexBasis))
            }.sum()

        var initialFreeSpace = (nodeInnerSize.main(dir) - usedSpace).orElse(0.0f)


        // 4. Loop

        while (true) {
            // a. Check for flexible items. If all the flex items on the line are frozen,
            //    free space has been distributed; exit this loop.

            if (line.items.all { child -> child.frozen }) {
                break
            }

            // b. Calculate the remaining free space as for initial free space, above.
            //    If the sum of the unfrozen flex items’ flex factors is less than one,
            //    multiply the initial free space by this sum. If the magnitude of this
            //    value is less than the magnitude of the remaining free space, use this
            //    as the remaining free space.

            var usedSpace: Float = line.items
                .map { child ->
                    child.margin.main(dir)
                    +(if (child.frozen) child.targetSize.main(dir) else child.flexBasis)
                }
                .sum()


            var unfrozen: List<FlexItem> =
                line.items.filter { child -> !child.frozen }.toList()

            var (sumFlexGrow, sumFlexShrink) = unfrozen
                .fold(Pair(0.0f, 0.0f)) { (flexGrow, flexShrink), item ->
                    var style = item.node.style
                    (flexGrow + style.flexGrow) to (flexShrink + style.flexShrink)
                }

            var freeSpace = if (growing && sumFlexGrow < 1.0f) {
                (initialFreeSpace * sumFlexGrow).maybeMin(nodeInnerSize.main(dir) - usedSpace)
            } else if (shrinking && sumFlexShrink < 1.0f) {
                (initialFreeSpace * sumFlexShrink).maybeMax(nodeInnerSize.main(dir) - usedSpace)
            } else {
                (nodeInnerSize.main(dir) - usedSpace).orElse(0.0f)
            }


            // c. Distribute free space proportional to the flex factors.
            //    - If the remaining free space is zero
            //        Do Nothing
            //    - If using the flex grow factor
            //        Find the ratio of the item’s flex grow factor to the sum of the
            //        flex grow factors of all unfrozen items on the line. Set the item’s
            //        target main size to its flex base size plus a fraction of the remaining
            //        free space proportional to the ratio.
            //    - If using the flex shrink factor
            //        For every unfrozen item on the line, multiply its flex shrink factor by
            //        its inner flex base size, and note this as its scaled flex shrink factor.
            //        Find the ratio of the item’s scaled flex shrink factor to the sum of the
            //        scaled flex shrink factors of all unfrozen items on the line. Set the item’s
            //        target main size to its flex base size minus a fraction of the absolute value
            //        of the remaining free space proportional to the ratio. Note this may result
            //        in a negative inner main size; it will be corrected in the next step.
            //    - Otherwise
            //        Do Nothing

            if (freeSpace.isNormal()) {
                if (growing && sumFlexGrow > 0.0f) {
                    for (child in unfrozen) {
                        child.targetSize.setMain(
                            dir,
                            child.flexBasis
                                    + freeSpace * (child.node.style.flexGrow / sumFlexGrow),
                        )
                    }
                } else if (shrinking && sumFlexShrink > 0.0f) {
                    var sumScaledShrinkFactor: Float = unfrozen
                        .map { child -> child.innerFlexBasis * child.node.style.flexShrink }
                        .sum()

                    if (sumScaledShrinkFactor > 0.0f) {
                        for (child in unfrozen) {
                            var scaledShrinkFactor =
                                child.innerFlexBasis * child.node.style.flexShrink
                            child.targetSize.setMain(
                                dir,
                                child.flexBasis + freeSpace * (scaledShrinkFactor / sumScaledShrinkFactor),
                            )
                        }
                    }
                }
            }


            // d. Fix min/max violations. Clamp each non-frozen item’s target main size by its
            //    used min and max main sizes and floor its content-box size at zero. If the
            //    item’s target main size was made smaller by this, it’s a max violation.
            //    If the item’s target main size was made larger by this, it’s a min violation.

            var totalViolation = unfrozen.fold(0.0f) { acc, child ->
                // TODO - not really spec abiding but needs to be done somewhere. probably somewhere else though.
                // The following logic was developed not from the spec but by trial and error looking into how
                // webkit handled various scenarios. Can probably be solved better by passing in
                // min-content max-content constraints from the top. Need to figure out correct thing to do here as
                // just piling on more conditionals.
                var minMain = if (isRow && child.node.measure == null) {
                    computeInternal(
                        node = child.node,
                        nodeSize = Size.undefinedNumber(),
                        parentSize = availableSpace.toStretchNumberSize(),
                        performLayout = false,
                        mainSize = false
                    )
                        .size
                        .width
                        .maybeMin(child.size.width)
                        .maybeMax(child.minSize.width)
                } else {
                    child.minSize.main(dir).asFloat()
                }

                var maxMain = child.maxSize.main(dir)
                var clamped = child.targetSize.main(dir).maybeMin(maxMain).maybeMax(minMain).maybeMax(0.0f)
                child.violation = clamped - child.targetSize.main(dir)
                child.targetSize.setMain(dir, clamped)
                child.outerTargetSize.setMain(dir, child.targetSize.main(dir) + child.margin.main(dir))

                acc + child.violation
            }


            // e. Freeze over-flexed items. The total violation is the sum of the adjustments
            //    from the previous step ∑(clamped size - unclamped size). If the total violation is:
            //    - Zero
            //        Freeze all items.
            //    - Positive
            //        Freeze all the items with min violations.
            //    - Negative
            //        Freeze all the items with max violations.

            for (child in unfrozen) {
                when {
                    totalViolation > 0.0f -> child.frozen = child.violation > 0.0f
                    totalViolation < 0.0f -> child.frozen = child.violation < 0.0f
                    else -> child.frozen = true
                }
            }

            // f. Return to the start of this loop.
        }

    }

    // Not part of the spec from what i can see but seems correct
    containerSize.setMain(
        dir,
        nodeSize.main(dir).orElse(let {
            var longestLine = flexLines.fold(Float.MIN_VALUE) { acc, line ->
                var length: Float = line.items.map { item -> item.outerTargetSize.main(dir) }.sum()
                acc.maybeMax(length)
            }

            var size = longestLine + paddingBorder.main(dir)
            val main = availableSpace.main(dir)
            when {
                main.isDefined() && flexLines.size > 1 && size < main -> availableSpace.main(dir)
                else -> size
            }
        }),
    )

    innerContainerSize.setMain(dir, containerSize.main(dir) - paddingBorder.main(dir))

    // 9.4. Cross Size Determination

    // 7. Determine the hypothetical cross size of each item by performing layout with the
    //    used main size and the available space, treating auto as fit-content.

    for (line in flexLines) {
        for (child in line.items) {
            var childCross =
                child.size.cross(dir).maybeMax(child.minSize.cross(dir)).maybeMin(child.maxSize.cross(dir))

            val targetSize = child.targetSize.toStretchNumberSize()
            child.hypotheticalInnerSize.setCross(
                dir,
                computeInternal(
                    node = child.node,
                    nodeSize = Size(
                        width = if (isRow) {
                            targetSize.width
                        } else {
                            childCross
                        },
                        height = if (isRow) {
                            childCross
                        } else {
                            targetSize.height
                        },
                    ),
                    parentSize = Size(
                        width = if (isRow) {
                            containerSize.main(dir)
                        } else {
                            availableSpace.width
                        },
                        height = if (isRow) {
                            availableSpace.height
                        } else {
                            containerSize.main(dir)
                        },
                    ).toStretchNumberSize(),
                    performLayout = false, mainSize = false,
                )
                    .size
                    .cross(dir)
                    .maybeMax(child.minSize.cross(dir))
                    .maybeMin(child.maxSize.cross(dir)),
            )

            child
                .hypotheticalOuterSize
                .setCross(dir, child.hypotheticalInnerSize.cross(dir) + child.margin.cross(dir))
        }
    }

    // TODO - probably should move this somewhere else as it doesn't make a ton of sense here but we need it below
    // TODO - This is expensive and should only be done if we really require a baseline. aka, make it lazy

    fun calcBaseline(node: NodeData, layout: Layout): Float {
        return if (node.children.isEmpty()) {
            layout.size.height
        } else {
            var child = node.children.first()
            calcBaseline(child, child.layout)
        }
    }

    if (hasBaselineChild) {
        for (line in flexLines) {
            for (child in line.items) {
                var result = computeInternal(
                    node = child.node,
                    nodeSize = Size(
                        width = if (isRow) {
                            child.targetSize.width
                        } else {
                            child.hypotheticalInnerSize.width
                        },
                        height = if (isRow) {
                            child.hypotheticalInnerSize.height
                        } else {
                            child.targetSize.height
                        },
                    ).toStretchNumberSize(),
                    parentSize = Size(
                        width = if (isRow) {
                            StretchNumber.from(containerSize.width)
                        } else {
                            nodeSize.width
                        },
                        height = if (isRow) {
                            nodeSize.height
                        } else {
                            StretchNumber.from(containerSize.height)
                        },
                    ),
                    performLayout = true,
                    mainSize = false,
                )

                child.baseline = calcBaseline(
                    child.node,
                    Layout(
                        order = node.children.indexOf(child.node).toUInt(),
                        size = result.size,
                        location = Point.zero(),
                    ),
                )
            }
        }
    }

    // 8. Calculate the cross size of each flex line.
    //    If the flex container is single-line and has a definite cross size, the cross size
    //    of the flex line is the flex container’s inner cross size. Otherwise, for each flex line:
    //
    //    If the flex container is single-line, then clamp the line’s cross-size to be within
    //    the container’s computed min and max cross sizes. Note that if CSS 2.1’s definition
    //    of min/max-width/height applied more generally, this behavior would fall out automatically.

    if (flexLines.size == 1 && nodeSize.cross(dir).isDefined) {
        flexLines[0].crossSize = (nodeSize.cross(dir) - paddingBorder.cross(dir)).orElse(0.0f)
    } else {
        for (line in flexLines) {
            //    1. Collect all the flex items whose inline-axis is parallel to the main-axis, whose
            //       align-self is baseline, and whose cross-axis margins are both non-auto. Find the
            //       largest of the distances between each item’s baseline and its hypothetical outer
            //       cross-start edge, and the largest of the distances between each item’s baseline
            //       and its hypothetical outer cross-end edge, and sum these two values.

            //    2. Among all the items not collected by the previous step, find the largest
            //       outer hypothetical cross size.

            //    3. The used cross-size of the flex line is the largest of the numbers found in the
            //       previous two steps and zero.

            var maxBaseline: Float =
                line.items.map { child -> child.baseline }.fold(0.0f) { acc, x -> acc.maybeMax(x) }
            line.crossSize = line
                .items
                .map { child ->
                    var childStyle = child.node.style
                    if (childStyle.alignSelf(node.style) == AlignSelf.Baseline
                        && childStyle.crossMarginStart(dir) != StretchDimension.Auto
                        && childStyle.crossMarginEnd(dir) != StretchDimension.Auto
                        && childStyle.crossSize(dir) == StretchDimension.Auto
                    ) {
                        maxBaseline - child.baseline + child.hypotheticalOuterSize.cross(dir)
                    } else {
                        child.hypotheticalOuterSize.cross(dir)
                    }
                }
                .fold(0.0f) { acc, x -> acc.maybeMax(x) }
        }
    }


    // 9. Handle 'align-content: stretch'. If the flex container has a definite cross size,
    //    align-content is stretch, and the sum of the flex lines' cross sizes is less than
    //    the flex container’s inner cross size, increase the cross size of each flex line
    //    by equal amounts such that the sum of their cross sizes exactly equals the
    //    flex container’s inner cross size.

    if (node.style.alignContent == AlignContent.Stretch && nodeSize.cross(dir).isDefined) {
        var totalCross: Float = flexLines.map { line -> line.crossSize }.sum()
        var innerCross = (nodeSize.cross(dir) - paddingBorder.cross(dir)).orElse(0.0f)

        if (totalCross < innerCross) {
            var remaining = innerCross - totalCross
            var addition = remaining / flexLines.size as Float
            flexLines.forEach { line -> line.crossSize += addition }
        }
    }

    // 10. Collapse visibility:collapse items. If any flex items have visibility: collapse,
    //     note the cross size of the line they’re in as the item’s strut size, and restart
    //     layout from the beginning.
    //
    //     In this second layout round, when collecting items into lines, treat the collapsed
    //     items as having zero main size. For the rest of the algorithm following that step,
    //     ignore the collapsed items entirely (as if they were display:none) except that after
    //     calculating the cross size of the lines, if any line’s cross size is less than the
    //     largest strut size among all the collapsed items in the line, set its cross size to
    //     that strut size.
    //
    //     Skip this step in the second layout round.

    // TODO implement once (if ever) we support visibility:collapse


    // 11. Determine the used cross size of each flex item. If a flex item has align-self: stretch,
    //     its computed cross size property is auto, and neither of its cross-axis margins are auto,
    //     the used outer cross size is the used cross size of its flex line, clamped according to
    //     the item’s used min and max cross sizes. Otherwise, the used cross size is the item’s
    //     hypothetical cross size.
    //
    //     If the flex item has align-self: stretch, redo layout for its contents, treating this
    //     used size as its definite cross size so that percentage-sized children can be resolved.
    //
    //     Note that this step does not affect the main size of the flex item, even if it has an
    //     intrinsic aspect ratio.

    for (line in flexLines) {
        var lineCrossSize = line.crossSize

        for (child in line.items) {
            var childStyle = child.node.style
            child.targetSize.setCross(
                dir,
                if (childStyle.alignSelf(node.style) == AlignSelf.Stretch
                    && childStyle.crossMarginStart(dir) != StretchDimension.Auto
                    && childStyle.crossMarginEnd(dir) != StretchDimension.Auto
                    && childStyle.crossSize(dir) == StretchDimension.Auto
                ) {
                    (lineCrossSize - child.margin.cross(dir))
                        .maybeMax(child.minSize.cross(dir))
                        .maybeMin(child.maxSize.cross(dir))
                } else {
                    child.hypotheticalInnerSize.cross(dir)
                },
            )

            child.outerTargetSize.setCross(dir, child.targetSize.cross(dir) + child.margin.cross(dir))
        }
    }

    // 9.5. Main-Axis Alignment

    // 12. Distribute any remaining free space. For each flex line:
    //     1. If the remaining free space is positive and at least one main-axis margin on this
    //        line is auto, distribute the free space equally among these margins. Otherwise,
    //        set all auto margins to zero.
    //     2. Align the items along the main-axis per justify-content.

    for (line in flexLines) {
        var usedSpace: Float = line.items.map { child -> child.outerTargetSize.main(dir) }.sum()
        var freeSpace = innerContainerSize.main(dir) - usedSpace
        var numAutoMargins = 0

        for (child in line.items) {
            var childStyle = child.node.style
            if (childStyle.mainMarginStart(dir) == StretchDimension.Auto) {
                numAutoMargins += 1
            }
            if (childStyle.mainMarginEnd(dir) == StretchDimension.Auto) {
                numAutoMargins += 1
            }
        }

        if (freeSpace > 0.0f && numAutoMargins > 0) {
            var margin = freeSpace / numAutoMargins.toFloat()

            for (child in line.items) {
                var childStyle = child.node.style
                if (childStyle.mainMarginStart(dir) == StretchDimension.Auto) {
                    if (isRow) {
                        child.margin.start = margin
                    } else {
                        child.margin.top = margin
                    }
                }
                if (childStyle.mainMarginEnd(dir) == StretchDimension.Auto) {
                    if (isRow) {
                        child.margin.end = margin
                    } else {
                        child.margin.bottom = margin
                    }
                }
            }
        } else {
            var numItems = line.items.size
            var layoutReverse = dir.isReverse()

            var justifyItem: (Int, FlexItem) -> Unit = { i, child ->
                var isFirst = i == 0

                child.offsetMain = when (node.style.justifyContent) {
                    JustifyContent.FlexStart -> {
                        if (layoutReverse && isFirst) {
                            freeSpace
                        } else {
                            0.0f
                        }
                    }
                    JustifyContent.Center -> {
                        if (isFirst) {
                            freeSpace / 2.0f
                        } else {
                            0.0f
                        }
                    }
                    JustifyContent.FlexEnd -> {
                        if (isFirst && !layoutReverse) {
                            freeSpace
                        } else {
                            0.0f
                        }
                    }
                    JustifyContent.SpaceBetween -> {
                        if (isFirst) {
                            0.0f
                        } else {
                            freeSpace / (numItems - 1).toFloat()
                        }
                    }
                    JustifyContent.SpaceAround -> {
                        if (isFirst) {
                            (freeSpace / numItems.toFloat()) / 2.0f
                        } else {
                            freeSpace / numItems.toFloat()
                        }
                    }
                    JustifyContent.SpaceEvenly -> freeSpace / (numItems + 1).toFloat()
                }
            }

            if (layoutReverse) {
                line.items.asReversed().forEachIndexed(justifyItem)
            } else {
                line.items.forEachIndexed(justifyItem)
            }
        }
    }

    // 9.6. Cross-Axis Alignment

    // 13. Resolve cross-axis auto margins. If a flex item has auto cross-axis margins:
    //     - If its outer cross size (treating those auto margins as zero) is less than the
    //       cross size of its flex line, distribute the difference in those sizes equally
    //       to the auto margins.
    //     - Otherwise, if the block-start or inline-start margin (whichever is in the cross axis)
    //       is auto, set it to zero. Set the opposite margin so that the outer cross size of the
    //       item equals the cross size of its flex line.

    for (line in flexLines) {
        var lineCrossSize = line.crossSize
        var maxBaseline: Float = line.items.map { child -> child.baseline }.fold(0.0f) { acc, x -> acc.maybeMax(x) }
        for (child in line.items) {
            var freeSpace = lineCrossSize - child.outerTargetSize.cross(dir)
            var childStyle = child.node.style

            if (childStyle.crossMarginStart(dir) == StretchDimension.Auto
                && childStyle.crossMarginEnd(dir) == StretchDimension.Auto
            ) {
                if (isRow) {
                    child.margin.top = freeSpace / 2.0f
                    child.margin.bottom = freeSpace / 2.0f
                } else {
                    child.margin.start = freeSpace / 2.0f
                    child.margin.end = freeSpace / 2.0f
                }
            } else if (childStyle.crossMarginStart(dir) == StretchDimension.Auto) {
                if (isRow) {
                    child.margin.top = freeSpace
                } else {
                    child.margin.start = freeSpace
                }
            } else if (childStyle.crossMarginEnd(dir) == StretchDimension.Auto) {
                if (isRow) {
                    child.margin.bottom = freeSpace
                } else {
                    child.margin.end = freeSpace
                }
            } else {
                // 14. Align all flex items along the cross-axis per align-self, if neither of the item’s
                //     cross-axis margins are auto.

                child.offsetCross = when (childStyle.alignSelf(node.style)) {
                    AlignSelf.Auto -> 0.0f // Should never happen
                    AlignSelf.FlexStart -> {
                        if (isWrapReverse) {
                            freeSpace
                        } else {
                            0.0f
                        }
                    }
                    AlignSelf.FlexEnd -> {
                        if (isWrapReverse) {
                            0.0f
                        } else {
                            freeSpace
                        }
                    }
                    AlignSelf.Center -> freeSpace / 2.0f
                    AlignSelf.Baseline -> {
                        if (isRow) {
                            maxBaseline - child.baseline
                        } else {
                            // baseline alignment only makes sense if the direction is row
                            // we treat it as flex-start alignment in columns.
                            if (isWrapReverse) {
                                freeSpace
                            } else {
                                0.0f
                            }
                        }
                    }
                    AlignSelf.Stretch -> {
                        if (isWrapReverse) {
                            freeSpace
                        } else {
                            0.0f
                        }
                    }
                }
            }
        }
    }


    // 15. Determine the flex container’s used cross size:
    //     - If the cross size property is a definite size, use that, clamped by the used
    //       min and max cross sizes of the flex container.
    //     - Otherwise, use the sum of the flex lines' cross sizes, clamped by the used
    //       min and max cross sizes of the flex container.

    var totalCrossSize: Float = flexLines.map { line -> line.crossSize }.sum()
    containerSize.setCross(dir, nodeSize.cross(dir).orElse(totalCrossSize + paddingBorder.cross(dir)))
    innerContainerSize.setCross(dir, containerSize.cross(dir) - paddingBorder.cross(dir))


    // We have the container size. If our caller does not care about performing
    // layout we are done now.
    if (!performLayout) {
        var result = ComputeResult(size = containerSize)
        setCache(node, mainSize, Cache(nodeSize, parentSize, performLayout, result = result.clone()))
        return result
    }

    // 16. Align all flex lines per align-content.

    var freeSpace = innerContainerSize.cross(dir) - totalCrossSize
    var numLines = flexLines.size

    var alignLine: (Int, FlexLine) -> Unit = { i, line ->
        var isFirst = i == 0

        line.offsetCross = when (node.style.alignContent) {
            AlignContent.FlexStart -> {
                if (isFirst && isWrapReverse) {
                    freeSpace
                } else {
                    0.0f
                }
            }
            AlignContent.FlexEnd -> {
                if (isFirst && !isWrapReverse) {
                    freeSpace
                } else {
                    0.0f
                }
            }
            AlignContent.Center -> {
                if (isFirst) {
                    freeSpace / 2.0f
                } else {
                    0.0f
                }
            }
            AlignContent.Stretch -> 0.0f
            AlignContent.SpaceBetween -> {
                if (isFirst) {
                    0.0f
                } else {
                    freeSpace / (numLines - 1).toFloat()
                }
            }
            AlignContent.SpaceAround -> {
                if (isFirst) {
                    (freeSpace / numLines.toFloat()) / 2.0f
                } else {
                    freeSpace / numLines.toFloat()
                }
            }
        }
    }

    if (isWrapReverse) {
        flexLines.asReversed().forEachIndexed(alignLine)
    } else {
        flexLines.forEachIndexed(alignLine)
    }

    // Do a final layout pass and gather the resulting layouts
    run {
        var totalOffsetCross = paddingBorder.crossStart(dir)

        var layoutLine: (FlexLine) -> Unit = { line ->
            var totalOffsetMain = paddingBorder.mainStart(dir)
            var lineOffsetCross = line.offsetCross

            var layoutItem: (FlexItem) -> Unit = { child ->
                var result = computeInternal(
                    node = child.node,
                    nodeSize = child.targetSize.toStretchNumberSize(),
                    parentSize = containerSize.toStretchNumberSize(),
                    performLayout = true,
                    mainSize = false,
                )

                var offsetMain =
                    totalOffsetMain + child.offsetMain + child.margin.mainStart(dir) + (child.position.mainStart(dir)
                        .orElse(0.0f) - child.position.mainEnd(dir).orElse(0.0f))

                var offsetCross =
                    totalOffsetCross + child.offsetCross + lineOffsetCross + child.margin.crossStart(dir) +
                            (child.position.crossStart(dir).orElse(0.0f) - child.position.crossEnd(dir).orElse(0.0f))

                child.node.layout = Layout(
                    order = node.children.indexOf(child.node).toUInt(),
                    size = result.size,
                    location = Point(
                        x = if (isRow) offsetMain else offsetCross,
                        y = if (isColumn) offsetMain else offsetCross,
                    ),
                )

                totalOffsetMain += child.offsetMain + child.margin.main(dir) + result.size.main(dir)
            }

            if (dir.isReverse()) {
                line.items.asReversed().forEach(layoutItem)
            } else {
                line.items.forEach(layoutItem)
            }

            totalOffsetCross += lineOffsetCross + line.crossSize
        }

        if (isWrapReverse) {
            flexLines.asReversed().forEach(layoutLine)
        } else {
            flexLines.forEach(layoutLine)
        }
    }


    // Before returning we perform absolute layout on all absolutely positioned children
    run {
        var candidates = node.children
            .filter { child -> child.style.positionType == PositionType.Absolute }
            .toList()

        for ((order, child) in candidates.withIndex()) {
            var containerWidth = containerSize.width
            var containerHeight = containerSize.height

            var childStyle = child.style

            var start =
                childStyle.position.start.resolve(containerWidth) + childStyle.margin.start.resolve(containerWidth)
            var end =
                childStyle.position.end.resolve(containerWidth) + childStyle.margin.end.resolve(containerWidth)
            var top = childStyle.position.top.resolve(containerHeight) + childStyle.margin.top.resolve(containerHeight)
            var bottom =
                childStyle.position.bottom.resolve(containerHeight) + childStyle.margin.bottom.resolve(containerHeight)

            var (startMain, endMain) = if (isRow) {
                (start to end)
            } else {
                (top to bottom)
            }
            var (startCross, endCross) = if (isRow) {
                (top to bottom)
            } else {
                (start to end)
            }

            var width = childStyle
                .size
                .width
                .resolve(containerWidth)
                .maybeMax(childStyle.minSize.width.resolve(containerWidth))
                .maybeMin(childStyle.maxSize.width.resolve(containerWidth))
                .orElse(
                    if (start.isDefined && end.isDefined) {
                        containerWidth - start - end
                    } else {
                        StretchNumber.Undefined
                    }
                )

            var height = childStyle
                .size
                .height
                .resolve(containerHeight)
                .maybeMax(childStyle.minSize.height.resolve(containerHeight))
                .maybeMin(childStyle.maxSize.height.resolve(containerHeight))
                .orElse(
                    if (top.isDefined && bottom.isDefined) {
                        containerHeight - top - bottom
                    } else {
                        StretchNumber.Undefined
                    }
                )

            var result = computeInternal(
                child,
                Size(width, height),
                Size(width = containerWidth, height = containerHeight).toStretchNumberSize(),
                true,
                false,
            )

            var freeMainSpace = containerSize.main(dir) - result
                .size
                .main(dir)
                .maybeMax(childStyle.minMainSize(dir).resolve(nodeInnerSize.main(dir)))
                .maybeMin(childStyle.maxMainSize(dir).resolve(nodeInnerSize.main(dir)))

            var freeCrossSpace = containerSize.cross(dir) - result
                .size
                .cross(dir)
                .maybeMax(childStyle.minCrossSize(dir).resolve(nodeInnerSize.cross(dir)))
                .maybeMin(childStyle.maxCrossSize(dir).resolve(nodeInnerSize.cross(dir)))

            var offsetMain = if (startMain.isDefined) {
                startMain.orElse(0.0f) + border.mainStart(dir)
            } else if (endMain.isDefined) {
                freeMainSpace - endMain.orElse(0.0f) - border.mainEnd(dir)
            } else {
                when (node.style.justifyContent) {
                    JustifyContent.SpaceBetween, JustifyContent.FlexStart -> paddingBorder.mainStart(dir)
                    JustifyContent.FlexEnd -> freeMainSpace - paddingBorder.mainEnd(dir)
                    JustifyContent.SpaceEvenly, JustifyContent.SpaceAround, JustifyContent.Center -> {
                        freeMainSpace / 2.0f
                    }
                }
            }

            var offsetCross = if (startCross.isDefined) {
                startCross.orElse(0.0f) + border.crossStart(dir)
            } else if (endCross.isDefined) {
                freeCrossSpace - endCross.orElse(0.0f) - border.crossEnd(dir)
            } else {
                when (childStyle.alignSelf(node.style)) {
                    AlignSelf.Auto -> 0.0f // Should never happen
                    AlignSelf.FlexStart -> {
                        if (isWrapReverse) {
                            freeCrossSpace - paddingBorder.crossEnd(dir)
                        } else {
                            paddingBorder.crossStart(dir)
                        }
                    }
                    AlignSelf.FlexEnd -> {
                        if (isWrapReverse) {
                            paddingBorder.crossStart(dir)
                        } else {
                            freeCrossSpace - paddingBorder.crossEnd(dir)
                        }
                    }
                    AlignSelf.Center -> freeCrossSpace / 2.0f
                    AlignSelf.Baseline -> freeCrossSpace / 2.0f // Treat as center for now until we have baseline support
                    AlignSelf.Stretch -> {
                        if (isWrapReverse) {
                            freeCrossSpace - paddingBorder.crossEnd(dir)
                        } else {
                            paddingBorder.crossStart(dir)
                        }
                    }
                }
            }

            child.layout = Layout(
                order = order.toUInt(),
                size = result.size,
                location = Point(
                    x = if (isRow) {
                        offsetMain
                    } else {
                        offsetCross
                    },
                    y = if (isColumn) {
                        offsetMain
                    } else {
                        offsetCross
                    },
                ),
            )
        }
    }


    fun hiddenLayout(node: NodeId, order: UInt) {
        node.layout = Layout(order, size = Size.zero(), location = Point.zero())

        for ((childOrder, child) in node.children.withIndex()) {
            hiddenLayout(child, childOrder.toUInt())
        }
    }


    for ((order, child) in node.children.withIndex()) {
        if (child.style.display == Display.None) {
            hiddenLayout(child, order.toUInt())
        }
    }

    var result = ComputeResult(size = containerSize)
    setCache(node, mainSize, Cache(nodeSize, parentSize, performLayout, result = result.clone()))

    return result
}