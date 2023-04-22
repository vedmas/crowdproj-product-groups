package com.crowdproj.marketplace.product.groups.biz

import com.crowdproj.marketplace.product.groups.common.models.ProductGroupCommand
import org.junit.Test

class BizValidationUpdateTest {
    private val command = ProductGroupCommand.UPDATE
    private val processor by lazy { ProductGroupProcessor() }

    @Test
    fun correctTitle() = validationNameCorrect(command, processor)
    @Test fun trimTitle() = validationNameTrim(command, processor)
    @Test fun emptyTitle() = validationNameEmpty(command, processor)
    @Test fun badSymbolsTitle() = validationNameSymbols(command, processor)

    @Test fun correctDescription() = validationDescriptionCorrect(command, processor)
    @Test fun trimDescription() = validationDescriptionTrim(command, processor)
    @Test fun emptyDescription() = validationDescriptionEmpty(command, processor)
    @Test fun badSymbolsDescription() = validationDescriptionSymbols(command, processor)

    @Test fun correctId() = validationIdCorrect(command, processor)
    @Test fun trimId() = validationIdTrim(command, processor)
    @Test fun emptyId() = validationIdEmpty(command, processor)
    @Test fun badFormatId() = validationIdFormat(command, processor)
}