package online.codeisfun.serialization.commons

import kotlin.math.max

class ObjectInitializer {
    companion object {
        fun createOrder(index: Int, objectSizeLevel: Int): Order {
            val customerId = index / 5
            val customer = Customer(
                customerId,
                "User name#$customerId",
                customerId.toString() + "_user@testmail.com",
                "091" + (100000000 + index)
            )
            val address = Address(
                "Country name",
                "State #" + (index % 10),
                "City #" + (index % 100),
                "Street #" + (index % 1000),
                (index + 10000000).toString()
            )
            val items: MutableList<OrderItem> = ArrayList()
            for (i in 0..<objectSizeLevel * 3) {
                items.add(
                    OrderItem(
                        index % 1000,
                        "item name #$index",
                        5,
                        (10000000 + index) / 100.0
                    )
                )
            }
            val description = StringBuilder()
            description.append(
                "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".repeat(
                    max(
                        0.0,
                        (objectSizeLevel * 5).toDouble()
                    ).toInt()
                )
            )
            return Order(
                index + 1,
                customer,
                items,
                address,
                OrderStatus.entries[index % (OrderStatus.entries.size - 1)],
                System.currentTimeMillis(),
                System.currentTimeMillis(),
                description.toString()
            )
        }

        fun createHtmlObject(): HtmlObjectContainer {
            val content = String(
                Thread.currentThread()
                    .contextClassLoader
                    .getResourceAsStream("Amazon.html")
                    .readAllBytes()
            )
            return HtmlObjectContainer(content)
        }
    }
}