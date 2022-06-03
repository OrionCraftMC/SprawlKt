package io.github.orioncraftmc.sprawlkt.gentest.model.mixin

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.deser.std.EnumDeserializer
import com.fasterxml.jackson.databind.util.EnumResolver

class MyEnumDeserializer(resolver: EnumResolver) : EnumDeserializer(resolver, true) {
    override fun _fromString(p: JsonParser?, ctxt: DeserializationContext?, text: String?): Any {
        return super._fromString(p, ctxt, text?.replace("-", ""))
    }
}