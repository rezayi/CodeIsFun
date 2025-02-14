## Code Is Fun :: Example :: Serialization :: Avro

In this module Serialization/Deserialization of objects in [serialization-commons](../serialization-commons) has been implemented using `Apache Avro` Serializer.

### Code:

maven dependencies:
```maven
<!-- Apache Avro (Core Library) -->
<dependency>
    <groupId>org.apache.avro</groupId>
    <artifactId>avro</artifactId>
    <version>1.11.3</version>
</dependency>
<dependency>
    <groupId>com.sksamuel.avro4k</groupId>
    <artifactId>avro4k-core</artifactId>
    <version>0.41.0</version>
</dependency>
```

maven plugin:
```maven
<!-- Avro Compiler Plugin -->
<plugin>
    <groupId>org.apache.avro</groupId>
    <artifactId>avro-maven-plugin</artifactId>
    <version>1.11.3</version>
    <executions>
        <execution>
            <phase>generate-sources</phase>
            <goals>
                <goal>schema</goal>
            </goals>
            <configuration>
                <sourceDirectory>src/main/avro</sourceDirectory>
                <outputDirectory>${project.build.directory}/generated-sources</outputDirectory>
            </configuration>
        </execution>
    </executions>
</plugin>
```

serialize java/kotlin object to `ByteArray`:
```kotlin
ByteArrayOutputStream().use { outputStream ->
    val encoder = EncoderFactory.get().binaryEncoder(outputStream, null)
    val writer = SpecificDatumWriter(online.codeisfun.serialization.avro.Order::class.java)
    writer.write((javaObject as Order).toAvro(), encoder)
    encoder.flush()
    outputStream.toByteArray()
}
```

deserialize `ByteArray` to java/kotlin object:
```kotlin
ByteArrayInputStream(msg).use { inputStream ->
    val decoder = DecoderFactory.get().binaryDecoder(inputStream, null)
    val reader = SpecificDatumReader(online.codeisfun.serialization.avro.Order::class.java)
    reader.read(null, decoder)
}
```

schema:
```avsc
[
  {
    "type": "enum",
    "name": "OrderStatus",
    "namespace": "online.codeisfun.serialization.avro",
    "symbols": [
      "PENDING",
      "SHIPPED",
      "DELIVERED",
      "CANCELED",
      "CONFIRMED"
    ]
  },
  ...
  {
    "type": "record",
    "name": "Order",
    "namespace": "online.codeisfun.serialization.avro",
    "fields": [
      {
        "name": "orderId",
        "type": "int"
      },
      {
        "name": "customer",
        "type": "online.codeisfun.serialization.avro.Customer"
      },
      {
        "name": "items",
        "type": {
          "type": "array",
          "items": "online.codeisfun.serialization.avro.OrderItem"
        }
      },
      {
        "name": "address",
        "type": "online.codeisfun.serialization.avro.Address"
      },
      {
        "name": "status",
        "type": "online.codeisfun.serialization.avro.OrderStatus"
      },
      {
        "name": "createdAt",
        "type": "long"
      },
      {
        "name": "updatedAt",
        "type": "long"
      },
      {
        "name": "description",
        "type": "string"
      }
    ]
  }
]
```