package online.codeisfun.messagebrokers.rabbitmq.servicecall.slave.configuration

import online.codeisfun.messagebrokers.rabbitmq.constants.DIRECT_EXCHANGE_NAME
import online.codeisfun.messagebrokers.rabbitmq.constants.ServiceCallConstants.Companion.MESSAGES_REQUEST_QUEUE_NAME
import online.codeisfun.messagebrokers.rabbitmq.constants.ServiceCallConstants.Companion.MESSAGES_REQUEST_ROUTING_KEY
import online.codeisfun.messagebrokers.rabbitmq.constants.ServiceCallConstants.Companion.TASKS_REQUEST_QUEUE_NAME
import online.codeisfun.messagebrokers.rabbitmq.constants.ServiceCallConstants.Companion.TASKS_REQUEST_ROUTING_KEY
import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.DirectExchange
import org.springframework.amqp.core.Queue
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RabbitMQConfig {

    @Bean
    fun tasksRequestQueue(): Queue {
        return Queue(TASKS_REQUEST_QUEUE_NAME, true) // Durable queue
    }

    @Bean
    fun messagesRequestQueue(): Queue {
        return Queue(MESSAGES_REQUEST_QUEUE_NAME, true) // Durable queue
    }

    @Bean
    fun exchange(): DirectExchange {
        return DirectExchange(DIRECT_EXCHANGE_NAME)
    }

    @Bean
    fun tasksRequestBinding(
        @Qualifier("tasksRequestQueue") tasksQueue: Queue,
        exchange: DirectExchange
    ): Binding {
        return BindingBuilder.bind(tasksQueue).to(exchange).with(TASKS_REQUEST_ROUTING_KEY)
    }

    @Bean
    fun messagesRequestBinding(
        @Qualifier("messagesRequestQueue") messagesQueue: Queue,
        exchange: DirectExchange
    ): Binding {
        return BindingBuilder.bind(messagesQueue).to(exchange).with(MESSAGES_REQUEST_ROUTING_KEY)
    }
}