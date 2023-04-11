package com.crowdproj.marketplace.product.groups.biz.workers

import com.crowdproj.marketplace.product.groups.common.ProductGroupContext
import com.crowdproj.marketplace.product.groups.common.helpers.fail
import com.crowdproj.marketplace.product.groups.common.models.ProductGroupError
import com.crowdproj.marketplace.product.groups.common.models.ProductGroupState
import com.crowdproj.marketplace.product.groups.libcor.ICorChainDsl
import com.crowdproj.marketplace.product.groups.libcor.worker

fun ICorChainDsl<ProductGroupContext>.stubNoCase(title: String) = worker {
    this.title = title
    on { state == ProductGroupState.RUNNING }
    handle {
        fail(
            ProductGroupError(
                code = "validation",
                field = "stub",
                group = "validation",
                message = "Wrong stub case is requested: ${stubCase.name}"
            )
        )
    }
}