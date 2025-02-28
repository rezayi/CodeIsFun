package online.codeisfun.apis.graphql.data

data class Customer(
    val customerId: Int,
    val name: String?,
    val email: String?,
    val phone: String?
)