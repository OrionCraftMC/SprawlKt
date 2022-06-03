package io.github.orioncraftmc.sprawlkt.gentest.model

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue

data class StretchElement (
    val style: ElementStyle,
    val layout: Layout,
    val children: List<StretchElement>
) {
    companion object {

        private val mapper = jacksonObjectMapper().apply {
            propertyNamingStrategy = PropertyNamingStrategies.LOWER_CAMEL_CASE
            setSerializationInclusion(JsonInclude.Include.NON_NULL)
        }

        fun fromJson(json: String) = mapper.readValue<StretchElement>(json)
    }
}


