package online.codeisfun.rabbitmq.servicecall.master.services

import online.codeisfun.rabbitmq.servicecall.master.configuration.RabbitMQConfig
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service

@Service
@Profile("messages")
class RabbitMQMessagesConsumer {

    @RabbitListener(queues = [RabbitMQConfig.MESSAGES_QUEUE_NAME])
    fun receiveMessage(message: String) {
        println("Received Message: $message")
    }
}
