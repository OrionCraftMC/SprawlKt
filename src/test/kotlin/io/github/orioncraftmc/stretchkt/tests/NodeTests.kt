package io.github.orioncraftmc.stretchkt.tests

import io.github.orioncraftmc.stretchkt.geometry.Size
import io.github.orioncraftmc.stretchkt.node.MeasureFunc
import io.github.orioncraftmc.stretchkt.node.Stretch
import io.github.orioncraftmc.stretchkt.style.Style
import io.github.orioncraftmc.stretchkt.style.enums.Display
import io.github.orioncraftmc.stretchkt.style.enums.FlexDirection
import kotlin.test.Test
import kotlin.test.assertEquals

class NodeTests {

    @Test
    fun children() {
        val child1 = Stretch.newNode(Style())
        val child2 = Stretch.newNode(Style())
        val node = Stretch.newNode(Style(), listOf(child1, child2))

        assertEquals(2, Stretch.childCount(node))
        assertEquals(child1, Stretch.children(node)[0])
        assertEquals(child2, Stretch.children(node)[1])
    }

    @Test
    fun setMeasure() {
        val node = Stretch.newLeaf(Style(), MeasureFunc { Size(width = 200.0f, height = 200.0f) })
        Stretch.computeLayout(node, Size.undefinedNumber())
        assertEquals(200.0f, Stretch.layout(node).size.width)

        Stretch.setMeasure(node, MeasureFunc { Size(width = 100.0f, height = 100.0f) })
        Stretch.computeLayout(node, Size.undefinedNumber())
        assertEquals(100.0f, Stretch.layout(node).size.width)
    }

    @Test
    fun addChild() {
        val node = Stretch.newNode(Style())
        assertEquals(0, Stretch.childCount(node))

        val child1 = Stretch.newNode(Style())
        Stretch.addChild(node, child1)
        assertEquals(1, Stretch.childCount(node))

        val child2 = Stretch.newNode(Style())
        Stretch.addChild(node, child2)
        assertEquals(2, Stretch.childCount(node))
    }

    @Test
    fun removeChild() {

        val child1 = Stretch.newNode(Style())
        val child2 = Stretch.newNode(Style())

        val node = Stretch.newNode(Style(), listOf(child1, child2))
        assertEquals(2, Stretch.childCount(node))

        Stretch.removeChild(node, child1)
        assertEquals(1, Stretch.childCount(node))
        assertEquals(child2, Stretch.children(node)[0])

        Stretch.removeChild(node, child2)
        assertEquals(0, Stretch.childCount(node))
    }

    @Test
    fun removeChildAtIndex() {

        val child1 = Stretch.newNode(Style())
        val child2 = Stretch.newNode(Style())

        val node = Stretch.newNode(Style(), listOf(child1, child2))
        assertEquals(2, Stretch.childCount(node))

        Stretch.removeChildAtIndex(node, 0)
        assertEquals(1, Stretch.childCount(node))
        assertEquals(child2, Stretch.children(node)[0])

        Stretch.removeChildAtIndex(node, 0)
        assertEquals(0, Stretch.childCount(node))
    }

    @Test
    fun replaceChildAtIndex() {

        val child1 = Stretch.newNode(Style())
        val child2 = Stretch.newNode(Style())

        val node = Stretch.newNode(Style(), listOf(child1))
        assertEquals(1, Stretch.childCount(node))
        assertEquals(child1, Stretch.children(node)[0])

        Stretch.replaceChildAtIndex(node, 0, child2)
        assertEquals(1, Stretch.childCount(node))
        assertEquals(child2, Stretch.children(node)[0])
    }

    @Test
    fun remove() {

        val style2 = Style(flexDirection = FlexDirection.Column)

        // Build a linear tree layout = <0> <- <1> <- <2>
        val node2 = Stretch.newNode(style2)
        val node1 = Stretch.newNode(Style(), listOf(node2))
        val node0 = Stretch.newNode(Style(), listOf(node1))

        assertEquals(listOf(node1), Stretch.children(node0))

        // Disconnect the tree = <0> <2>
        Stretch.remove(node1)

        assert(Stretch.children(node0).isEmpty())
        assert(Stretch.children(node2).isEmpty())
        assertEquals(style2.flexDirection, Stretch.style(node2).flexDirection)
    }

    @Test
    fun setChildren() {

        val child1 = Stretch.newNode(Style())
        val child2 = Stretch.newNode(Style())
        val node = Stretch.newNode(Style(), listOf(child1, child2))

        assertEquals(2, Stretch.childCount(node))
        assertEquals(child1, Stretch.children(node)[0])
        assertEquals(child2, Stretch.children(node)[1])

        val child3 = Stretch.newNode(Style())
        val child4 = Stretch.newNode(Style())
        Stretch.setChildren(node, listOf(child3, child4))

        assertEquals(2, Stretch.childCount(node))
        assertEquals(child3, Stretch.children(node)[0])
        assertEquals(child4, Stretch.children(node)[1])
    }

    @Test
    fun setStyle() {

        val node = Stretch.newNode(Style())
        assertEquals(Display.Flex, Stretch.style(node).display)

        Stretch.setStyle(node, Style(display = Display.None))
        assertEquals(Display.None, Stretch.style(node).display)
    }

    @Test
    fun markDirty() {

        val child1 = Stretch.newNode(Style())
        val child2 = Stretch.newNode(Style())
        val node = Stretch.newNode(Style(), listOf(child1, child2))

        Stretch.computeLayout(node, Size.undefinedNumber())

        assertEquals(false, Stretch.dirty(child1))
        assertEquals(false, Stretch.dirty(child2))
        assertEquals(false, Stretch.dirty(node))

        Stretch.markDirty(node)
        assertEquals(false, Stretch.dirty(child1))
        assertEquals(false, Stretch.dirty(child2))
        assertEquals(true, Stretch.dirty(node))

        Stretch.computeLayout(node, Size.undefinedNumber())
        Stretch.markDirty(child1)
        assertEquals(true, Stretch.dirty(child1))
        assertEquals(false, Stretch.dirty(child2))
        assertEquals(true, Stretch.dirty(node))
    }

    @Test
    fun removeLastNode() {
        val parent = Stretch.newNode(Style())
        val child = Stretch.newNode(Style())
        Stretch.addChild(parent, child)

        Stretch.remove(child)
        Stretch.remove(parent)
    }
}