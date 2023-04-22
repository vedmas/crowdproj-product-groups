package com.crowdproj.marketplace.product.groups.biz.stub

import com.crowdproj.marketplace.product.groups.biz.ProductGroupProcessor
import com.crowdproj.marketplace.product.groups.common.ProductGroupContext
import com.crowdproj.marketplace.product.groups.common.models.*
import com.crowdproj.marketplace.product.groups.common.stubs.ProductGroupStubs
import com.crowdproj.marketplace.product.groups.stubs.ProductGroupStub
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.fail

@OptIn(ExperimentalCoroutinesApi::class)
class PgSearchStubTest {

    private val processor = ProductGroupProcessor()
    private val filter = ProductGroupFilter(name = "pg-7-01 pg-7-01", description = "desc pg-7-01 pg-7-01")

    @Test
    fun read() = runTest {

        val ctx = ProductGroupContext(
            command = ProductGroupCommand.SEARCH,
            state = ProductGroupState.NONE,
            workMode = ProductGroupWorkMode.STUB,
            stubCase = ProductGroupStubs.SUCCESS,
            pgFilterRequest = filter,
        )
        processor.exec(ctx)
        assertTrue(ctx.pgsResponse.size > 1)
        val first = ctx.pgsResponse.firstOrNull() ?: fail("Empty response list")
        assertTrue(first.name.contains(filter.name))
        assertTrue(first.description.contains(filter.description))
        with(ProductGroupStub.get()) {
            assertEquals(properties, first.properties)
            assertEquals(deleted, first.deleted)
        }
    }

    @Test
    fun badId() = runTest {
        val ctx = ProductGroupContext(
            command = ProductGroupCommand.SEARCH,
            state = ProductGroupState.NONE,
            workMode = ProductGroupWorkMode.STUB,
            stubCase = ProductGroupStubs.BAD_ID,
            pgFilterRequest = filter,
        )
        processor.exec(ctx)
        assertEquals(ProductGroup(), ctx.pgResponse)
        assertEquals("id", ctx.errors.firstOrNull()?.field)
        assertEquals("validation", ctx.errors.firstOrNull()?.group)
    }

    @Test
    fun databaseError() = runTest {
        val ctx = ProductGroupContext(
            command = ProductGroupCommand.SEARCH,
            state = ProductGroupState.NONE,
            workMode = ProductGroupWorkMode.STUB,
            stubCase = ProductGroupStubs.DB_ERROR,
            pgFilterRequest = filter,
        )
        processor.exec(ctx)
        assertEquals(ProductGroup(), ctx.pgResponse)
        assertEquals("internal", ctx.errors.firstOrNull()?.group)
    }

    @Test
    fun badNoCase() = runTest {
        val ctx = ProductGroupContext(
            command = ProductGroupCommand.SEARCH,
            state = ProductGroupState.NONE,
            workMode = ProductGroupWorkMode.STUB,
            stubCase = ProductGroupStubs.BAD_NAME,
            pgFilterRequest = filter,
        )
        processor.exec(ctx)
        assertEquals(ProductGroup(), ctx.pgResponse)
        assertEquals("stub", ctx.errors.firstOrNull()?.field)
    }
}