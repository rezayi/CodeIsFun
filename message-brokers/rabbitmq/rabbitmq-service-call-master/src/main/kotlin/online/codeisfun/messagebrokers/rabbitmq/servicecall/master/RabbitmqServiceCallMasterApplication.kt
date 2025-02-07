package online.codeisfun.messagebrokers.rabbitmq.servicecall.master

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RabbitmqServiceCallMasterApplication

fun main(args: Array<String>) {
    runApplication<RabbitmqServiceCallMasterApplication>(*args)
}
