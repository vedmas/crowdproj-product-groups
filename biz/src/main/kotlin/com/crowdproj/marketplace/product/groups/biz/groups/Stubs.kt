package com.crowdproj.marketplace.product.groups.biz.groups

import com.crowdproj.marketplace.product.groups.common.ProductGroupContext
import com.crowdproj.marketplace.product.groups.common.models.ProductGroupState
import com.crowdproj.marketplace.product.groups.common.models.ProductGroupWorkMode
import com.crowdproj.marketplace.product.groups.libcor.ICorChainDsl
import com.crowdproj.marketplace.product.groups.libcor.chain

fun ICorChainDsl<ProductGroupContext>.stubs(title: String, block: ICorChainDsl<ProductGroupContext>.() -> Unit) = chain {
    block()
    this.title = title
    on { workMode == ProductGroupWorkMode.STUB && state == ProductGroupState.RUNNING }
}