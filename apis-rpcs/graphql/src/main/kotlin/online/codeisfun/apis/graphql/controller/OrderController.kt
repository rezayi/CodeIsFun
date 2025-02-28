//package online.codeisfun.apis.graphql.controller
//
//import online.codeisfun.apis.graphql.data.Customer
//import org.springframework.graphql.data.method.annotation.Argument
//import org.springframework.graphql.data.method.annotation.QueryMapping
//import org.springframework.stereotype.Controller
//
//@Controller
//class OrderController {
//
//    @QueryMapping
//    fun getCustomer(@Argument customerId: Long): Customer {
//        return Customer(
//            customerId = customerId.toInt(),
//            name = "Customer $customerId",
//            email = "customer.email.${customerId}@gmail.com",
//            phone = "100-10000-${customerId}",
//        )
//    }
//
//    @QueryMapping
//    fun getCustomers(): List<Customer> {
//        return (1..10)
//            .map { customerId ->
//                Customer(
//                    customerId = customerId,
//                    name = "Customer $customerId",
//                    email = "customer.email.${customerId}@gmail.com",
//                    phone = "100-10000-${customerId}",
//                )
//            }
//    }
//
////    @QueryMapping
////    fun getOrder(orderId: Long): Order? {
////        return null
////    }
////
////    @QueryMapping
////    fun getOrders(): List<Order> {
////        return emptyList()
////    }
////
////    @MutationMapping
////    fun createOrder(orderInput: OrderInput) {
////
////    }
//}

fun decipher(ciphertext: String, knownWord: String): String {
    val cipheredKnownWord = ciphertext.split(' ')
        .filter { word -> word.length == knownWord.length }
        .firstOrNull { word -> isValidWord(word, knownWord) }
        ?: return "invalid"
    val distance = distance(cipheredKnownWord[0], knownWord[0])
    return shift(ciphertext, distance)
}

fun shift(ciphertext: String, distance: Int): String {
    return ciphertext
        .map { c -> shiftChar(c, distance) }
        .joinToString("")
}

fun shiftChar(c: Char, distance: Int): Char {
    return when {
        c in 'a'..'z' -> {
            var result = c - distance
            if (result < 'a')
                result = 'z' - ('a' - result) + 1
            result
        }

        c in 'A'..'Z' -> {
            var result = c - distance
            if (result < 'A')
                result = 'Z' - ('A' - result) + 1
            result
        }

        else -> c
    }
}

fun isValidWord(test: String, known: String): Boolean {
    val firstDistance = distance(test[0], known[0])
    for (i in 1 until test.length) {
        if (distance(test[i], known[i]) != firstDistance)
            return false
    }
    return true
}

fun distance(test: Char, known: Char): Int {
    return (test - known + 26) % 26
}

fun main() {
    val ciphertext = "vguvu ctg! a"
    val knownWord = "tests"

    val result = decipher(ciphertext, knownWord)
    println(result)
}
