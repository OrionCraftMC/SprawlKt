package io.github.orioncraftmc.sprawlkt.gentest

import io.github.orioncraftmc.sprawlkt.gentest.model.StretchElement
import java.io.File

data class TestFixture(val name: String, val path: File, var handle: String? = null) {
    var rawDescription: String = ""
        set(value) {
            field = value
            description = StretchElement.fromJson(value)
        }

    lateinit var description: StretchElement

}