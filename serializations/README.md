## Code Is Fun :: Serializers

In this module serialization and deserialization for several serialization algorithms have been implemented.<br>
For this test, a unique test scenario has been implemented in [serialization-commons](serialization-commons) module.<br>
For each algorithm, a separate module has been implemented.

these serialization algorithms have been implemented:
- [Kryo](kryo/README.md)
- [Protobuf](protobuf/README.md)
- JSON
- BSON
- Message Pack


## Test Scenarios:

- Order: An almost complex object which its size is dynamic based on a `level` factor. This test will run several time for different levels.
- HtmlContainer: A simple object contains a large String data.

***Note:*** the scenario is equal for all algorithms and the only part has been implemented for algorithms are serialization and deserialization methods.

## Compare Test Results:

### process time (nanoseconds)

|  level   | Order #0 | Order #1 | Order #2 | Order #3 | Order #4 | Order #5 |  Html   |
|:--------:|:--------:|:--------:|:--------:|:--------:|:--------:|:--------:|:-------:|
|   Kryo   |   1287   |   1461   |   1921   |   2371   |   2837   |   3314   | 1728117 |
| ProtoBuf |   855    |   1267   |   1550   |   1980   |   2460   |   2831   | 704565  |


### Serialized message size (bytes)

|  level   | Order #0 | Order #1 | Order #2 | Order #3 | Order #4 | Order #5 |  Html  |
|:--------:|:--------:|:--------:|:--------:|:--------:|:--------:|:--------:|:------:|
|   Kryo   |   141    |   486    |   829    |   1173   |   1516   |   1860   | 633231 |
| ProtoBuf |   141    |   508    |   872    |   1236   |   1601   |   1965   | 633232 |

