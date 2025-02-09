package online.codeisfun.serialization.commons

enum class OrderStatus {
    PENDING,
    SHIPPED,
    DELIVERED,
    CANCELED,
    CONFIRMED
}

data class Order(
    val orderId: Int,
    val customer: Customer,
    val items: List<OrderItem>,
    val address: Address,
    val status: OrderStatus,
    val createdAt: Long,
    val updatedAt: Long,
    val description: String
)

data class OrderItem(
    val itemId: Int,
    val name: String,
    val quantity: Int,
    val price: Double
)

data class Customer(
    val customerId: Int,
    val name: String,
    val email: String,
    val phone: String
)

data class Address(
    val country: String,
    val state: String,
    val city: String,
    val street: String,
    val postalCode: String
)

data class HtmlObjectContainer(
val content: String
)