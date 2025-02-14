package online.codeisfun.serialization.avro

import online.codeisfun.serialization.commons.*

fun online.codeisfun.serialization.commons.Order.toAvro(): online.codeisfun.serialization.avro.Order {
    return online.codeisfun.serialization.avro.Order.newBuilder()
        .setOrderId(orderId)
        .setCustomer(customer.toAvro())
        .setItems(items.stream().map { it.toAvro() }.toList())
        .setAddress(address.toAvro())
        .setStatus(status.toAvro())
        .setCreatedAt(createdAt)
        .setUpdatedAt(updatedAt)
        .setDescription(description)
        .build()
}

fun fromAvro(avroOrder: online.codeisfun.serialization.avro.Order): Order {
    return Order(
        avroOrder.orderId,
        fromProto(avroOrder.customer),
        avroOrder.items.stream().map { item -> fromProto(item) }.toList(),
        fromProto(avroOrder.address),
        fromProto(avroOrder.status),
        avroOrder.createdAt,
        avroOrder.updatedAt,
        avroOrder.description
    )
}

fun online.codeisfun.serialization.commons.OrderItem.toAvro(): online.codeisfun.serialization.avro.OrderItem {
    return online.codeisfun.serialization.avro.OrderItem.newBuilder()
        .setItemId(itemId)
        .setName(name)
        .setQuantity(quantity)
        .setPrice(price)
        .build()
}

fun fromProto(orderItem: online.codeisfun.serialization.avro.OrderItem): OrderItem {
    return OrderItem(
        orderItem.itemId,
        orderItem.name,
        orderItem.quantity,
        orderItem.price
    )
}

fun online.codeisfun.serialization.commons.Customer.toAvro(): online.codeisfun.serialization.avro.Customer {
    return online.codeisfun.serialization.avro.Customer.newBuilder()
        .setCustomerId(customerId)
        .setName(name)
        .setEmail(email)
        .setPhone(phone)
        .build()
}

fun fromProto(customer: online.codeisfun.serialization.avro.Customer): Customer {
    return Customer(
        customer.customerId,
        customer.name,
        customer.email,
        customer.phone
    )
}

fun online.codeisfun.serialization.commons.Address.toAvro(): online.codeisfun.serialization.avro.Address {
    return online.codeisfun.serialization.avro.Address.newBuilder()
        .setCountry(country)
        .setState(state)
        .setCity(city)
        .setStreet(street)
        .setPostalCode(postalCode)
        .build()
}

fun fromProto(address: online.codeisfun.serialization.avro.Address): Address {
    return Address(
        address.country,
        address.state,
        address.city,
        address.street,
        address.postalCode
    )
}

fun online.codeisfun.serialization.commons.OrderStatus.toAvro(): online.codeisfun.serialization.avro.OrderStatus {
    return when (this) {
        online.codeisfun.serialization.commons.OrderStatus.PENDING -> online.codeisfun.serialization.avro.OrderStatus.PENDING
        online.codeisfun.serialization.commons.OrderStatus.DELIVERED -> online.codeisfun.serialization.avro.OrderStatus.DELIVERED
        online.codeisfun.serialization.commons.OrderStatus.SHIPPED -> online.codeisfun.serialization.avro.OrderStatus.SHIPPED
        online.codeisfun.serialization.commons.OrderStatus.CANCELED -> online.codeisfun.serialization.avro.OrderStatus.CANCELED
        online.codeisfun.serialization.commons.OrderStatus.CONFIRMED -> online.codeisfun.serialization.avro.OrderStatus.CONFIRMED
    }
}

fun fromProto(status: online.codeisfun.serialization.avro.OrderStatus): OrderStatus {
    return when (status) {
        online.codeisfun.serialization.avro.OrderStatus.PENDING -> OrderStatus.PENDING
        online.codeisfun.serialization.avro.OrderStatus.DELIVERED -> OrderStatus.DELIVERED
        online.codeisfun.serialization.avro.OrderStatus.SHIPPED -> OrderStatus.SHIPPED
        online.codeisfun.serialization.avro.OrderStatus.CANCELED -> OrderStatus.CANCELED
        online.codeisfun.serialization.avro.OrderStatus.CONFIRMED -> OrderStatus.CONFIRMED
    }
}

fun online.codeisfun.serialization.commons.HtmlObjectContainer.toAvro(): online.codeisfun.serialization.avro.HtmlObjectContainer {
    return online.codeisfun.serialization.avro.HtmlObjectContainer.newBuilder()
        .setContent(content)
        .build()
}

fun fromAvro(avro: online.codeisfun.serialization.avro.HtmlObjectContainer): HtmlObjectContainer {
    return HtmlObjectContainer(
        avro.content
    )
}

