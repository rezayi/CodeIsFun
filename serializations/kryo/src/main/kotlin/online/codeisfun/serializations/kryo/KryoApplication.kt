package online.codeisfun.serializations.kryo

import com.esotericsoftware.kryo.Kryo
import com.esotericsoftware.kryo.io.Input
import com.esotericsoftware.kryo.io.Output
import com.esotericsoftware.kryo.util.DefaultInstantiatorStrategy
import org.objenesis.strategy.StdInstantiatorStrategy
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream

fun main() {
    val kryo = Kryo().apply {
        isRegistrationRequired = false
        instantiatorStrategy = DefaultInstantiatorStrategy(StdInstantiatorStrategy())
        register(Gender::class.java)
        register(School::class.java)
        register(Student::class.java)
        register(Teacher::class.java)
    }
    val teachers = mutableMapOf<String, Teacher>()
    repeat(10) { index ->
        teachers["index $index"] = Teacher(
            name = "John Smith $index",
            age = 23,
            gender = Gender.FEMALE,
            father = null,
            mother = null,
        )
    }

    var total = 0L
    repeat(1_000_000) { index ->
        val startTime = System.nanoTime()
        val person = Student(
            name = "John Smith $index",
            age = 23,
            gender = Gender.MALE,
            father = null,
            mother = null,
            level = 1,
            school = School("my school"),
            teachers = teachers
        )
        val bytes = serialize(kryo, person)
        val deserialize = deserialize(kryo, bytes)

        val tookTime = System.nanoTime() - startTime
        total += tookTime
    }
    System.err.println("Kryo Application took ${total / 1_000_000} ns")
}

private fun serialize(
    kryo: Kryo,
    person: Student
): ByteArray {
    val byteArrayOutputStream = ByteArrayOutputStream()
    val output = Output(byteArrayOutputStream)
    kryo.writeObject(output, person)
    output.close()
    return byteArrayOutputStream.toByteArray()
}

private fun deserialize(
    kryo: Kryo,
    bytes: ByteArray
): Student {
    val input = Input(ByteArrayInputStream(bytes))
    val deserializedPerson = kryo.readObject(input, Student::class.java)
    input.close()
    return deserializedPerson
}
