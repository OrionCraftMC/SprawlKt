package io.github.orioncraftmc.stretchkt.tests

import io.github.orioncraftmc.stretchkt.extensions.splitAtIndex
import kotlin.test.Test
import kotlin.test.assertContentEquals

class CollectionExtensionsTest {
    @Test
    fun `List#splitAtIndex implementation is correct`() {
        val v = mutableListOf(1, 0, 3, 0, 5, 6)

        val (left, right) = v.splitAtIndex(2)

        assertContentEquals(listOf(1, 0), left)
        assertContentEquals(listOf(3, 0, 5, 6), right)
    }
}