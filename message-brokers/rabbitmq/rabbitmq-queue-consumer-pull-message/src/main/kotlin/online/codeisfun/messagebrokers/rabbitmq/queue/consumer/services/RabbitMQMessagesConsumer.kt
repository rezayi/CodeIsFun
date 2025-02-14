package online.codeisfun.messagebrokers.rabbitmq.queue.consumer.services

import online.codeisfun.messagebrokers.rabbitmq.constants.QueueConstants
import org.springframework.amqp.core.Message
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Service


@Service
class RabbitMQMessagesConsumer(val rabbitTemplate: RabbitTemplate) {

    fun receiveMessage(ack: Boolean): String? {
        val message: Message? = rabbitTemplate.receive(QueueConstants.MESSAGES_QUEUE_NAME)
        return message?.body?.let { String(it) }
    }
}
