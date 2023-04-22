package com.crowdproj.marketplace.product.groups.biz.validation

import com.crowdproj.kotlin.cor.ICorAddExecDsl
import com.crowdproj.kotlin.cor.handlers.worker
import com.crowdproj.marketplace.product.groups.common.ProductGroupContext
import com.crowdproj.marketplace.product.groups.common.models.ProductGroupState

fun ICorAddExecDsl<ProductGroupContext>.finishPgValidation(title: String) = worker {
    this.title = title
    on { state == ProductGroupState.RUNNING }
    handle {
        adValidated = adValidating
    }
}

fun ICorAddExecDsl<ProductGroupContext>.finishAdFilterValidation(title: String) = worker {
    this.title = title
    on { state == ProductGroupState.RUNNING }
    handle {
        adFilterValidated = adFilterValidating
    }
}