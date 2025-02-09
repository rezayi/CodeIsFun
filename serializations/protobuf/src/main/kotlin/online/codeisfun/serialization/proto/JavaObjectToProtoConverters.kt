package online.codeisfun.serialization.proto

import online.codeisfun.serialization.commons.*
import online.codeisfun.serialization.proto.proto_models.HtmlContainerProto
import online.codeisfun.serialization.proto.proto_models.OrderProto

fun Order.toProto(): OrderProto.Order {
    return OrderProto.Order.newBuilder()
        .setOrderId(orderId)
        .setCustomer(customer.toProto())
        .addAllItems(items.stream().map(OrderItem::toProto).toList())
        .setAddress(address.toProto())
        .setOrderStatus(status.toProto())
        .setCreatedAt(createdAt)
        .setUpdatedAt(updatedAt)
        .setDescription(description)
        .build()
}

fun fromProto(proto: OrderProto.Order): Order {
    return Order(
        proto.orderId,
        fromProto(proto.customer),
        proto.itemsList.stream().map { item -> fromProto(item) }.toList(),
        fromProto(proto.getAddress()),
        fromProto(proto.orderStatus),
        proto.createdAt,
        proto.updatedAt,
        proto.getDescription()
    )
}

fun OrderItem.toProto(): OrderProto.Order.OrderItem {
    return OrderProto.Order.OrderItem.newBuilder()
        .setItemId(itemId)
        .setName(name)
        .setQuantity(quantity)
        .setPrice(price)
        .build()
}

fun fromProto(orderItem: OrderProto.Order.OrderItem): OrderItem {
    return OrderItem(
        orderItem.itemId,
        orderItem.name,
        orderItem.quantity,
        orderItem.price
    )
}

fun Customer.toProto(): OrderProto.Order.Customer {
    return OrderProto.Order.Customer.newBuilder()
        .setCustomerId(customerId)
        .setName(name)
        .setEmail(email)
        .setPhone(phone)
        .build()
}

fun fromProto(customer: OrderProto.Order.Customer): Customer {
    return Customer(
        customer.customerId,
        customer.name,
        customer.email,
        customer.phone
    )
}

fun Address.toProto(): OrderProto.Order.Address {
    return OrderProto.Order.Address.newBuilder()
        .setCountry(country)
        .setState(state)
        .setCity(city)
        .setStreet(street)
        .setPostalCode(postalCode)
        .build()
}

fun fromProto(address: OrderProto.Order.Address): Address {
    return Address(
        address.getCountry(),
        address.getState(),
        address.getCity(),
        address.getStreet(),
        address.getPostalCode()
    )
}

fun OrderStatus.toProto(): OrderProto.Order.OrderStatus {
    return when (this) {
        OrderStatus.PENDING -> OrderProto.Order.OrderStatus.PENDING
        OrderStatus.DELIVERED -> OrderProto.Order.OrderStatus.DELIVERED
        OrderStatus.SHIPPED -> OrderProto.Order.OrderStatus.SHIPPED
        OrderStatus.CANCELED -> OrderProto.Order.OrderStatus.CANCELED
        OrderStatus.CONFIRMED -> OrderProto.Order.OrderStatus.CONFIRMED
    }
}

fun fromProto(status: OrderProto.Order.OrderStatus): OrderStatus {
    return when (status) {
        OrderProto.Order.OrderStatus.PENDING -> OrderStatus.PENDING
        OrderProto.Order.OrderStatus.DELIVERED -> OrderStatus.DELIVERED
        OrderProto.Order.OrderStatus.SHIPPED -> OrderStatus.SHIPPED
        OrderProto.Order.OrderStatus.CANCELED -> OrderStatus.CANCELED
        OrderProto.Order.OrderStatus.CONFIRMED -> OrderStatus.CONFIRMED
        OrderProto.Order.OrderStatus.UNRECOGNIZED -> OrderStatus.PENDING
    }
}

fun HtmlObjectContainer.toProto(): HtmlContainerProto.HtmlContainer {
    return HtmlContainerProto.HtmlContainer.newBuilder()
        .setContent(content)
        .build()
}

fun fromProto(proto: HtmlContainerProto.HtmlContainer): HtmlObjectContainer {
    return HtmlObjectContainer(
        proto.getContent()
    )
}

