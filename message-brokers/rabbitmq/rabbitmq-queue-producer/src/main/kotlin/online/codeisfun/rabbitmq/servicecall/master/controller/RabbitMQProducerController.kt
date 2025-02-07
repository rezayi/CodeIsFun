package online.codeisfun.rabbitmq.servicecall.master.controller

import online.codeisfun.rabbitmq.servicecall.master.configuration.RabbitMQConfig
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/publish")
class RabbitMQProducerController(private val rabbitTemplate: RabbitTemplate) {
    private var taskCounter: Int = 1
    private var messageCounter: Int = 1

    @GetMapping("/task")
    fun sendTask(): String {
        val task = "task #${taskCounter++}"
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.TASKS_ROUTING_KEY, task)
        return "Task sent: $task"
    }

    @GetMapping("/message")
    fun sendMessage(): String {
        val message = "message #${messageCounter++}"
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.MESSAGES_ROUTING_KEY, message)
        return "Task sent: $message"
    }
}
