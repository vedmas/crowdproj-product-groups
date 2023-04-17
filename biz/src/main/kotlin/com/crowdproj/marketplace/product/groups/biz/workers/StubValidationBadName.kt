package com.crowdproj.marketplace.product.groups.biz.workers

import com.crowdproj.kotlin.cor.ICorAddExecDsl
import com.crowdproj.kotlin.cor.handlers.worker
import com.crowdproj.marketplace.product.groups.common.ProductGroupContext
import com.crowdproj.marketplace.product.groups.common.models.ProductGroupError
import com.crowdproj.marketplace.product.groups.common.models.ProductGroupState
import com.crowdproj.marketplace.product.groups.common.stubs.ProductGroupStubs

fun ICorAddExecDsl<ProductGroupContext>.stubValidationBadName(title: String) = worker {
    this.title = title
    on { state == ProductGroupState.RUNNING && stubCase == ProductGroupStubs.BAD_NAME }
    handle {
        this.errors.add(
            ProductGroupError(
                group = "validation",
                code = "validation-title",
                field = "name",
                message = "Wrong title field"
            )
        )
    }
}