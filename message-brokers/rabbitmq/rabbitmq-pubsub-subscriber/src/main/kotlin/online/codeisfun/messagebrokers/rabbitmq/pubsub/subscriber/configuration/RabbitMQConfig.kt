package online.codeisfun.messagebrokers.rabbitmq.pubsub.subscriber.configuration

import online.codeisfun.messagebrokers.rabbitmq.constants.FANOUT_EXCHANGE_NAME
import online.codeisfun.messagebrokers.rabbitmq.constants.PubSubConstants.Companion.MESSAGES_TOPIC_QUEUE_NAME
import online.codeisfun.messagebrokers.rabbitmq.constants.PubSubConstants.Companion.TASKS_TOPIC_QUEUE_NAME
import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.FanoutExchange
import org.springframework.amqp.core.Queue
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RabbitMQConfig {

    @Bean
    fun tasksQueue(): Queue {
        return Queue(TASKS_TOPIC_QUEUE_NAME, true) // Durable queue
    }

    @Bean
    fun messagesQueue(): Queue {
        return Queue(MESSAGES_TOPIC_QUEUE_NAME, true) // Durable queue
    }

    @Bean
    fun exchange(): FanoutExchange {
        return FanoutExchange(FANOUT_EXCHANGE_NAME)
    }

    @Bean
    fun tasksBinding(@Qualifier("tasksQueue") tasksQueue: Queue, exchange: FanoutExchange): Binding {
        return BindingBuilder.bind(tasksQueue).to(exchange)
    }

    @Bean
    fun messagesBinding(@Qualifier("messagesQueue") messagesQueue: Queue, exchange: FanoutExchange): Binding {
        return BindingBuilder.bind(messagesQueue).to(exchange)
    }
}
