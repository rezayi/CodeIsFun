package online.codeisfun.messagebrokers.rabbitmq.pubsub.publisher.controller

import online.codeisfun.messagebrokers.rabbitmq.constants.FANOUT_EXCHANGE_NAME
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/publish")
class RabbitMQProducerController(private val rabbitTemplate: RabbitTemplate) {
    private var taskCounter: Int = 1
    private var messageCounter: Int = 1

    @GetMapping("/task")
    fun sendTask(): String {
        val task = "task #${taskCounter++}"
        rabbitTemplate.convertAndSend(FANOUT_EXCHANGE_NAME, "", task)
        return "Task sent: $task"
    }

    @GetMapping("/message")
    fun sendMessage(): String {
        val message = "message #${messageCounter++}"
        rabbitTemplate.convertAndSend(FANOUT_EXCHANGE_NAME, "", message)
        return "Task sent: $message"
    }
}
