package com.crowdproj.marketplace.product.groups.biz.validation

import com.crowdproj.kotlin.cor.ICorAddExecDsl
import com.crowdproj.kotlin.cor.handlers.worker
import com.crowdproj.marketplace.product.groups.common.ProductGroupContext
import com.crowdproj.marketplace.product.groups.common.helpers.errorValidation
import com.crowdproj.marketplace.product.groups.common.helpers.fail
import com.crowdproj.marketplace.product.groups.common.models.ProductGroupId

fun ICorAddExecDsl<ProductGroupContext>.validateIdProperFormat(title: String) = worker {
    this.title = title

    // Может быть вынесен в ProductGroupId для реализации различных форматов
    val regExp = Regex("^[0-9a-zA-Z-]+$")
    on { adValidating.id != ProductGroupId.NONE && ! adValidating.id.asString().matches(regExp) }
    handle {
        val encodedId = adValidating.id.asString()
            .replace("<", "&lt;")
            .replace(">", "&gt;")
        fail(
            errorValidation(
                field = "id",
                violationCode = "badFormat",
                description = "value $encodedId must contain only letters and numbers"
            )
        )
    }
}