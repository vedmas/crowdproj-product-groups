package com.crowdproj.marketplace.product.groups.biz

import com.crowdproj.marketplace.product.groups.common.ProductGroupContext
import com.crowdproj.marketplace.product.groups.common.models.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals


@OptIn(ExperimentalCoroutinesApi::class)
fun validationIdCorrect(command: ProductGroupCommand, processor: ProductGroupProcessor) = runTest {
    val ctx = ProductGroupContext(
        command = command,
        state = ProductGroupState.NONE,
        workMode = ProductGroupWorkMode.TEST,
        pgRequest = ProductGroup(
            id = ProductGroupId("123-234-abc-ABC"),
            name = "abc",
            description = "abc",
            properties = "",
            deleted = false
        ),
    )
    processor.exec(ctx)
    assertEquals(0, ctx.errors.size)
    assertNotEquals(ProductGroupState.FAILING, ctx.state)
}

@OptIn(ExperimentalCoroutinesApi::class)
fun validationIdTrim(command: ProductGroupCommand, processor: ProductGroupProcessor) = runTest {
    val ctx = ProductGroupContext(
        command = command,
        state = ProductGroupState.NONE,
        workMode = ProductGroupWorkMode.TEST,
        pgRequest = ProductGroup(
            id = ProductGroupId(" \n\t 123-234-abc-ABC \n\t "),
            name = "abc",
            description = "abc",
            properties = "",
            deleted = false
        ),
    )
    processor.exec(ctx)
    assertEquals(0, ctx.errors.size)
    assertNotEquals(ProductGroupState.FAILING, ctx.state)
}

@OptIn(ExperimentalCoroutinesApi::class)
fun validationIdEmpty(command: ProductGroupCommand, processor: ProductGroupProcessor) = runTest {
    val ctx = ProductGroupContext(
        command = command,
        state = ProductGroupState.NONE,
        workMode = ProductGroupWorkMode.TEST,
        pgRequest = ProductGroup(
            id = ProductGroupId(""),
            name = "abc",
            description = "abc",
            properties = "",
            deleted = false
        ),
    )
    processor.exec(ctx)
    assertEquals(1, ctx.errors.size)
    assertEquals(ProductGroupState.FAILING, ctx.state)
    val error = ctx.errors.firstOrNull()
    assertEquals("id", error?.field)
    assertContains(error?.message ?: "", "id")
}

@OptIn(ExperimentalCoroutinesApi::class)
fun validationIdFormat(command: ProductGroupCommand, processor: ProductGroupProcessor) = runTest {
    val ctx = ProductGroupContext(
        command = command,
        state = ProductGroupState.NONE,
        workMode = ProductGroupWorkMode.TEST,
        pgRequest = ProductGroup(
            id = ProductGroupId("!@#\$%^&*(),.{}"),
            name = "abc",
            description = "abc",
            properties = "",
            deleted = false
        ),
    )
    processor.exec(ctx)
    assertEquals(1, ctx.errors.size)
    assertEquals(ProductGroupState.FAILING, ctx.state)
    val error = ctx.errors.firstOrNull()
    assertEquals("id", error?.field)
    assertContains(error?.message ?: "", "id")
}