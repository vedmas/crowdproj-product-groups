package com.crowdproj.marketplace.product.groups.common.helpers

import com.crowdproj.marketplace.product.groups.common.ProductGroupContext
import com.crowdproj.marketplace.product.groups.common.models.ProductGroupError
import com.crowdproj.marketplace.product.groups.common.models.ProductGroupState

fun ProductGroupContext.addError(vararg error: ProductGroupError) = errors.addAll(error)

fun ProductGroupContext.fail(error: ProductGroupError) {
    addError(error)
    state = ProductGroupState.FAILING
}