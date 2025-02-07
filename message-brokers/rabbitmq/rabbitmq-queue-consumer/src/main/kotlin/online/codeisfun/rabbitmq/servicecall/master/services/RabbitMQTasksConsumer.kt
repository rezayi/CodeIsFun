package online.codeisfun.rabbitmq.servicecall.master.services

import online.codeisfun.rabbitmq.servicecall.master.configuration.RabbitMQConfig
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service

@Service
@Profile("tasks")
class RabbitMQTasksConsumer {
    @RabbitListener(queues = [RabbitMQConfig.TASKS_QUEUE_NAME])
    fun receiveTask(task: String) {
        println("Received Task: $task")
    }
}
