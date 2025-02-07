package online.codeisfun.messagebrokers.rabbitmq.servicecall.master.controller

import online.codeisfun.messagebrokers.rabbitmq.servicecall.master.services.RabbitMQRequestService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/service-call")
class RabbitMQMasterController(
    private val rabbitMQRequestService: RabbitMQRequestService
) {
    private var taskCounter: Int = 1
    private var messageCounter: Int = 1

    @GetMapping("/task")
    fun sendTask(): String {
        val task = "task #${taskCounter++}"
        return rabbitMQRequestService.sendTaskRequest(task)
    }

    @GetMapping("/message")
    fun sendMessage(): String {
        val message = "message #${messageCounter++}"
        return rabbitMQRequestService.sendMessageRequest(message)
    }
}
