package com.crowdproj.marketplace.product.groups.biz

import com.crowdproj.marketplace.product.groups.common.ProductGroupContext
import com.crowdproj.marketplace.product.groups.common.models.*
import com.crowdproj.marketplace.product.groups.common.stubs.ProductGroupStubs
import com.crowdproj.marketplace.product.groups.stubs.ProductGroupStub
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class PgReadStubTest {

    private val processor = ProductGroupProcessor()
    private val id = ProductGroupId("1")

    @Test
    fun read() = runTest {

        val ctx = ProductGroupContext(
            command = ProductGroupCommand.READ,
            state = ProductGroupState.NONE,
            workMode = ProductGroupWorkMode.STUB,
            stubCase = ProductGroupStubs.SUCCESS,
            pgRequest = ProductGroup(
                id = id,
            ),
        )
        processor.exec(ctx)
        with(ProductGroupStub.get()) {
            assertEquals(id, ctx.pgResponse.id)
            assertEquals(name, ctx.pgResponse.name)
            assertEquals(description, ctx.pgResponse.description)
            assertEquals(properties, ctx.pgResponse.properties)
            assertEquals(deleted, ctx.pgResponse.deleted)
        }
    }

    @Test
    fun badId() = runTest {
        val ctx = ProductGroupContext(
            command = ProductGroupCommand.READ,
            state = ProductGroupState.NONE,
            workMode = ProductGroupWorkMode.STUB,
            stubCase = ProductGroupStubs.BAD_ID,
            pgRequest = ProductGroup(),
        )
        processor.exec(ctx)
        assertEquals(ProductGroup(), ctx.pgResponse)
        assertEquals("id", ctx.errors.firstOrNull()?.field)
        assertEquals("validation", ctx.errors.firstOrNull()?.group)
    }

    @Test
    fun databaseError() = runTest {
        val ctx = ProductGroupContext(
            command = ProductGroupCommand.READ,
            state = ProductGroupState.NONE,
            workMode = ProductGroupWorkMode.STUB,
            stubCase = ProductGroupStubs.DB_ERROR,
            pgRequest = ProductGroup(
                id = id,
            ),
        )
        processor.exec(ctx)
        assertEquals(ProductGroup(), ctx.pgResponse)
        assertEquals("internal", ctx.errors.firstOrNull()?.group)
    }

    @Test
    fun badNoCase() = runTest {
        val ctx = ProductGroupContext(
            command = ProductGroupCommand.READ,
            state = ProductGroupState.NONE,
            workMode = ProductGroupWorkMode.STUB,
            stubCase = ProductGroupStubs.BAD_NAME,
            pgRequest = ProductGroup(
                id = id,
            ),
        )
        processor.exec(ctx)
        assertEquals(ProductGroup(), ctx.pgResponse)
        assertEquals("stub", ctx.errors.firstOrNull()?.field)
    }
}