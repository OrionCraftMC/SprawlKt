package io.github.orioncraftmc.sprawlkt.gentest

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.TypeSpec
import io.github.orioncraftmc.sprawlkt.gentest.model.StretchElement
import java.io.File
import java.util.concurrent.atomic.AtomicInteger

internal fun generateTest(fixture: TestFixture) {
    // First, generate the directory
    generatedTestsDir.mkdirs()

    val testFile = File(generatedTestsDir, "${classNameFromFixture(fixture)}Test.kt")
    testFile.createNewFile()


    val className = "${classNameFromFixture(fixture)}Test"
    val fileSpec = FileSpec.builder(
        "io.github.orioncraftmc.sprawlkt.tests.generated",
        className
    )
        .addImport(
            enumsWildcardPackagePlaceholder, enumsWildcardNamePlaceholder
        )
        .addType(
            TypeSpec.classBuilder(className)
                .addFunction(generateTestFunction(fixture))
                .build()
        ).build()

    testFile.writeText(fileSpec.toString().replace(enumsWildcardPlaceholder, enumsWildcardPlaceholderReplacement))

}

internal fun generateTestFunction(fixture: TestFixture): FunSpec {
    return FunSpec.builder(fixture.name)
        .addAnnotation(ClassName("kotlin.test", "Test"))
        .apply {
            val root = fixture.description

            val rootName = declareNode(root, AtomicInteger(), true)

            addCode("")
            addComment(fixture.rawDescription)
            addCode("")

            addStatement(
                "%T.%M(%L, %T.%M())",
                sprawlType,
                computeLayoutMethod, rootName,
                sizeCompanionType,
                undefinedNumberMethod
            )

            generateAsserts(root)
        }
        .build()
}

internal fun FunSpec.Builder.generateAsserts(node: StretchElement) {
    addStatement(
        "kotlin.test.assertEquals(%L, %T.%M(%L).size.width)", node.layout.size.width.toCode(),
        sprawlType,
        layoutMethod, node.elementName
    )
    addStatement(
        "kotlin.test.assertEquals(%L, %T.%M(%L).size.height)", node.layout.size.height.toCode(),
        sprawlType,
        layoutMethod, node.elementName
    )
    addStatement(
        "kotlin.test.assertEquals(%L, %T.%M(%L).location.x)", node.layout.location.x.toCode(),
        sprawlType,
        layoutMethod, node.elementName
    )
    addStatement(
        "kotlin.test.assertEquals(%L, %T.%M(%L).location.y)", node.layout.location.y.toCode(),
        sprawlType,
        layoutMethod, node.elementName
    )

    node.children.forEach {
        generateAsserts(it)
    }
}

internal fun FunSpec.Builder.declareNode(
    node: StretchElement,
    recursionCount: AtomicInteger,
    rootNode: Boolean
): String {
    val childrenNodeNames = node.children.map {
        this@declareNode.declareNode(it, recursionCount, false)
    }

    val name = if (rootNode) "rootNode" else "node${recursionCount.incrementAndGet()}"
    node.elementName = name

    val styleCode = node.style.toCode()
    addStatement(
        """val %N = %M(
        |    %L,
        |    %L,
        |)""".trimMargin(), name, newNodeMethod, styleCode, "listOf(${childrenNodeNames.joinToString()})"
    )

    return name
}