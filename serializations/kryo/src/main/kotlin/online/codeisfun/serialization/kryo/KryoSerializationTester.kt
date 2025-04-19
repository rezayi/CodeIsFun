package online.codeisfun.serialization.kryo

import com.esotericsoftware.kryo.Kryo
import com.esotericsoftware.kryo.io.Input
import com.esotericsoftware.kryo.io.Output
import com.esotericsoftware.kryo.util.DefaultInstantiatorStrategy
import online.codeisfun.serialization.commons.AbstractSerializationTester
import org.objenesis.strategy.StdInstantiatorStrategy
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream

class KryoSerializationTester : AbstractSerializationTester<ByteArray>() {
    private val kryo = Kryo().apply {
        isRegistrationRequired = false
        instantiatorStrategy = DefaultInstantiatorStrategy(StdInstantiatorStrategy())
    }

    override fun <JavaObjectDataType> serializeMessage(
        javaObject: JavaObjectDataType,
        clazz: Class<JavaObjectDataType>
    ): ByteArray {
        val byteArrayOutputStream = ByteArrayOutputStream()
        val output = Output(byteArrayOutputStream)
        kryo.writeObject(output, javaObject)
        output.close()
        return byteArrayOutputStream.toByteArray()
    }

    override fun <JavaObjectDataType> deserializeMessage(
        msg: ByteArray,
        clazz: Class<JavaObjectDataType>
    ): JavaObjectDataType {
        val byteArrayInputStream = ByteArrayInputStream(msg)
        val input = Input(byteArrayInputStream)
        val deserialized = kryo.readObject(input, clazz)
        input.close()
        return deserialized
    }

    override fun getBytes(msg: ByteArray): ByteArray {
        return msg
    }
}