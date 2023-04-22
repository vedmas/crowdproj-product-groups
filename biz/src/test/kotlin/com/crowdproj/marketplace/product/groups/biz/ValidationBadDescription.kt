package com.crowdproj.marketplace.product.groups.biz

import com.crowdproj.marketplace.product.groups.common.ProductGroupContext
import com.crowdproj.marketplace.product.groups.common.models.ProductGroup
import com.crowdproj.marketplace.product.groups.common.models.ProductGroupCommand
import com.crowdproj.marketplace.product.groups.common.models.ProductGroupState
import com.crowdproj.marketplace.product.groups.common.models.ProductGroupWorkMode
import com.crowdproj.marketplace.product.groups.stubs.ProductGroupStub
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

private val stub = ProductGroupStub.get()

@OptIn(ExperimentalCoroutinesApi::class)
fun validationDescriptionCorrect(command: ProductGroupCommand, processor: ProductGroupProcessor) = runTest {
    val ctx = ProductGroupContext(
        command = command,
        state = ProductGroupState.NONE,
        workMode = ProductGroupWorkMode.TEST,
        pgRequest = ProductGroup(
            id = stub.id,
            name = "abc",
            description = "abc",
            properties = "",
            deleted = false
        ),
    )
    processor.exec(ctx)
    assertEquals(0, ctx.errors.size)
    assertNotEquals(ProductGroupState.FAILING, ctx.state)
    assertEquals("abc", ctx.adValidated.description)
}

@OptIn(ExperimentalCoroutinesApi::class)
fun validationDescriptionTrim(command: ProductGroupCommand, processor: ProductGroupProcessor) = runTest {
    val ctx = ProductGroupContext(
        command = command,
        state = ProductGroupState.NONE,
        workMode = ProductGroupWorkMode.TEST,
        pgRequest = ProductGroup(
            id = stub.id,
            name = "abc",
            description = " \n\tabc \n\t",
            properties = "",
            deleted = false
        ),
    )
    processor.exec(ctx)
    assertEquals(0, ctx.errors.size)
    assertNotEquals(ProductGroupState.FAILING, ctx.state)
    assertEquals("abc", ctx.adValidated.description)
}

@OptIn(ExperimentalCoroutinesApi::class)
fun validationDescriptionEmpty(command: ProductGroupCommand, processor: ProductGroupProcessor) = runTest {
    val ctx = ProductGroupContext(
        command = command,
        state = ProductGroupState.NONE,
        workMode = ProductGroupWorkMode.TEST,
        pgRequest = ProductGroup(
            id = stub.id,
            name = "abc",
            description = "",
            properties = "",
            deleted = false
        ),
    )
    processor.exec(ctx)
    assertEquals(1, ctx.errors.size)
    assertEquals(ProductGroupState.FAILING, ctx.state)
    val error = ctx.errors.firstOrNull()
    assertEquals("description", error?.field)
    assertContains(error?.message ?: "", "description")
}

@OptIn(ExperimentalCoroutinesApi::class)
fun validationDescriptionSymbols(command: ProductGroupCommand, processor: ProductGroupProcessor) = runTest {
    val ctx = ProductGroupContext(
        command = command,
        state = ProductGroupState.NONE,
        workMode = ProductGroupWorkMode.TEST,
        pgRequest = ProductGroup(
            id = stub.id,
            name = "abc",
            description = "!@#$%^&*(),.{}",
            properties = "",
            deleted = false
        ),
    )
    processor.exec(ctx)
    assertEquals(1, ctx.errors.size)
    assertEquals(ProductGroupState.FAILING, ctx.state)
    val error = ctx.errors.firstOrNull()
    assertEquals("description", error?.field)
    assertContains(error?.message ?: "", "description")
}