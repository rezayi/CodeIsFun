package online.codeisfun.messagebrokers.rabbitmq.servicecall.slave

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RabbitmqServiceCallSlaveApplication

fun main(args: Array<String>) {
    runApplication<RabbitmqServiceCallSlaveApplication>(*args)
}
