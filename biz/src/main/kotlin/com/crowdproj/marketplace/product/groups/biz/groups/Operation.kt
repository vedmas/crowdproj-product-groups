package com.crowdproj.marketplace.product.groups.biz.groups

import com.crowdproj.marketplace.product.groups.common.ProductGroupContext
import com.crowdproj.marketplace.product.groups.common.models.ProductGroupCommand
import com.crowdproj.marketplace.product.groups.common.models.ProductGroupState
import com.crowdproj.marketplace.product.groups.libcor.ICorChainDsl
import com.crowdproj.marketplace.product.groups.libcor.chain

fun ICorChainDsl<ProductGroupContext>.operation(title: String, command: ProductGroupCommand, block: ICorChainDsl<ProductGroupContext>.() -> Unit) = chain {
    block()
    this.title = title
    on { this.command == command && state == ProductGroupState.RUNNING }
}