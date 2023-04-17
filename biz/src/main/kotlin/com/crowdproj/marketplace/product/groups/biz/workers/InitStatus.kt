package com.crowdproj.marketplace.product.groups.biz.workers

import com.crowdproj.kotlin.cor.ICorAddExecDsl
import com.crowdproj.kotlin.cor.handlers.worker
import com.crowdproj.marketplace.product.groups.common.ProductGroupContext
import com.crowdproj.marketplace.product.groups.common.models.ProductGroupState

fun ICorAddExecDsl<ProductGroupContext>.initStatus(title: String) = worker {
    this.title = title
    on { state == ProductGroupState.NONE }
    handle { state = ProductGroupState.RUNNING }
}