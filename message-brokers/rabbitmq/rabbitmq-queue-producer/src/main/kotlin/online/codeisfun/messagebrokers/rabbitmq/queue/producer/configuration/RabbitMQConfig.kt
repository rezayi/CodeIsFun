package online.codeisfun.messagebrokers.rabbitmq.queue.producer.configuration
import online.codeisfun.messagebrokers.rabbitmq.constants.DIRECT_EXCHANGE_NAME
import org.springframework.amqp.core.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RabbitMQConfig {

    @Bean
    fun exchange(): DirectExchange {
        return DirectExchange(DIRECT_EXCHANGE_NAME)
    }
}
