package io.github.orioncraftmc.sprawlkt.gentest.model.mixin

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.TreeNode
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.node.ObjectNode
import io.github.orioncraftmc.sprawlkt.geometry.Size
import io.github.orioncraftmc.sprawlkt.style.enums.StretchDimension

object SizeDeserializer : JsonDeserializer<Size<StretchDimension>>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): Size<StretchDimension> {
        val node = p.readValueAsTree<TreeNode>()

        val width = node.readNodeAsDimension("width", ctxt)
        val height = node.readNodeAsDimension("height", ctxt)

        return Size(width, height)
    }

    private fun TreeNode.readNodeAsDimension(
        info: String,
        ctxt: DeserializationContext
    ) =
        (get(info) as? ObjectNode)?.let { ctxt.readTreeAsValue(it, StretchDimension::class.java) }
            ?: StretchDimension.Undefined

}