package io.github.orioncraftmc.sprawlkt.gentest

import com.squareup.kotlinpoet.MemberName
import com.squareup.kotlinpoet.asTypeName
import io.github.orioncraftmc.sprawlkt.geometry.Size
import io.github.orioncraftmc.sprawlkt.node.Sprawl
import java.io.File
import java.util.*

internal val sprawlType = Sprawl::class.asTypeName()
internal val computeLayoutMethod = MemberName(sprawlType, "computeLayout")
internal val layoutMethod = MemberName(sprawlType, "layout")
internal val newNodeMethod = MemberName(sprawlType, Sprawl::newNode.name)
internal val sizeCompanionType = Size.Companion::class.asTypeName()
internal val undefinedNumberMethod = MemberName(sizeCompanionType, Size.Companion::undefinedNumber.name)
internal val generatedTestsDir =
    File("src/test/kotlin/io/github/orioncraftmc/sprawlkt/tests/generated".replace("/", File.separator))

internal const val enumsWildcardPackagePlaceholder = "io.github.orioncraftmc.sprawlkt.style.enums"
internal const val enumsWildcardNamePlaceholder = "WildcardPlaceholder"
internal const val enumsWildcardPlaceholder = "$enumsWildcardPackagePlaceholder.$enumsWildcardNamePlaceholder"
internal const val enumsWildcardPlaceholderReplacement = "io.github.orioncraftmc.sprawlkt.style.enums.*"

internal fun classNameFromFixture(fixture: TestFixture): String {
    return fixture.name.split("_")
        .joinToString("") { name -> name.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() } }
}