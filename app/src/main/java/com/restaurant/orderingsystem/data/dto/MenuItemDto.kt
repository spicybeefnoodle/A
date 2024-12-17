package com.restaurant.orderingsystem.data.dto

data class MenuItemDto(
    val id: String = "",
    val name: String = "",
    val description: String = "",
    val price: Double = 0.0,
    val category: String = "",
    val imageUrl: String? = null,
    val allergens: List<String> = emptyList(),
    val isAvailable: Boolean = true,
    val customizationOptions: List<CustomizationOptionDto> = emptyList()
)
