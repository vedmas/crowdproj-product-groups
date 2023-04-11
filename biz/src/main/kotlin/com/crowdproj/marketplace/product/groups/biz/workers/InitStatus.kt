package com.crowdproj.marketplace.product.groups.biz.workers

import com.crowdproj.marketplace.product.groups.common.ProductGroupContext
import com.crowdproj.marketplace.product.groups.common.models.ProductGroupState
import com.crowdproj.marketplace.product.groups.libcor.ICorChainDsl
import com.crowdproj.marketplace.product.groups.libcor.worker

fun ICorChainDsl<ProductGroupContext>.initStatus(title: String) = worker {
    this.title = title
    on { state == ProductGroupState.NONE }
    handle { state = ProductGroupState.RUNNING }
}