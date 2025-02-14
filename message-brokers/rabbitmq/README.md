## Code Is Fun :: Message brokers :: RabbitMQ

In this module we are going to test features of RabbitMQ.

RabbitMQ has 3 types of connections:
 - Queue
 - Pub-Sub
 - Service Call

## 1- Queue:
In this type `Producer` sends a message to the `Routing`. <br>
Then a `Consumer` creates or connects a `Queue` and binds the `Queue` and `Routing` through the `Exchange`.<br>
Now sent message by the `Producer` will receive by the consumer.

### Code:<br>
Configuration: 
```kotlin
@Configuration
class RabbitMQConfig {

    @Bean
    fun messagesQueue(): Queue {
        return Queue(MESSAGES_QUEUE_NAME, true) // Durable queue
    }

    @Bean
    fun exchange(): DirectExchange {
        return DirectExchange(DIRECT_EXCHANGE_NAME)
    }

    @Bean
    fun messagesBinding(@Qualifier("messagesQueue") messagesQueue: Queue, exchange: DirectExchange): Binding {
        return BindingBuilder.bind(messagesQueue).to(exchange).with(MESSAGES_ROUTING_KEY)
    }
}
```
Producer:
```kotlin
@Service
class RabbitMQProducerService(private val rabbitTemplate: RabbitTemplate) {

    fun sendMessage() {
        rabbitTemplate.convertAndSend(DIRECT_EXCHANGE_NAME, MESSAGES_ROUTING_KEY, "message")
    }
}
```
Consumer:
```kotlin
@Service
class RabbitMQMessagesConsumer {

    @RabbitListener(queues = [MESSAGES_QUEUE_NAME])
    fun receiveMessage(message: String) {
        println("Received Message: $message")
    }
}
```

## 1-1 Pull messages from the Queue manually
To pull messages from queues manually we need to use `RabbitTemplate` bean.<br> 
The configurations and producer is completely like previous part.<br>
Consumer should be like below:
```kotlin
@Service
class RabbitMQMessagesConsumer(val rabbitTemplate: RabbitTemplate) {

    fun receiveMessage(ack: Boolean): String? {
        val message: Message? = rabbitTemplate.receive(QueueConstants.MESSAGES_QUEUE_NAME)
        return message?.body?.let { String(it) }
    }
}
```

## 2- Service Call (RPC)
RabbitMQ can be used as `RPC`.<br>
Imagine ServiceA wants to call an `RPC` of ServiceB. We call ServiceA as `Master` and serviceB as `Slave`.<br>
The first part of type is almost similar to `Queue` type, It means the `Master` sends a message to a `Routing`.
Then the `Slave` creates a queue and binds the `Queue` and `Routing` through the `Exchange`.
Now `Slave` receives the message.<br>
The difference is that the `Master` sends the message using `rabbitTemplate.convertSendAndReceive()` method instead of `rabbitTemplate.convertAndSend()` method.
Also, the send message needs a `CorrelationData` attributes that includes a `UUID`.<br>
When the `Slave` service receives a message, processes it and returns the response. The broker sends response via `reply-to` queue.<br>
The `Master` service which has sent the message is listening to `reply-to` queue too.
when it receives the response with its own `UUID`, assumes the data as the `RPC` response.<br>
***NOTE:*** this `RPC` has timeout and if the slave cannot process it in the expected time, it will fail.

### Code:
Configuration:
```kotlin
@Configuration
class RabbitMQConfig {

    @Bean
    fun messagesQueue(): Queue {
        return Queue(MESSAGES_QUEUE_NAME, true) // Durable queue
    }

    @Bean
    fun exchange(): DirectExchange {
        return DirectExchange(DIRECT_EXCHANGE_NAME)
    }

    @Bean
    fun messagesBinding(@Qualifier("messagesQueue") messagesQueue: Queue, exchange: DirectExchange): Binding {
        return BindingBuilder.bind(messagesQueue).to(exchange).with(MESSAGES_ROUTING_KEY)
    }
}
```
Master:
```kotlin
@Service
class RabbitMQRequestService(private val rabbitTemplate: RabbitTemplate) {

    fun sendMessageRequest(data: String): String {
        val correlationId = UUID.randomUUID().toString()

        val response = rabbitTemplate.convertSendAndReceive(
            DIRECT_EXCHANGE_NAME,
            MESSAGES_REQUEST_ROUTING_KEY,
            data,
            CorrelationData(correlationId)
        )

        return response?.toString() ?: "No response received"
    }
}
```
Slave:
```kotlin
@Service
class RabbitMQMessagesConsumer{

    @RabbitListener(queues = [MESSAGES_REQUEST_QUEUE_NAME])
    fun processMessage(message: String): String {
        println("Received Message: $message")
        return "Response to Message: $message"
    }
}
```

