package com.crowdproj.marketplace.product.groups.biz.validation

import com.crowdproj.kotlin.cor.ICorAddExecDsl
import com.crowdproj.kotlin.cor.handlers.chain
import com.crowdproj.marketplace.product.groups.common.ProductGroupContext
import com.crowdproj.marketplace.product.groups.common.models.ProductGroupState

fun ICorAddExecDsl<ProductGroupContext>.validation(block: ICorAddExecDsl<ProductGroupContext>.() -> Unit) = chain {
    block()
    title = "Валидация"

    on { state == ProductGroupState.RUNNING }
}