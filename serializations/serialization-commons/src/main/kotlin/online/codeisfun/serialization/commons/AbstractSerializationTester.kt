package online.codeisfun.serialization.commons

import java.lang.System.nanoTime


abstract class AbstractSerializationTester<SerializedDataType> {

    fun runTest(){
        repeat(6) { level ->
            val (time, size) = testSerialization(1_000_000) { index ->
                ObjectInitializer.createOrder(index, level) to Order::class.java
            }
            println("order process finished. level: $level , avg sample size: $size , avg time: $time ns")
        }

        val (time, size) = testSerialization(1_000) { index ->
            ObjectInitializer.createHtmlObject() to HtmlObjectContainer::class.java
        }
        println("html process finished. avg sample size: $size , avg time: $time ns")
    }

    private fun <T> testSerialization(
        testCount: Int,
        testItemInitializer: (Int) -> Pair<T, Class<T>>
    ): Pair<Long, Long> {
        var totalTime = 0L
        var totalBytes = 0L
        repeat(testCount) { index ->
            val (testItem, clazz) = testItemInitializer(index)
            val start = nanoTime()

            val serialized = serializeMessage(testItem, clazz)
            deserializeMessage(serialized, clazz)

            val end = nanoTime()
            totalTime += end - start
            val bytes = getBytes(serialized)
            totalBytes += bytes.size
        }
        return (totalTime / testCount) to (totalBytes / testCount)
    }

    protected abstract fun <JavaObjectDataType> serializeMessage(
        javaObject: JavaObjectDataType,
        clazz: Class<JavaObjectDataType>
    ): SerializedDataType

    protected abstract fun <JavaObjectDataType> deserializeMessage(
        msg: SerializedDataType,
        clazz: Class<JavaObjectDataType>
    ): JavaObjectDataType

    protected abstract fun getBytes(msg: SerializedDataType): ByteArray
}
