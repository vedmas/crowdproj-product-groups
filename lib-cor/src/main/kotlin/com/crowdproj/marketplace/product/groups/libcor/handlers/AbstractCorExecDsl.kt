package com.crowdproj.marketplace.product.groups.libcor.handlers

import com.crowdproj.marketplace.product.groups.libcor.ICorExecDsl

abstract class AbstractCorExecDsl<T>: ICorExecDsl<T> {
    protected var blockOn: suspend T.() -> Boolean = { true }
    protected var blockExcept: suspend T.(e: Throwable) -> Unit = { e: Throwable -> throw e }

    override var title: String = ""
    override var description: String = ""

    override fun on(function: suspend T.() -> Boolean) {
        blockOn = function
    }

    override fun except(function: suspend T.(e: Throwable) -> Unit) {
        blockExcept = function
    }
}