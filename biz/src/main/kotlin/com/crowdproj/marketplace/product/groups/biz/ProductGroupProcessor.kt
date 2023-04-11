package com.crowdproj.marketplace.product.groups.biz

import com.crowdproj.marketplace.product.groups.biz.groups.operation
import com.crowdproj.marketplace.product.groups.biz.groups.stubs
import com.crowdproj.marketplace.product.groups.biz.workers.*
import com.crowdproj.marketplace.product.groups.common.ProductGroupContext
import com.crowdproj.marketplace.product.groups.common.models.ProductGroupCommand
import com.crowdproj.marketplace.product.groups.libcor.rootChain
import stubDeleteSuccess

class ProductGroupProcessor {

    suspend fun exec(ctx: ProductGroupContext) = businessChain.exec(ctx)

    companion object {
        private val businessChain = rootChain<ProductGroupContext> {
            initStatus("Инициализация статуса")

            operation("Создание объявления", ProductGroupCommand.CREATE) {
                stubs("Обработка стабов") {
                    stubCreateSuccess("Имитация успешной обработки")
                    stubValidationBadName("Имитация ошибки валидации названия")
                    stubValidationBadDescription("Имитация ошибки валидации описания")
                    stubDbError("Имитация ошибки работы с БД")
                    stubNoCase("Ошибка: запрошенный стаб недопустим")
                }
            }
            operation("Получить объявление", ProductGroupCommand.READ) {
                stubs("Обработка стабов") {
                    stubReadSuccess("Имитация успешной обработки")
                    stubValidationBadId("Имитация ошибки валидации id")
                    stubDbError("Имитация ошибки работы с БД")
                    stubNoCase("Ошибка: запрошенный стаб недопустим")
                }
            }
            operation("Изменить объявление", ProductGroupCommand.UPDATE) {
                stubs("Обработка стабов") {
                    stubUpdateSuccess("Имитация успешной обработки")
                    stubValidationBadId("Имитация ошибки валидации id")
                    stubValidationBadName("Имитация ошибки валидации названия")
                    stubValidationBadDescription("Имитация ошибки валидации описания")
                    stubDbError("Имитация ошибки работы с БД")
                    stubNoCase("Ошибка: запрошенный стаб недопустим")
                }
            }
            operation("Удалить объявление", ProductGroupCommand.DELETE) {
                stubs("Обработка стабов") {
                    stubDeleteSuccess("Имитация успешной обработки")
                    stubValidationBadId("Имитация ошибки валидации id")
                    stubDbError("Имитация ошибки работы с БД")
                    stubNoCase("Ошибка: запрошенный стаб недопустим")
                }
            }
            operation("Поиск объявлений", ProductGroupCommand.SEARCH) {
                stubs("Обработка стабов") {
                    stubSearchSuccess("Имитация успешной обработки")
                    stubValidationBadId("Имитация ошибки валидации id")
                    stubDbError("Имитация ошибки работы с БД")
                    stubNoCase("Ошибка: запрошенный стаб недопустим")
                }
            }
        }.build()
    }
}