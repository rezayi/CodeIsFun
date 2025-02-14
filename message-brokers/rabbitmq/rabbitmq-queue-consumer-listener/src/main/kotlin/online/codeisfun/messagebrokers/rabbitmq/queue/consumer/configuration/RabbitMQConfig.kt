package online.codeisfun.messagebrokers.rabbitmq.queue.consumer.configuration

import online.codeisfun.messagebrokers.rabbitmq.constants.DIRECT_EXCHANGE_NAME
import online.codeisfun.messagebrokers.rabbitmq.constants.QueueConstants.Companion.MESSAGES_QUEUE_NAME
import online.codeisfun.messagebrokers.rabbitmq.constants.QueueConstants.Companion.MESSAGES_ROUTING_KEY
import online.codeisfun.messagebrokers.rabbitmq.constants.QueueConstants.Companion.TASKS_QUEUE_NAME
import online.codeisfun.messagebrokers.rabbitmq.constants.QueueConstants.Companion.TASKS_ROUTING_KEY
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
    fun tasksQueue(): Queue {
        return Queue(TASKS_QUEUE_NAME, true) // Durable queue
    }

    @Bean
    fun messagesQueue(): Queue {
        return Queue(MESSAGES_QUEUE_NAME, true) // Durable queue
    }

    @Bean
    fun exchange(): DirectExchange {
        return DirectExchange(DIRECT_EXCHANGE_NAME)
    }

    @Bean
    fun tasksBinding(@Qualifier("tasksQueue") tasksQueue: Queue, exchange: DirectExchange): Binding {
        return BindingBuilder.bind(tasksQueue).to(exchange).with(TASKS_ROUTING_KEY)
    }

    @Bean
    fun messagesBinding(@Qualifier("messagesQueue") messagesQueue: Queue, exchange: DirectExchange): Binding {
        return BindingBuilder.bind(messagesQueue).to(exchange).with(MESSAGES_ROUTING_KEY)
    }
}
