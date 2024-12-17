package com.restaurant.orderingsystem.data.dto

data class CustomerRequestDto(
    val id: String = "",
    val tableId: String = "",
    val type: String = "",
    val description: String? = null,
    val timestamp: Long = 0L,
    val status: String = ""
)
