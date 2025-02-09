## CodeIsFun :: Example :: Serialization :: Kryo

In this module Serialization/Deserialization of objects in [serialization-commons](../serialization-commons) has been implemented using `Kryo` Serializer.

### Code:
maven library:
```maven
<dependency>
    <groupId>com.esotericsoftware</groupId>
    <artifactId>kryo</artifactId>
    <version>5.6.1</version>
</dependency>
```

create Default Kryo object:
```kotlin
private val kryo = Kryo()
    .apply {
        //you need to register all classes manually if you set it to true
        isRegistrationRequired = false
        //
        instantiatorStrategy = DefaultInstantiatorStrategy(StdInstantiatorStrategy())
    }
```

serialize java/kotlin object to `ByteArray`:
```kotlin
val byteArrayOutputStream = ByteArrayOutputStream()
val output = Output(byteArrayOutputStream)
kryo.writeObject(output, javaObject)
output.close()
val bytes = byteArrayOutputStream.toByteArray()
```

deserialize `ByteArray` to java/kotlin object:
```kotlin
val input = Input(ByteArrayInputStream(msg))
val deserialized = kryo.readObject(input, clazz)
input.close()
```

### You can read more about Kryo features and configurations [here](https://github.com/EsotericSoftware/kryo/blob/master/README.md)  