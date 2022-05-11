package io.github.orioncraftmc.stretchkt.extensions

fun <T> MutableList<T>.swapRemove(index: Int): T {
    val lastIndex = this.lastIndex
    val toRemove = this[index]

    // Swap the last element with the element to remove
    this[index] = this[lastIndex]
    this[lastIndex] = toRemove

    // Remove the last element
    return removeAt(lastIndex)
}