## 3- Pub-Sub
The last type of RabbitMQ working types is Pub-Sub.<br> 
Even though, the configuration of this type is similar to `Queue`, it has a difference.<br>
In `Queue`, we use `DirectExchange` which binds the `Queue` and `Routing`. 
But, for Pub-Sub mode we should use `FanoutExchange`. 
In `FanoutExchange`, publisher sends a message to the exchange without any `Routing`.
Then the `FanoutExchange` sends the message to all connected queues.<br>
***NOTE:*** `FanoutExchange` sends the message to all connected queues, but if two listeners are listening to a unique `Queue` It will dispatch messages between them by round-robin algorithm yet.

### Code:
Config:
```kotlin
@Configuration
class RabbitMQConfig {

    @Bean
    fun tasksQueue(): Queue {
        return Queue(TASKS_TOPIC_QUEUE_NAME, true) // Durable queue
    }

    @Bean
    fun messagesQueue(): Queue {
        return Queue(MESSAGES_TOPIC_QUEUE_NAME, true) // Durable queue
    }

    @Bean
    fun exchange(): FanoutExchange {
        return FanoutExchange(FANOUT_EXCHANGE_NAME)
    }

    @Bean
    fun tasksBinding(@Qualifier("tasksQueue") tasksQueue: Queue, exchange: FanoutExchange): Binding {
        return BindingBuilder.bind(tasksQueue).to(exchange)
    }

    @Bean
    fun messagesBinding(@Qualifier("messagesQueue") messagesQueue: Queue, exchange: FanoutExchange): Binding {
        return BindingBuilder.bind(messagesQueue).to(exchange)
    }
}
```
Publisher:
```kotlin
@Service
class RabbitMQPublisherService(private val rabbitTemplate: RabbitTemplate) {

    fun sendMessage(){
        val message = "message"
        rabbitTemplate.convertAndSend(FANOUT_EXCHANGE_NAME, "", message)
    }
}
```
Subscriber:
```kotlin
@Service
class RabbitMQMessagesConsumer{

    @RabbitListener(queues = [MESSAGES_TOPIC_QUEUE_NAME])
    fun receiveMessage(message: String) {
        println("Received Message: $message")
    }
}
```

## RabbitMQ Exchange types

- **Direct Exchange:** sends message through a specific `Routing` and binds a `Queue` to the `Routing`.
- **Fanout Exchange:** sends message to all `Queues` are connected to the `Exchange`.

## Library needed:
```maven
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-amqp</artifactId>
    </dependency>
</dependencies>
```

## connection config:
application.yaml:
```yaml
spring:
  rabbitmq:
    host: localhost
    port: 5672
    username: guest
    password: guest
```

## Acknowledge message processed
To acknowledge whether a message has been processed successfully or not, `rabbitTemplate` cannot be used.<br>
When we use `rabbitTemplate.receive()`, the message will remove from the queue immediately.
To acknowledge the message processing manually, we should use `RabbitListener` with both `message` and `channel` in input.
using `channel` we can use acknowledgement options.<br>
rabbit supports these kind of acknowledgements:
- **basicAck:** message processed successfully
- **basicNack with requeue:** message couldn't process successfully, but requeue it to process it again.
- **basicNack without requeue or basicReject:** message couldn't process successfully, but we don't want to reprocess it.

### What will happen if we reject a message?
If we don't set any setting for, it will be discarded.<br> 
But if we set a `dead letter exchange` for it, It will be sent it `dead letter queue` automatically.