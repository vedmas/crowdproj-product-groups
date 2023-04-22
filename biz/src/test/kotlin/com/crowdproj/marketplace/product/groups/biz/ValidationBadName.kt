package com.crowdproj.marketplace.product.groups.biz

import com.crowdproj.marketplace.product.groups.common.ProductGroupContext
import com.crowdproj.marketplace.product.groups.common.models.*
import com.crowdproj.marketplace.product.groups.stubs.ProductGroupStub
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

@OptIn(ExperimentalCoroutinesApi::class)
fun validationNameCorrect(command: ProductGroupCommand, processor: ProductGroupProcessor) = runTest {
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
    assertEquals("abc", ctx.adValidated.name)
}

@OptIn(ExperimentalCoroutinesApi::class)
fun validationNameTrim(command: ProductGroupCommand, processor: ProductGroupProcessor) = runTest {
    val ctx = ProductGroupContext(
        command = command,
        state = ProductGroupState.NONE,
        workMode = ProductGroupWorkMode.TEST,
        pgRequest = ProductGroup(
            id = stub.id,
            name = " \n\t abc \t\n ",
            description = "abc",
            properties = "",
            deleted = false
        ),
    )
    processor.exec(ctx)
    assertEquals(0, ctx.errors.size)
    assertNotEquals(ProductGroupState.FAILING, ctx.state)
    assertEquals("abc", ctx.adValidated.name)
}
private val stub = ProductGroupStub.get()
@OptIn(ExperimentalCoroutinesApi::class)
fun validationNameEmpty(command: ProductGroupCommand, processor: ProductGroupProcessor) = runTest {
    val ctx = ProductGroupContext(
        command = command,
        state = ProductGroupState.NONE,
        workMode = ProductGroupWorkMode.TEST,
        pgRequest = ProductGroup(
            id = stub.id,
            name = "",
            description = "abc",
            properties = "",
            deleted = false
        ),
    )
    processor.exec(ctx)
    assertEquals(1, ctx.errors.size)
    assertEquals(ProductGroupState.FAILING, ctx.state)
    val error = ctx.errors.firstOrNull()
    assertEquals("name", error?.field)
    assertContains(error?.message ?: "", "name")
}

@OptIn(ExperimentalCoroutinesApi::class)
fun validationNameSymbols(command: ProductGroupCommand, processor: ProductGroupProcessor) = runTest {
    val ctx = ProductGroupContext(
        command = command,
        state = ProductGroupState.NONE,
        workMode = ProductGroupWorkMode.TEST,
        pgRequest = ProductGroup(
            id = ProductGroupId("123"),
            name = "!@#$%^&*(),.{}",
            description = "abc",
            properties = "",
            deleted = false
        ),
    )
    processor.exec(ctx)
    assertEquals(1, ctx.errors.size)
    assertEquals(ProductGroupState.FAILING, ctx.state)
    val error = ctx.errors.firstOrNull()
    assertEquals("name", error?.field)
    assertContains(error?.message ?: "", "name")
}