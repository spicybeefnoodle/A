package com.restaurant.orderingsystem.data.dto

data class OrderDto(
    val id: String = "",
    val tableId: String = "",
    val items: List<OrderItemDto> = emptyList(),
    val status: String = "",
    val timestamp: Long = 0L,
    val specialInstructions: String? = null,
    val totalAmount: Double = 0.0
)
