package com.crowdproj.marketplace.product.groups.common.helpers

import com.crowdproj.marketplace.product.groups.common.ProductGroupContext
import com.crowdproj.marketplace.product.groups.common.models.ProductGroupError
import com.crowdproj.marketplace.product.groups.common.models.ProductGroupState

fun ProductGroupContext.addError(vararg error: ProductGroupError) = errors.addAll(error)

fun ProductGroupContext.fail(error: ProductGroupError) {
    addError(error)
    state = ProductGroupState.FAILING
}

fun errorValidation(
    field: String,
    /**
     * Код, характеризующий ошибку. Не должен включать имя поля или указание на валидацию.
     * Например: empty, badSymbols, tooLong, etc
     */
    violationCode: String,
    description: String,
    level: ProductGroupError.Level = ProductGroupError.Level.ERROR,
) = ProductGroupError(
    code = "validation-$field-$violationCode",
    field = field,
    group = "validation",
    message = "Validation error for field $field: $description",
    level = level,
)