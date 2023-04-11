package com.crowdproj.marketplace.product.groups.biz

import com.crowdproj.marketplace.product.groups.common.ProductGroupContext
import com.crowdproj.marketplace.product.groups.common.models.*
import com.crowdproj.marketplace.product.groups.common.stubs.ProductGroupStubs
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class PgUpdateStubTest {

    private val processor = ProductGroupProcessor()
    private val id = ProductGroupId("777")
    private val name = "name 123"
    private val description = "desc 321"
    private val properties = "Prop!"
    private val deleted = false

    @Test
    fun create() = runTest {

        val ctx = ProductGroupContext(
            command = ProductGroupCommand.UPDATE,
            state = ProductGroupState.NONE,
            workMode = ProductGroupWorkMode.STUB,
            stubCase = ProductGroupStubs.SUCCESS,
            pgRequest = ProductGroup(
                id = id,
                name = name,
                description = description,
                properties = properties,
                deleted = deleted,
            ),
        )
        processor.exec(ctx)
        assertEquals(id, ctx.pgResponse.id)
        assertEquals(name, ctx.pgResponse.name)
        assertEquals(description, ctx.pgResponse.description)
        assertEquals(properties, ctx.pgResponse.properties)
        assertEquals(deleted, ctx.pgResponse.deleted)
    }

    @Test
    fun badId() = runTest {
        val ctx = ProductGroupContext(
            command = ProductGroupCommand.UPDATE,
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
    fun badTitle() = runTest {
        val ctx = ProductGroupContext(
            command = ProductGroupCommand.UPDATE,
            state = ProductGroupState.NONE,
            workMode = ProductGroupWorkMode.STUB,
            stubCase = ProductGroupStubs.BAD_NAME,
            pgRequest = ProductGroup(
                id = id,
                name = "",
                description = description,
                properties = properties,
                deleted = deleted,
            ),
        )
        processor.exec(ctx)
        assertEquals(ProductGroup(), ctx.pgResponse)
        assertEquals("name", ctx.errors.firstOrNull()?.field)
        assertEquals("validation", ctx.errors.firstOrNull()?.group)
    }

    @Test
    fun badDescription() = runTest {
        val ctx = ProductGroupContext(
            command = ProductGroupCommand.UPDATE,
            state = ProductGroupState.NONE,
            workMode = ProductGroupWorkMode.STUB,
            stubCase = ProductGroupStubs.BAD_DESCRIPTION,
            pgRequest = ProductGroup(
                id = id,
                name = name,
                description = "",
                properties = properties,
                deleted = deleted,
            ),
        )
        processor.exec(ctx)
        assertEquals(ProductGroup(), ctx.pgResponse)
        assertEquals("description", ctx.errors.firstOrNull()?.field)
        assertEquals("validation", ctx.errors.firstOrNull()?.group)
    }

    @Test
    fun databaseError() = runTest {
        val ctx = ProductGroupContext(
            command = ProductGroupCommand.UPDATE,
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
            command = ProductGroupCommand.UPDATE,
            state = ProductGroupState.NONE,
            workMode = ProductGroupWorkMode.STUB,
            stubCase = ProductGroupStubs.BAD_SEARCH_STRING,
            pgRequest = ProductGroup(
                id = id,
                name = name,
                description = description,
                properties = properties,
                deleted = deleted,
            ),
        )
        processor.exec(ctx)
        assertEquals(ProductGroup(), ctx.pgResponse)
        assertEquals("stub", ctx.errors.firstOrNull()?.field)
        assertEquals("validation", ctx.errors.firstOrNull()?.group)
    }
}