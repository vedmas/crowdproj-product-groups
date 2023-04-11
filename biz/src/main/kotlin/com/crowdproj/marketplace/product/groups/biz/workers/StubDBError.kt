package com.crowdproj.marketplace.product.groups.biz.workers

import com.crowdproj.marketplace.product.groups.common.ProductGroupContext
import com.crowdproj.marketplace.product.groups.common.models.ProductGroupError
import com.crowdproj.marketplace.product.groups.common.models.ProductGroupState
import com.crowdproj.marketplace.product.groups.common.stubs.ProductGroupStubs
import com.crowdproj.marketplace.product.groups.libcor.ICorChainDsl
import com.crowdproj.marketplace.product.groups.libcor.worker

fun ICorChainDsl<ProductGroupContext>.stubDbError(title: String) = worker {
    this.title = title
    on { state == ProductGroupState.RUNNING && stubCase == ProductGroupStubs.DB_ERROR }
    handle {
        this.errors.add(
            ProductGroupError(
                group = "internal",
                code = "internal-db",
                message = "Internal error"
            )
        )
    }
}