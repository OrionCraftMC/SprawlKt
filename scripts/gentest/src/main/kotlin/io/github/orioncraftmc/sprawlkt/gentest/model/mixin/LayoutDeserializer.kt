package io.github.orioncraftmc.sprawlkt.gentest.model.mixin

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.TreeNode
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.node.NumericNode
import io.github.orioncraftmc.sprawlkt.geometry.Point
import io.github.orioncraftmc.sprawlkt.geometry.Size
import io.github.orioncraftmc.sprawlkt.node.Layout

object LayoutDeserializer : JsonDeserializer<Layout>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): Layout {
        val node = p.readValueAsTree<TreeNode>()

        val width = (node["width"] as NumericNode).floatValue()
        val height = (node["height"] as NumericNode).floatValue()
        val x = (node["x"] as NumericNode).floatValue()
        val y = (node["y"] as NumericNode).floatValue()

        return Layout(0u, Size.of(width, height), Point(x, y))
    }

}
