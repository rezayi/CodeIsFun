package online.codeisfun.serialization.proto

import online.codeisfun.serialization.commons.AbstractSerializationTester
import online.codeisfun.serialization.commons.HtmlObjectContainer
import online.codeisfun.serialization.commons.Order
import online.codeisfun.serialization.proto.proto_models.HtmlContainerProto
import online.codeisfun.serialization.proto.proto_models.OrderProto

class ProtoSerializationTester : AbstractSerializationTester<ByteArray>() {

    override fun <JavaObjectDataType> serializeMessage(
        javaObject: JavaObjectDataType,
        clazz: Class<JavaObjectDataType>
    ): ByteArray {
        return when (clazz) {
            Order::class.java -> (javaObject as Order).toProto().toByteArray()
            HtmlObjectContainer::class.java -> (javaObject as HtmlObjectContainer).toProto().toByteArray()
            else -> throw IllegalArgumentException("${clazz.simpleName} is not supported")
        }
    }

    override fun <JavaObjectDataType> deserializeMessage(
        msg: ByteArray,
        clazz: Class<JavaObjectDataType>
    ): JavaObjectDataType {
        return when (clazz) {
            Order::class.java -> fromProto(OrderProto.Order.parseFrom(msg)) as JavaObjectDataType
            HtmlObjectContainer::class.java -> fromProto(HtmlContainerProto.HtmlContainer.parseFrom(msg)) as JavaObjectDataType
            else -> throw IllegalArgumentException("${clazz.simpleName} is not supported")
        }
    }

    override fun getBytes(msg: ByteArray): ByteArray {
        return msg
    }
}