package com.crowdproj.marketplace.product.groups.libcor.handlers

import com.crowdproj.marketplace.product.groups.libcor.CorDslMarker
import com.crowdproj.marketplace.product.groups.libcor.ICorExec
import com.crowdproj.marketplace.product.groups.libcor.ICorWorkerDsl

@CorDslMarker
class CorWorkerDsl<T> : AbstractCorExecDsl<T>(), ICorWorkerDsl<T> {
    private var blockHandle: suspend T.() -> Unit = {}
    override fun handle(function: suspend T.() -> Unit) {
        blockHandle = function
    }

    override fun build(): ICorExec<T> = CorWorker(
        title = title,
        description = description,
        blockOn = blockOn,
        blockHandle = blockHandle,
        blockExcept = blockExcept
    )
}