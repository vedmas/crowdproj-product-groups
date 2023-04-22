package com.crowdproj.marketplace.product.groups.biz.validation

import com.crowdproj.kotlin.cor.ICorAddExecDsl
import com.crowdproj.kotlin.cor.handlers.worker
import com.crowdproj.marketplace.product.groups.common.ProductGroupContext
import com.crowdproj.marketplace.product.groups.common.helpers.errorValidation
import com.crowdproj.marketplace.product.groups.common.helpers.fail

fun ICorAddExecDsl<ProductGroupContext>.validateNameHasContent(title: String) = worker {
    this.title = title
    val regExp = Regex("\\p{L}")
    on { adValidating.name.isNotEmpty() && ! adValidating.name.contains(regExp) }
    handle {
        fail(
            errorValidation(
                field = "name",
                violationCode = "noContent",
                description = "field must contain leters"
            )
        )
    }
}