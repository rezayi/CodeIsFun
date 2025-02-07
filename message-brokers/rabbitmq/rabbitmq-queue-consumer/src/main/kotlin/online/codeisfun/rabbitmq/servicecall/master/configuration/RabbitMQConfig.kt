package online.codeisfun.rabbitmq.servicecall.master.configuration

import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.DirectExchange
import org.springframework.amqp.core.Queue
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RabbitMQConfig {

    companion object {
        const val TASKS_QUEUE_NAME = "tasks_queue"
        const val MESSAGES_QUEUE_NAME = "messages_queue"
        const val EXCHANGE_NAME = "task_exchange"
        const val TASKS_ROUTING_KEY = "routing-tasks"
        const val MESSAGES_ROUTING_KEY = "routing-messages"
    }

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
        return DirectExchange(EXCHANGE_NAME)
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
