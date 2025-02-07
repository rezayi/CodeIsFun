package online.codeisfun.rabbitmq.servicecall.master.configuration

import org.springframework.amqp.core.*
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RabbitMQConfig {

    companion object {
        const val EXCHANGE_NAME = "task_exchange"
        const val TASKS_ROUTING_KEY = "routing-tasks"
        const val MESSAGES_ROUTING_KEY = "routing-messages"
    }

    @Bean
    fun exchange(): DirectExchange {
        return DirectExchange(EXCHANGE_NAME)
    }
}
