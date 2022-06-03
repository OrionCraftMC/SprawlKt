package io.github.orioncraftmc.sprawlkt.gentest.model

import com.fasterxml.jackson.core.Version
import com.fasterxml.jackson.databind.BeanDescription
import com.fasterxml.jackson.databind.DeserializationConfig
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.Module
import com.fasterxml.jackson.databind.module.SimpleDeserializers
import com.fasterxml.jackson.databind.util.EnumResolver
import io.github.orioncraftmc.sprawlkt.gentest.model.mixin.*
import io.github.orioncraftmc.sprawlkt.geometry.Rect
import io.github.orioncraftmc.sprawlkt.geometry.Size
import io.github.orioncraftmc.sprawlkt.node.Layout
import io.github.orioncraftmc.sprawlkt.style.enums.StretchDimension

class TestModule : Module() {
    override fun version(): Version {
        return Version.unknownVersion()
    }

    override fun getModuleName(): String {
        return "Test Module"
    }

    override fun setupModule(context: SetupContext) {
        context.addDeserializers(TestDefinitionDeserializers())
    }

    class TestDefinitionDeserializers : SimpleDeserializers() {

        init {
            addDeserializer(StretchDimension::class.java, StretchDimensionDeserializer)
            addDeserializer(Layout::class.java, LayoutDeserializer)
            addDeserializer(Rect::class.java, RectDeserializer)
            addDeserializer(Size::class.java, SizeDeserializer)
        }

        override fun findEnumDeserializer(
            type: Class<*>,
            config: DeserializationConfig?,
            beanDesc: BeanDescription?
        ): JsonDeserializer<*> {
            return MyEnumDeserializer(EnumResolver.constructFor(config, type))
        }
    }
}