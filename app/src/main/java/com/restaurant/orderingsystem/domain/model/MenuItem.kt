package com.restaurant.orderingsystem.domain.model

data class MenuItem(
    val id: String,
    val name: String,
    val description: String,
    val price: Double,
    val category: String,
    val imageUrl: String?,
    val allergens: List<String>,
    val isAvailable: Boolean = true,
    val customizationOptions: List<CustomizationOption> = emptyList()
)

data class CustomizationOption(
    val id: String,
    val name: String,
    val options: List<String>,
    val maxSelections: Int = 1
)