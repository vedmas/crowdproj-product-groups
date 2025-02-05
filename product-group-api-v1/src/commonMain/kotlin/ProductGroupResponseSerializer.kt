package com.crowdproj.marketplace.product.group.api.v1

import com.crowdproj.marketplace.product.group.api.v1.models.IProductGroupResponse
import com.crowdproj.marketplace.product.group.api.v1.response.IResponseStrategy
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive


val ProductGroupResponseSerializer = ResponseSerializer(ProductGroupResponseSerializerBase)

private object ProductGroupResponseSerializerBase : JsonContentPolymorphicSerializer<IProductGroupResponse>(IProductGroupResponse::class) {
    private const val discriminator = "requestType"

    override fun selectDeserializer(element: JsonElement): KSerializer<out IProductGroupResponse> {

        val discriminatorValue = element.jsonObject[discriminator]?.jsonPrimitive?.content
        return IResponseStrategy.membersByDiscriminator[discriminatorValue]?.serializer
            ?: throw SerializationException(
                "Unknown value '${discriminatorValue}' in discriminator '$discriminator' " +
                        "property of ${IProductGroupResponse::class} implementation"
            )
    }
}

class ResponseSerializer<T : IProductGroupResponse>(private val serializer: KSerializer<T>) : KSerializer<T> by serializer {
    override fun serialize(encoder: Encoder, value: T) =
        IResponseStrategy
            .membersByClazz[value::class]
            ?.fillDiscriminator(value)
            ?.let { serializer.serialize(encoder, it) }
            ?: throw SerializationException(
                "Unknown class to serialize as IResponse instance in ResponseSerializer"
            )
}
