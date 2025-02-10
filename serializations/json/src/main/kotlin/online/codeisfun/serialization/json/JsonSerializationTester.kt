package online.codeisfun.serialization.json

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import online.codeisfun.serialization.commons.AbstractSerializationTester
import java.nio.charset.StandardCharsets

class JsonSerializationTester : AbstractSerializationTester<String>() {
    private val objectMapper = jacksonObjectMapper()

    override fun <JavaObjectDataType> serializeMessage(
        javaObject: JavaObjectDataType,
        clazz: Class<JavaObjectDataType>
    ): String {
        try {
            return objectMapper.writeValueAsString(javaObject)
        } catch (e: Exception) {
            throw RuntimeException("error in serialize data", e)
        }
    }

    override fun <JavaObjectDataType> deserializeMessage(
        msg: String,
        clazz: Class<JavaObjectDataType>
    ): JavaObjectDataType {
        try {
            return objectMapper.readValue(msg, clazz)
        } catch (e: java.lang.Exception) {
            throw RuntimeException("error in deserialize data", e)
        }
    }

    override fun getBytes(msg: String): ByteArray {
        return msg.toByteArray(StandardCharsets.UTF_8)
    }

}