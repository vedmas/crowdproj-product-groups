package com.crowdproj.marketplace.product.groups.biz.workers

import com.crowdproj.kotlin.cor.ICorAddExecDsl
import com.crowdproj.kotlin.cor.handlers.worker
import com.crowdproj.marketplace.product.groups.common.ProductGroupContext
import com.crowdproj.marketplace.product.groups.common.models.ProductGroupState
import com.crowdproj.marketplace.product.groups.common.stubs.ProductGroupStubs
import com.crowdproj.marketplace.product.groups.stubs.ProductGroupStub

fun ICorAddExecDsl<ProductGroupContext>.stubSearchSuccess(title: String) = worker {
    this.title = title
    on { state == ProductGroupState.RUNNING && stubCase == ProductGroupStubs.SUCCESS }
    handle {
        state = ProductGroupState.FINISHING
        pgsResponse.addAll(ProductGroupStub.prepareSearchList("pg-7-01"))
    }
}