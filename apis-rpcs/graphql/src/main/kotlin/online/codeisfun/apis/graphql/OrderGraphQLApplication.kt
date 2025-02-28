package online.codeisfun.apis.graphql

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class OrderGraphQLApplication

fun main(args: Array<String>) {
    runApplication<OrderGraphQLApplication>(*args)
}
