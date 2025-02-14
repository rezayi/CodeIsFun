package online.codeisfun.messagebrokers.rabbitmq.queue.consumer.configuration

import online.codeisfun.messagebrokers.rabbitmq.constants.DIRECT_EXCHANGE_NAME
import online.codeisfun.messagebrokers.rabbitmq.constants.DLX_EXCHANGE_NAME
import online.codeisfun.messagebrokers.rabbitmq.constants.QueueConstants.Companion.DLX_QUEUE_NAME
import online.codeisfun.messagebrokers.rabbitmq.constants.QueueConstants.Companion.DLX_ROUTING_KEY
import online.codeisfun.messagebrokers.rabbitmq.constants.QueueConstants.Companion.MESSAGES_QUEUE_NAME
import online.codeisfun.messagebrokers.rabbitmq.constants.QueueConstants.Companion.MESSAGES_ROUTING_KEY
import org.springframework.amqp.core.*
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class RabbitMQConfig {

    @Bean
    fun deadLetterQueue(): Queue {
        return QueueBuilder.durable(DLX_QUEUE_NAME).build()
    }

    @Bean
    fun messagesQueue(): Queue {
        return QueueBuilder.durable(MESSAGES_QUEUE_NAME)
            .withArgument("x-dead-letter-exchange", DLX_EXCHANGE_NAME)
            .withArgument("x-dead-letter-routing-key", DLX_ROUTING_KEY)
            .build()
    }

    @Bean
    fun directExchange(): DirectExchange {
        return DirectExchange(DIRECT_EXCHANGE_NAME)
    }

    @Bean
    fun deadLetterExchange(): DirectExchange {
        return DirectExchange(DLX_EXCHANGE_NAME)
    }

    @Bean
    fun messagesBinding(
        @Qualifier("messagesQueue") messagesQueue: Queue,
        @Qualifier("directExchange") exchange: DirectExchange
    ): Binding {
        return BindingBuilder.bind(messagesQueue).to(exchange).with(MESSAGES_ROUTING_KEY)
    }

    @Bean
    fun dlqBinding(
        @Qualifier("deadLetterQueue") deadLetterQueue: Queue?,
        @Qualifier("deadLetterExchange") deadLetterExchangeexchange: DirectExchange, deadLetterExchange: DirectExchange
    ): Binding {
        return BindingBuilder.bind(deadLetterQueue).to(deadLetterExchange).with(DLX_ROUTING_KEY)
    }
}
