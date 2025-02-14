package online.codeisfun.serialization.avro

import online.codeisfun.serialization.commons.AbstractSerializationTester
import online.codeisfun.serialization.commons.HtmlObjectContainer
import online.codeisfun.serialization.commons.Order
import org.apache.avro.io.DecoderFactory
import org.apache.avro.io.EncoderFactory
import org.apache.avro.specific.SpecificDatumReader
import org.apache.avro.specific.SpecificDatumWriter
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream

class AvroSerializationTester : AbstractSerializationTester<ByteArray>() {
    override fun <JavaObjectDataType> serializeMessage(
        javaObject: JavaObjectDataType,
        clazz: Class<JavaObjectDataType>
    ): ByteArray {
        return ByteArrayOutputStream().use { outputStream ->
            val encoder = EncoderFactory.get().binaryEncoder(outputStream, null)
            when (clazz) {
                online.codeisfun.serialization.commons.Order::class.java -> {
                    val writer = SpecificDatumWriter(online.codeisfun.serialization.avro.Order::class.java)
                    writer.write((javaObject as Order).toAvro(), encoder)
                }

                online.codeisfun.serialization.commons.HtmlObjectContainer::class.java -> {
                    val writer =
                        SpecificDatumWriter(online.codeisfun.serialization.avro.HtmlObjectContainer::class.java)
                    writer.write((javaObject as HtmlObjectContainer).toAvro(), encoder)
                }

                else -> throw Exception("${clazz.simpleName} is not supported")
            }
            encoder.flush()
            outputStream.toByteArray()
        }
    }

    override fun <JavaObjectDataType> deserializeMessage(
        msg: ByteArray,
        clazz: Class<JavaObjectDataType>
    ): JavaObjectDataType {
        return ByteArrayInputStream(msg).use { inputStream ->
            val decoder = DecoderFactory.get().binaryDecoder(inputStream, null)
            when (clazz) {
                online.codeisfun.serialization.commons.Order::class.java -> {
                    val reader = SpecificDatumReader(online.codeisfun.serialization.avro.Order::class.java)
                    fromAvro(reader.read(null, decoder)) as JavaObjectDataType
                }

                online.codeisfun.serialization.commons.HtmlObjectContainer::class.java -> {
                    val reader = SpecificDatumReader(online.codeisfun.serialization.avro.HtmlObjectContainer::class.java)
                    fromAvro(reader.read(null, decoder)) as JavaObjectDataType
                }

                else -> throw Exception("${clazz.simpleName} is not supported")
            }
        }
    }

    override fun getBytes(msg: ByteArray): ByteArray {
        return msg
    }
}