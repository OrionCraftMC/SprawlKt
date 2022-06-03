package io.github.orioncraftmc.sprawlkt.gentest.model.mixin

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.TreeNode
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.node.ObjectNode
import io.github.orioncraftmc.sprawlkt.geometry.Rect
import io.github.orioncraftmc.sprawlkt.style.enums.StretchDimension

object RectDeserializer : JsonDeserializer<Rect<StretchDimension>>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): Rect<StretchDimension> {
        val node = p.readValueAsTree<TreeNode>()

        val start = node.readNodeAsDimension("start", ctxt)
        val end = node.readNodeAsDimension("end", ctxt)
        val top = node.readNodeAsDimension("top", ctxt)
        val bottom = node.readNodeAsDimension("bottom", ctxt)

        return Rect(start, end, top, bottom)
    }

    private fun TreeNode.readNodeAsDimension(
        info: String,
        ctxt: DeserializationContext
    ) =
        (get(info) as? ObjectNode)?.let { ctxt.readTreeAsValue(it, StretchDimension::class.java) }
            ?: StretchDimension.Undefined
}