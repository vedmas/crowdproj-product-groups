package com.crowdproj.marketplace.product.groups.biz.workers

import com.crowdproj.kotlin.cor.ICorAddExecDsl
import com.crowdproj.kotlin.cor.handlers.worker
import com.crowdproj.marketplace.product.groups.common.ProductGroupContext
import com.crowdproj.marketplace.product.groups.common.models.ProductGroupId
import com.crowdproj.marketplace.product.groups.common.models.ProductGroupState
import com.crowdproj.marketplace.product.groups.common.stubs.ProductGroupStubs
import com.crowdproj.marketplace.product.groups.stubs.ProductGroupStub


fun ICorAddExecDsl<ProductGroupContext>.stubUpdateSuccess(title: String) = worker {
    this.title = title
    on { state == ProductGroupState.RUNNING && stubCase == ProductGroupStubs.SUCCESS }
    handle {
        state = ProductGroupState.FINISHING
        val stub = ProductGroupStub.prepareResult {
            pgRequest.id.takeIf { it != ProductGroupId.NONE }?.also { this.id = it }
            pgRequest.name.takeIf { it.isNotBlank() }?.also { this.name = it }
            pgRequest.description.takeIf { it.isNotBlank() }?.also { this.description = it }
            pgRequest.properties.takeIf { it.isNotBlank() }?.also { this.properties }
        }
        pgResponse = stub
    }
}