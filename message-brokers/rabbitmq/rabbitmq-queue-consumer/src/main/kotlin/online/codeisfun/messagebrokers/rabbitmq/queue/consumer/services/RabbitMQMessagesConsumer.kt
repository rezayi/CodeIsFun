package online.codeisfun.messagebrokers.rabbitmq.queue.consumer.services

import online.codeisfun.messagebrokers.rabbitmq.constants.QueueConstants.Companion.MESSAGES_QUEUE_NAME
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service

@Service
@Profile("messages")
class RabbitMQMessagesConsumer {

    @RabbitListener(queues = [MESSAGES_QUEUE_NAME])
    fun receiveMessage(message: String) {
        println("Received Message: $message")
    }
}
