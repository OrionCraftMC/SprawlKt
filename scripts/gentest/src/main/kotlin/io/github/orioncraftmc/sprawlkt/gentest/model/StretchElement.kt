package io.github.orioncraftmc.sprawlkt.gentest.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.module.kotlin.jacksonMapperBuilder
import com.fasterxml.jackson.module.kotlin.readValue
import io.github.orioncraftmc.sprawlkt.node.Layout
import io.github.orioncraftmc.sprawlkt.style.Style

data class StretchElement(
    val style: Style, val layout: Layout, val children: List<StretchElement>
) {
    @JsonIgnore
    lateinit var elementName: String

    companion object {

        private val mapper = jacksonMapperBuilder().apply {
            enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS)
            addModule(TestModule())
        }.build().apply {
            propertyNamingStrategy = PropertyNamingStrategies.LOWER_CAMEL_CASE
            setSerializationInclusion(JsonInclude.Include.NON_NULL)
        }

        fun fromJson(json: String) = mapper.readValue<StretchElement>(json)
    }
}


