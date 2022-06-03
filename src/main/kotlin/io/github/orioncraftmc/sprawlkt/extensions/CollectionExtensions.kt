package io.github.orioncraftmc.sprawlkt.extensions

internal fun <E> List<E>.splitAtIndex(index: Int): Pair<List<E>, List<E>> {
    var itemCount = 0
    return this.partition { itemCount++ < index }
}