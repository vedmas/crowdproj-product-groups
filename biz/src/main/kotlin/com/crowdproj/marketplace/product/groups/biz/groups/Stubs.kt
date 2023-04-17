package com.crowdproj.marketplace.product.groups.biz.groups

import com.crowdproj.kotlin.cor.ICorAddExecDsl
import com.crowdproj.kotlin.cor.handlers.chain
import com.crowdproj.marketplace.product.groups.common.ProductGroupContext
import com.crowdproj.marketplace.product.groups.common.models.ProductGroupState
import com.crowdproj.marketplace.product.groups.common.models.ProductGroupWorkMode

fun ICorAddExecDsl<ProductGroupContext>.stubs(title: String, block: ICorAddExecDsl<ProductGroupContext>.() -> Unit) = chain {
    block()
    this.title = title
    on { workMode == ProductGroupWorkMode.STUB && state == ProductGroupState.RUNNING }
}