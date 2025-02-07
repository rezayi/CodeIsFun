package online.codeisfun.messagebrokers.rabbitmq.servicecall.slave.services

import online.codeisfun.messagebrokers.rabbitmq.constants.ServiceCallConstants.Companion.TASKS_REQUEST_QUEUE_NAME
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service

@Service
@Profile("tasks")
class RabbitMQTasksConsumer {

    @RabbitListener(queues = [TASKS_REQUEST_QUEUE_NAME])
    fun processMessage(task: String): String {
        println("Received Task: $task")
        return "Response to Task: $task"
    }
}
