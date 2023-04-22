package com.crowdproj.marketplace.product.groups.biz

import com.crowdproj.kotlin.cor.handlers.worker
import com.crowdproj.kotlin.cor.rootChain
import com.crowdproj.marketplace.product.groups.biz.groups.operation
import com.crowdproj.marketplace.product.groups.biz.groups.stubs
import com.crowdproj.marketplace.product.groups.biz.validation.*
import com.crowdproj.marketplace.product.groups.biz.workers.*
import com.crowdproj.marketplace.product.groups.common.ProductGroupContext
import com.crowdproj.marketplace.product.groups.common.models.ProductGroupCommand
import com.crowdproj.marketplace.product.groups.common.models.ProductGroupId

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
                validation {
                    worker("Копируем поля в adValidating") { adValidating = pgRequest.deepCopy() }
                    worker("Очистка id") { adValidating.id = ProductGroupId.NONE }
                    worker("Очистка заголовка") { adValidating.name = adValidating.name.trim() }
                    worker("Очистка описания") { adValidating.description = adValidating.description.trim() }
                    validateNameNotEmpty("Проверка, что заголовок не пуст")
                    validateNameHasContent("Проверка символов")
                    validateDescriptionNotEmpty("Проверка, что описание не пусто")
                    validateDescriptionHasContent("Проверка символов")

                    finishPgValidation("Завершение проверок")
                }
            }
            operation("Получить объявление", ProductGroupCommand.READ) {
                stubs("Обработка стабов") {
                    stubReadSuccess("Имитация успешной обработки")
                    stubValidationBadId("Имитация ошибки валидации id")
                    stubDbError("Имитация ошибки работы с БД")
                    stubNoCase("Ошибка: запрошенный стаб недопустим")
                }
                validation {
                    worker("Копируем поля в adValidating") { adValidating = pgRequest.deepCopy() }
                    worker("Очистка id") { adValidating.id = ProductGroupId(adValidating.id.asString().trim()) }
                    validateIdNotEmpty("Проверка на непустой id")
                    validateIdProperFormat("Проверка формата id")

                    finishPgValidation("Успешное завершение процедуры валидации")
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
                validation {
                    worker("Копируем поля в adValidating") { adValidating = pgRequest.deepCopy() }
                    worker("Очистка id") { adValidating.id = ProductGroupId(adValidating.id.asString().trim()) }
                    worker("Очистка заголовка") { adValidating.name = adValidating.name.trim() }
                    worker("Очистка описания") { adValidating.description = adValidating.description.trim() }
                    validateIdNotEmpty("Проверка на непустой id")
                    validateIdProperFormat("Проверка формата id")
                    validateNameNotEmpty("Проверка на непустой заголовок")
                    validateNameHasContent("Проверка на наличие содержания в заголовке")
                    validateDescriptionNotEmpty("Проверка на непустое описание")
                    validateDescriptionHasContent("Проверка на наличие содержания в описании")

                    finishPgValidation("Успешное завершение процедуры валидации")
                }
            }
            operation("Удалить объявление", ProductGroupCommand.DELETE) {
                stubs("Обработка стабов") {
                    stubDeleteSuccess("Имитация успешной обработки")
                    stubValidationBadId("Имитация ошибки валидации id")
                    stubDbError("Имитация ошибки работы с БД")
                    stubNoCase("Ошибка: запрошенный стаб недопустим")
                }
                validation {
                    worker("Копируем поля в adValidating") {
                        adValidating = pgRequest.deepCopy() }
                    worker("Очистка id") { adValidating.id = ProductGroupId(adValidating.id.asString().trim()) }
                    validateIdNotEmpty("Проверка на непустой id")
                    validateIdProperFormat("Проверка формата id")
                    finishPgValidation("Успешное завершение процедуры валидации")
                }
            }
            operation("Поиск объявлений", ProductGroupCommand.SEARCH) {
                stubs("Обработка стабов") {
                    stubSearchSuccess("Имитация успешной обработки")
                    stubValidationBadId("Имитация ошибки валидации id")
                    stubDbError("Имитация ошибки работы с БД")
                    stubNoCase("Ошибка: запрошенный стаб недопустим")
                }
                validation {
                    worker("Копируем поля в adFilterValidating") { adFilterValidating = pgFilterRequest.copy() }

                    finishAdFilterValidation("Успешное завершение процедуры валидации")
                }
            }
        }.build()
    }
}