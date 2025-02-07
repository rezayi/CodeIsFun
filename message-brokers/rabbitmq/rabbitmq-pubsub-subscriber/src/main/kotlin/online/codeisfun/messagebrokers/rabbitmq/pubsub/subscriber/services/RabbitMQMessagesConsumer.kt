package online.codeisfun.messagebrokers.rabbitmq.pubsub.subscriber.services

import online.codeisfun.messagebrokers.rabbitmq.constants.PubSubConstants.Companion.MESSAGES_TOPIC_QUEUE_NAME
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service

@Service
@Profile("messages")
class RabbitMQMessagesConsumer{

    @RabbitListener(queues = [MESSAGES_TOPIC_QUEUE_NAME])
    fun receiveMessage(message: String) {
        println("Received Message: $message")
    }
}
