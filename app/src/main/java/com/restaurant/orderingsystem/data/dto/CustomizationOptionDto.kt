package com.restaurant.orderingsystem.data.dto

data class CustomizationOptionDto(
    val id: String = "",
    val name: String = "",
    val options: List<String> = emptyList(),
    val maxSelections: Int = 1
)
