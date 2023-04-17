package com.crowdproj.marketplace.product.groups.biz.groups

import com.crowdproj.kotlin.cor.ICorAddExecDsl
import com.crowdproj.kotlin.cor.handlers.chain
import com.crowdproj.marketplace.product.groups.common.ProductGroupContext
import com.crowdproj.marketplace.product.groups.common.models.ProductGroupCommand
import com.crowdproj.marketplace.product.groups.common.models.ProductGroupState

fun ICorAddExecDsl<ProductGroupContext>.operation(title: String, command: ProductGroupCommand, block: ICorAddExecDsl<ProductGroupContext>.() -> Unit) = chain {
    block()
    this.title = title
    on { this.command == command && state == ProductGroupState.RUNNING }
}