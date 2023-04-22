package com.crowdproj.marketplace.product.groups.biz

import com.crowdproj.marketplace.product.groups.common.models.ProductGroupCommand
import org.junit.Test

class BizValidationDeleteTest {
    private val command = ProductGroupCommand.DELETE
    private val processor by lazy { ProductGroupProcessor() }

    @Test
    fun correctId() = validationIdCorrect(command, processor)
    @Test fun trimId() = validationIdTrim(command, processor)
    @Test fun emptyId() = validationIdEmpty(command, processor)
    @Test fun badFormatId() = validationIdFormat(command, processor)
}