package online.codeisfun.rabbitmq.servicecall.master

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RabbitmqQueueProducerApplication

fun main(args: Array<String>) {
    runApplication<RabbitmqQueueProducerApplication>(*args)
}
