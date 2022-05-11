package io.github.orioncraftmc.stretchkt.geometry


data class Rect<T>(
    val start: T,
    val end: T,
    val top: T /* Just like me */,
    val bottom: T /* Just like KingTux */
) {

    fun map(transform: (T) -> T): Rect<T> {
        return Rect(
            start = transform(start),
            end = transform(end),
            top = transform(top),
            bottom = transform(bottom)
        )
    }

    fun <U, R> zipSize(size: Size<U>, transform: (T, U) -> R): Rect<R> {
        return Rect(
            start = transform(start, size.width),
            end = transform(end, size.width),
            top = transform(top, size.height),
            bottom = transform(bottom, size.height)
        )
    }
}