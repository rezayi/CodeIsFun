package online.codeisfun.messagebrokers.rabbitmq.pubsub.publisher.configuration
import online.codeisfun.messagebrokers.rabbitmq.constants.FANOUT_EXCHANGE_NAME
import org.springframework.amqp.core.DirectExchange
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RabbitMQConfig {

    @Bean
    fun exchange(): DirectExchange {
        return DirectExchange(FANOUT_EXCHANGE_NAME)
    }
}
