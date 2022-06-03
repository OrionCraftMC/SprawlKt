package io.github.orioncraftmc.sprawlkt.tests

import io.github.orioncraftmc.sprawlkt.geometry.Size
import io.github.orioncraftmc.sprawlkt.node.MeasureFunc
import io.github.orioncraftmc.sprawlkt.node.Sprawl
import io.github.orioncraftmc.sprawlkt.style.Style
import io.github.orioncraftmc.sprawlkt.style.enums.Display
import io.github.orioncraftmc.sprawlkt.style.enums.FlexDirection
import kotlin.test.Test
import kotlin.test.assertEquals

class NodeTests {

    @Test
    fun children() {
        val child1 = Sprawl.newNode(Style())
        val child2 = Sprawl.newNode(Style())
        val node = Sprawl.newNode(Style(), listOf(child1, child2))

        assertEquals(2, Sprawl.childCount(node))
        assertEquals(child1, Sprawl.children(node)[0])
        assertEquals(child2, Sprawl.children(node)[1])
    }

    @Test
    fun setMeasure() {
        val node = Sprawl.newLeaf(Style(), MeasureFunc { Size(width = 200.0f, height = 200.0f) })
        Sprawl.computeLayout(node, Size.undefinedNumber())
        assertEquals(200.0f, Sprawl.layout(node).size.width)

        Sprawl.setMeasure(node, MeasureFunc { Size(width = 100.0f, height = 100.0f) })
        Sprawl.computeLayout(node, Size.undefinedNumber())
        assertEquals(100.0f, Sprawl.layout(node).size.width)
    }

    @Test
    fun addChild() {
        val node = Sprawl.newNode(Style())
        assertEquals(0, Sprawl.childCount(node))

        val child1 = Sprawl.newNode(Style())
        Sprawl.addChild(node, child1)
        assertEquals(1, Sprawl.childCount(node))

        val child2 = Sprawl.newNode(Style())
        Sprawl.addChild(node, child2)
        assertEquals(2, Sprawl.childCount(node))
    }

    @Test
    fun removeChild() {

        val child1 = Sprawl.newNode(Style())
        val child2 = Sprawl.newNode(Style())

        val node = Sprawl.newNode(Style(), listOf(child1, child2))
        assertEquals(2, Sprawl.childCount(node))

        Sprawl.removeChild(node, child1)
        assertEquals(1, Sprawl.childCount(node))
        assertEquals(child2, Sprawl.children(node)[0])

        Sprawl.removeChild(node, child2)
        assertEquals(0, Sprawl.childCount(node))
    }

    @Test
    fun removeChildAtIndex() {

        val child1 = Sprawl.newNode(Style())
        val child2 = Sprawl.newNode(Style())

        val node = Sprawl.newNode(Style(), listOf(child1, child2))
        assertEquals(2, Sprawl.childCount(node))

        Sprawl.removeChildAtIndex(node, 0)
        assertEquals(1, Sprawl.childCount(node))
        assertEquals(child2, Sprawl.children(node)[0])

        Sprawl.removeChildAtIndex(node, 0)
        assertEquals(0, Sprawl.childCount(node))
    }

    @Test
    fun replaceChildAtIndex() {

        val child1 = Sprawl.newNode(Style())
        val child2 = Sprawl.newNode(Style())

        val node = Sprawl.newNode(Style(), listOf(child1))
        assertEquals(1, Sprawl.childCount(node))
        assertEquals(child1, Sprawl.children(node)[0])

        Sprawl.replaceChildAtIndex(node, 0, child2)
        assertEquals(1, Sprawl.childCount(node))
        assertEquals(child2, Sprawl.children(node)[0])
    }

    @Test
    fun remove() {

        val style2 = Style(flexDirection = FlexDirection.Column)

        // Build a linear tree layout = <0> <- <1> <- <2>
        val node2 = Sprawl.newNode(style2)
        val node1 = Sprawl.newNode(Style(), listOf(node2))
        val node0 = Sprawl.newNode(Style(), listOf(node1))

        assertEquals(listOf(node1), Sprawl.children(node0))

        // Disconnect the tree = <0> <2>
        Sprawl.remove(node1)

        assert(Sprawl.children(node0).isEmpty())
        assert(Sprawl.children(node2).isEmpty())
        assertEquals(style2.flexDirection, Sprawl.style(node2).flexDirection)
    }

    @Test
    fun setChildren() {

        val child1 = Sprawl.newNode(Style())
        val child2 = Sprawl.newNode(Style())
        val node = Sprawl.newNode(Style(), listOf(child1, child2))

        assertEquals(2, Sprawl.childCount(node))
        assertEquals(child1, Sprawl.children(node)[0])
        assertEquals(child2, Sprawl.children(node)[1])

        val child3 = Sprawl.newNode(Style())
        val child4 = Sprawl.newNode(Style())
        Sprawl.setChildren(node, listOf(child3, child4))

        assertEquals(2, Sprawl.childCount(node))
        assertEquals(child3, Sprawl.children(node)[0])
        assertEquals(child4, Sprawl.children(node)[1])
    }

    @Test
    fun setStyle() {

        val node = Sprawl.newNode(Style())
        assertEquals(Display.Flex, Sprawl.style(node).display)

        Sprawl.setStyle(node, Style(display = Display.None))
        assertEquals(Display.None, Sprawl.style(node).display)
    }

    @Test
    fun markDirty() {

        val child1 = Sprawl.newNode(Style())
        val child2 = Sprawl.newNode(Style())
        val node = Sprawl.newNode(Style(), listOf(child1, child2))

        Sprawl.computeLayout(node, Size.undefinedNumber())

        assertEquals(false, Sprawl.dirty(child1))
        assertEquals(false, Sprawl.dirty(child2))
        assertEquals(false, Sprawl.dirty(node))

        Sprawl.markDirty(node)
        assertEquals(false, Sprawl.dirty(child1))
        assertEquals(false, Sprawl.dirty(child2))
        assertEquals(true, Sprawl.dirty(node))

        Sprawl.computeLayout(node, Size.undefinedNumber())
        Sprawl.markDirty(child1)
        assertEquals(true, Sprawl.dirty(child1))
        assertEquals(false, Sprawl.dirty(child2))
        assertEquals(true, Sprawl.dirty(node))
    }

    @Test
    fun removeLastNode() {
        val parent = Sprawl.newNode(Style())
        val child = Sprawl.newNode(Style())
        Sprawl.addChild(parent, child)

        Sprawl.remove(child)
        Sprawl.remove(parent)
    }
}