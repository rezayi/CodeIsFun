package online.codeisfun.messagebrokers.rabbitmq.pubsub.subscriber.services

import online.codeisfun.messagebrokers.rabbitmq.constants.PubSubConstants.Companion.TASKS_TOPIC_QUEUE_NAME
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service

@Service
@Profile("tasks")
class RabbitMQTasksConsumer {
    @RabbitListener(queues = [TASKS_TOPIC_QUEUE_NAME])
    fun receiveTask(task: String) {
        println("Received Task: $task")
    }
}
