package io.github.orioncraftmc.sprawlkt.gentest.model.mixin

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.TreeNode
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.node.NumericNode
import com.fasterxml.jackson.databind.node.TextNode
import io.github.orioncraftmc.sprawlkt.style.enums.StretchDimension

object StretchDimensionDeserializer : JsonDeserializer<StretchDimension>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): StretchDimension {
        val tree = p.readValueAsTree<TreeNode>()

        val unit = (tree["unit"] as? TextNode)?.textValue()
        val value = (tree["value"] as? NumericNode)?.floatValue()

        return when (unit) {
            "Points" -> StretchDimension.Points(value!!)
            "Percent" -> StretchDimension.Percent(value!!)
            "Auto" -> StretchDimension.Auto
            "Undefined" -> StretchDimension.Undefined
            else -> throw IllegalArgumentException("Unknown unit: $unit")
        }
    }

}
