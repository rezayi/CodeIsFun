package online.codeisfun.messagebrokers.rabbitmq.queue.consumer.controllers

import online.codeisfun.messagebrokers.rabbitmq.queue.consumer.services.RabbitMQMessagesConsumer
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/pull")
class RabbitMQPullDataController(
    private val rabbitMQMessagesConsumer: RabbitMQMessagesConsumer,
) {

    @GetMapping("/message")
    fun pullMessage(@RequestParam("ack") ack: Boolean): String? {
        return rabbitMQMessagesConsumer.receiveMessage(ack)
    }
}