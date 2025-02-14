package online.codeisfun.messagebrokers.rabbitmq.queue.consumer.services

import com.rabbitmq.client.Channel
import online.codeisfun.messagebrokers.rabbitmq.constants.QueueConstants.Companion.DLX_QUEUE_NAME
import online.codeisfun.messagebrokers.rabbitmq.constants.QueueConstants.Companion.MESSAGES_QUEUE_NAME
import org.springframework.amqp.core.Message
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service

@Service
class RabbitMQMessagesConsumer {

    @RabbitListener(queues = [MESSAGES_QUEUE_NAME])
    fun receiveMessageFail(message: Message, channel: Channel) {
        val body = String(message.body)
        val num = body.split("#")[1].toInt()
        if (num % 3 == 0) {
            println("Reject Message: $body")
            channel.basicReject(message.messageProperties.deliveryTag, false)
        } else {
            println("Requeue Message: $body")
            channel.basicNack(message.messageProperties.deliveryTag, false, true)
        }
    }

    @RabbitListener(queues = [MESSAGES_QUEUE_NAME])
    fun receiveMessageSuccess(message: Message, channel: Channel) {
        val body= String(message.body)
        println("Received Message: $body")
        channel.basicAck(message.messageProperties.deliveryTag, false)
    }

    @RabbitListener(queues = [DLX_QUEUE_NAME])
    fun receiveDeadLetterExchangeMessages(message: Message) {
        val body= String(message.body)
        println("DLX: Dead Message: $body")
    }
}
