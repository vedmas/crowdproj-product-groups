package com.crowdproj.marketplace.product.groups.biz.workers

import com.crowdproj.marketplace.product.groups.common.ProductGroupContext
import com.crowdproj.marketplace.product.groups.common.models.ProductGroupError
import com.crowdproj.marketplace.product.groups.common.models.ProductGroupState
import com.crowdproj.marketplace.product.groups.common.stubs.ProductGroupStubs
import com.crowdproj.marketplace.product.groups.libcor.ICorChainDsl
import com.crowdproj.marketplace.product.groups.libcor.worker

fun ICorChainDsl<ProductGroupContext>.stubValidationBadId(title: String) = worker {
    this.title = title
    on { state == ProductGroupState.RUNNING && stubCase == ProductGroupStubs.BAD_ID }
    handle {
        this.errors.add(
            ProductGroupError(
                group = "validation",
                code = "validation-id",
                field = "id",
                message = "Wrong id field"
            )
        )
    }
}