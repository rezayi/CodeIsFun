package online.codeisfun.messagebrokers.rabbitmq.queue.consumer.services

import online.codeisfun.messagebrokers.rabbitmq.constants.QueueConstants.Companion.TASKS_QUEUE_NAME
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service

@Service
@Profile("tasks")
class RabbitMQTasksConsumer {
    @RabbitListener(queues = [TASKS_QUEUE_NAME])
    fun receiveTask(task: String) {
        println("Received Task: $task")
    }
}
