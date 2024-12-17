package com.restaurant.orderingsystem.data.dto

data class TableDto(
    val id: String = "",
    val number: Int = 0,
    val status: String = "",
    val currentOrderId: String? = null,
    val customerRequests: List<CustomerRequestDto> = emptyList()
)
