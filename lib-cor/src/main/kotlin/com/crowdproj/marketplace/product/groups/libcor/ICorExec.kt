package com.crowdproj.marketplace.product.groups.libcor

/**
 * Блок кода, который обрабатывает контекст. Имеет имя и описание
 */
interface ICorExec<T> {
    val title: String
    val description: String
    suspend fun exec(context: T)
}