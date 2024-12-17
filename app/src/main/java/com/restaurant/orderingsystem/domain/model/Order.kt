package com.restaurant.orderingsystem.domain.model

data class Order(
    val id: String,
    val tableId: String,
    val items: List<OrderItem>,
    val status: OrderStatus,
    val timestamp: Long,
    val specialInstructions: String? = null,
    val totalAmount: Double
)

data class OrderItem(
    val menuItem: MenuItem,
    val quantity: Int,
    val customizations: List<String>,
    val specialInstructions: String? = null
)

enum class OrderStatus {
    PENDING,
    CONFIRMED,
    IN_PREPARATION,
    READY,
    DELIVERED,
    CANCELLED
}
