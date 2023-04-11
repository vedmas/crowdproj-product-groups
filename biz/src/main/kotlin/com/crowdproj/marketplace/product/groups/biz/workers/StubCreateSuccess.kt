package com.crowdproj.marketplace.product.groups.biz.workers

import com.crowdproj.marketplace.product.groups.common.ProductGroupContext
import com.crowdproj.marketplace.product.groups.common.models.ProductGroupState
import com.crowdproj.marketplace.product.groups.common.stubs.ProductGroupStubs
import com.crowdproj.marketplace.product.groups.libcor.ICorChainDsl
import com.crowdproj.marketplace.product.groups.libcor.worker
import com.crowdproj.marketplace.product.groups.stubs.ProductGroupStub


fun ICorChainDsl<ProductGroupContext>.stubCreateSuccess(title: String) = worker {
    this.title = title
    on { stubCase == ProductGroupStubs.SUCCESS && state == ProductGroupState.RUNNING }
    handle {
        state = ProductGroupState.FINISHING
        val stub = ProductGroupStub.prepareResult {
            pgRequest.name.takeIf { it.isNotBlank() }?.also { this.name = it }
            pgRequest.description.takeIf { it.isNotBlank() }?.also { this.description = it }
            pgRequest.properties.takeIf { it.isNotBlank() }?.also { this.properties = it }
        }
        pgResponse = stub
    }
}