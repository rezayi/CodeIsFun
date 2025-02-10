## Code Is Fun :: Serializers

In this module serialization and deserialization for several serialization algorithms have been implemented.<br>
For this test, a unique test scenario has been implemented in [serialization-commons](serialization-commons) module.<br>
For each algorithm, a separate module has been implemented.

these serialization algorithms have been implemented:
- [Kryo](kryo/README.md)
- [Protobuf](protobuf/README.md)
- [JSON](json/README.md)
- BSON
- Message Pack


## Test Scenarios:

- Order: An almost complex object which its size is dynamic based on a `level` factor. This test will run several time for different levels.
- HtmlContainer: A simple object contains a large String data.

***Note:*** the scenario is equal for all algorithms and the only part has been implemented for algorithms are serialization and deserialization methods.

## Compare Test Results:

### process time (nanoseconds)

|      level       | Order #0 | Order #1 | Order #2 | Order #3 | Order #4 | Order #5 |   Html    |
|:----------------:|:--------:|:--------:|:--------:|:--------:|:--------:|:--------:|:---------:|
|       Kryo       |  1,287   |  1,461   |  1,921   |  2,371   |  2,837   |  3,314   | 1,728,117 |
|     ProtoBuf     |   855    |  1,267   |  1,550   |  1,980   |  2,460   |  2,831   |  704,565  |
| JSON (fasterxml) |  3,528   |  5,269   |  6,905   |  8,907   |  10,809  |  12,544  | 3,364,299 |

### Serialized message size (bytes)

|      level       | Order #0 | Order #1 | Order #2 | Order #3 | Order #4 | Order #5 |  Html   |
|:----------------:|:--------:|:--------:|:--------:|:--------:|:--------:|:--------:|:-------:|
|       Kryo       |   141    |   486    |   829    |  1,173   |  1,516   |  1,860   | 633,231 |
|     ProtoBuf     |   141    |   508    |   872    |  1,236   |  1,601   |  1,965   | 633,232 |
| JSON (fasterxml) |   353    |   830    |  1,308   |  1,786   |  2,264   |  2,742   | 650,820 |

