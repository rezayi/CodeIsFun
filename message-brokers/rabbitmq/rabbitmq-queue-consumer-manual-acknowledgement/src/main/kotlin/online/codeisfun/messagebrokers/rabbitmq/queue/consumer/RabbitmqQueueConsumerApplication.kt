package online.codeisfun.messagebrokers.rabbitmq.queue.consumer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RabbitmqQueueConsumerApplication

fun main(args: Array<String>) {
    runApplication<RabbitmqQueueConsumerApplication>(*args)
}
