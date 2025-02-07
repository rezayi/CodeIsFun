package online.codeisfun.messagebrokers.rabbitmq.queue.producer.controller

import online.codeisfun.messagebrokers.rabbitmq.constants.DIRECT_EXCHANGE_NAME
import online.codeisfun.messagebrokers.rabbitmq.constants.QueueConstants.Companion.MESSAGES_ROUTING_KEY
import online.codeisfun.messagebrokers.rabbitmq.constants.QueueConstants.Companion.TASKS_ROUTING_KEY
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
        rabbitTemplate.convertAndSend(DIRECT_EXCHANGE_NAME, TASKS_ROUTING_KEY, task)
        return "Task sent: $task"
    }

    @GetMapping("/message")
    fun sendMessage(): String {
        val message = "message #${messageCounter++}"
        rabbitTemplate.convertAndSend(DIRECT_EXCHANGE_NAME, MESSAGES_ROUTING_KEY, message)
        return "Task sent: $message"
    }
}
