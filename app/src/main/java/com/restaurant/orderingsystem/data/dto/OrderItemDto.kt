package com.restaurant.orderingsystem.data.dto

data class OrderItemDto(
    val menuItemId: String = "",
    val quantity: Int = 0,
    val customizations: List<String> = emptyList(),
    val specialInstructions: String? = null
)