package com.crowdproj.marketplace.product.groups.app.ktor

import com.crowdproj.marketplace.product.groups.biz.ProductGroupProcessor
import com.crowdproj.marketplace.product.groups.common.ProductGroupContext


private val processor = ProductGroupProcessor()
suspend fun process(ctx: ProductGroupContext) = processor.exec(ctx)
