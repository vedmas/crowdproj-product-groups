package com.crowdproj.marketplace.product.groups.biz

import com.crowdproj.marketplace.product.groups.common.ProductGroupContext
import com.crowdproj.marketplace.product.groups.common.models.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class BizValidationSearchTest {
    private val command = ProductGroupCommand.SEARCH
    private val processor by lazy { ProductGroupProcessor() }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun correctEmpty() = runTest {
        val ctx = ProductGroupContext(
            command = command,
            state = ProductGroupState.NONE,
            workMode = ProductGroupWorkMode.TEST,
            pgFilterRequest = ProductGroupFilter()
        )
        processor.exec(ctx)
        assertEquals(0, ctx.errors.size)
        assertNotEquals(ProductGroupState.FAILING, ctx.state)
    }
}