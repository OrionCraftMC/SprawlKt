package io.github.orioncraftmc.stretchkt.tests

import io.github.orioncraftmc.stretchkt.extensions.swapRemove
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class CollectionExtensionsTest {
    @Test
    fun `MutableList#swapRemove implementation is correct`() {
        val v = mutableListOf("foo", "bar", "baz", "qux")

        assertEquals("bar", v.swapRemove(1))
        assertContentEquals(listOf("foo", "qux", "baz"), v)

        assertEquals("foo", v.swapRemove(0))
        assertContentEquals(listOf("baz", "qux"), v)
    }
}