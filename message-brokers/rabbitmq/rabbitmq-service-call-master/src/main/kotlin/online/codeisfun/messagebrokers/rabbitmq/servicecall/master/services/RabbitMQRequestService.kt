package online.codeisfun.messagebrokers.rabbitmq.servicecall.master.services

import online.codeisfun.messagebrokers.rabbitmq.constants.DIRECT_EXCHANGE_NAME
import online.codeisfun.messagebrokers.rabbitmq.constants.ServiceCallConstants.Companion.MESSAGES_REQUEST_ROUTING_KEY
import online.codeisfun.messagebrokers.rabbitmq.constants.ServiceCallConstants.Companion.TASKS_REQUEST_ROUTING_KEY
import org.springframework.amqp.rabbit.connection.CorrelationData
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Service
import java.util.*

@Service
class RabbitMQRequestService(private val rabbitTemplate: RabbitTemplate) {

    fun sendTaskRequest(data: String): String {
        val correlationId = UUID.randomUUID().toString()

        val response = rabbitTemplate.convertSendAndReceive(
            DIRECT_EXCHANGE_NAME,
            TASKS_REQUEST_ROUTING_KEY,
            data,
            CorrelationData(correlationId)
        )

        return response?.toString() ?: "No response received"
    }

    fun sendMessageRequest(data: String): String {
        val correlationId = UUID.randomUUID().toString()

        val response = rabbitTemplate.convertSendAndReceive(
            DIRECT_EXCHANGE_NAME,
            MESSAGES_REQUEST_ROUTING_KEY,
            data,
            CorrelationData(correlationId)
        )

        return response?.toString() ?: "No response received"
    }
}
