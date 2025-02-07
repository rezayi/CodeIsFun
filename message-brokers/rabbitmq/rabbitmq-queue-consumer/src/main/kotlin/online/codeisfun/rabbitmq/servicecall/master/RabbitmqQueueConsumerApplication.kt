package online.codeisfun.rabbitmq.servicecall.master

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RabbitmqQueueConsumerApplication

fun main(args: Array<String>) {
    runApplication<RabbitmqQueueConsumerApplication>(*args)
}
