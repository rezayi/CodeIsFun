package online.codeisfun.messagebrokers.rabbitmq.servicecall.slave.services

import online.codeisfun.messagebrokers.rabbitmq.constants.ServiceCallConstants.Companion.MESSAGES_REQUEST_QUEUE_NAME
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service

@Service
@Profile("messages")
class RabbitMQMessagesConsumer{

    @RabbitListener(queues = [MESSAGES_REQUEST_QUEUE_NAME])
    fun processMessage(message: String): String {
        println("Received Message: $message")
        return "Response to Message: $message"
    }
}
