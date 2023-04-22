package com.crowdproj.marketplace.product.groups.biz

import com.crowdproj.marketplace.product.groups.common.models.ProductGroupCommand
import org.junit.Test

class BizValidationCreateTest {
    private val command = ProductGroupCommand.CREATE
    private val processor by lazy { ProductGroupProcessor() }

    @Test
    fun correctName() = validationNameCorrect(command, processor)
    @Test fun trimTitle() = validationNameTrim(command, processor)
    @Test fun emptyTitle() = validationNameEmpty(command, processor)
    @Test fun badSymbolsTitle() = validationNameSymbols(command, processor)

    @Test fun correctDescription() = validationDescriptionCorrect(command, processor)
    @Test fun trimDescription() = validationDescriptionTrim(command, processor)
    @Test fun emptyDescription() = validationDescriptionEmpty(command, processor)
    @Test fun badSymbolsDescription() = validationDescriptionSymbols(command, processor)
}