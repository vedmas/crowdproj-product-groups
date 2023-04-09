package com.crowdproj.marketplace.product.groups.libcor.handlers

import AbstractCorExec

class CorWorker<T>(
    title: String,
    description: String = "",
    blockOn: suspend T.() -> Boolean = { true },
    private val blockHandle: suspend T.() -> Unit = {},
    blockExcept: suspend T.(Throwable) -> Unit = {}
) : AbstractCorExec<T>(title, description, blockOn, blockExcept) {
    override suspend fun handle(context: T) = blockHandle(context)
}