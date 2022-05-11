package io.github.orioncraftmc.stretchkt.style.enums

enum class FlexDirection {
    Row,
    Column,
    RowReverse,
    ColumnReverse;

    companion object {
        @JvmStatic
        val default: FlexDirection get() = Row
    }

    fun isRow() = this == Row || this == RowReverse
    fun isColumn() = this == Column || this == ColumnReverse
    fun isReverse() = this == RowReverse || this == ColumnReverse
}