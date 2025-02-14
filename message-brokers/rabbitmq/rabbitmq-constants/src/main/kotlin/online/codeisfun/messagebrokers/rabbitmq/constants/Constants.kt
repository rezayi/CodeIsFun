package online.codeisfun.messagebrokers.rabbitmq.constants

const val DIRECT_EXCHANGE_NAME = "exchange.direct"
const val FANOUT_EXCHANGE_NAME = "exchange.fanout"
const val DLX_EXCHANGE_NAME = "exchange.dlx"

class QueueConstants {
    companion object {
        const val TASKS_QUEUE_NAME = "queue.tasks"
        const val MESSAGES_QUEUE_NAME = "queue.messages"
        const val TASKS_ROUTING_KEY = "routing.tasks"
        const val MESSAGES_ROUTING_KEY = "routing.messages"

        const val DLX_QUEUE_NAME = "queue.dlx"
        const val DLX_ROUTING_KEY = "routing.dlx"
    }
}

class ServiceCallConstants {
    companion object {
        const val TASKS_REQUEST_QUEUE_NAME = "queue.request.tasks"
        const val MESSAGES_REQUEST_QUEUE_NAME = "queue.request.messages"
        const val TASKS_REQUEST_ROUTING_KEY = "routing.request.tasks"
        const val MESSAGES_REQUEST_ROUTING_KEY = "routing.request.messages"
    }
}

class PubSubConstants {
    companion object {
        const val TASKS_TOPIC_QUEUE_NAME = "queue.topic.tasks"
        const val MESSAGES_TOPIC_QUEUE_NAME = "queue.topic.messages"
    }
}