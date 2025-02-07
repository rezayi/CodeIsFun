package online.codeisfun.messagebrokers.rabbitmq.pubsub.subscriber

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RabbitmqPubSubSubscriberApplication

fun main(args: Array<String>) {
    runApplication<RabbitmqPubSubSubscriberApplication>(*args)
}
