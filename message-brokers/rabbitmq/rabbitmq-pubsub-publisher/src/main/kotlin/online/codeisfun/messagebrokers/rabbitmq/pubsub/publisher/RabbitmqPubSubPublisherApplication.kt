package online.codeisfun.messagebrokers.rabbitmq.pubsub.publisher

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RabbitmqPubSubPublisherApplication

fun main(args: Array<String>) {
    runApplication<RabbitmqPubSubPublisherApplication>(*args)
}
