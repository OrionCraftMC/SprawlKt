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

fun <E> List<E>.splitAtIndex(index: Int): Pair<List<E>, List<E>> {
    var itemCount = 0
    return this.partition { itemCount++ < index }
}