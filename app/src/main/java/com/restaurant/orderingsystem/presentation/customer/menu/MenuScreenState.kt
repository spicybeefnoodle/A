// presentation/customer/menu/MenuScreenState.kt
package com.restaurant.orderingsystem.presentation.customer.menu

import com.restaurant.orderingsystem.domain.model.MenuItem

data class MenuScreenState(
    val categories: List<String> = emptyList(),
    val selectedCategory: String? = null,
    val menuItems: List<MenuItem> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val searchQuery: String = "",
    val filterOptions: FilterOptions = FilterOptions()
)

data class FilterOptions(
    val isVegetarian: Boolean = false,
    val isVegan: Boolean = false,
    val isGlutenFree: Boolean = false,
    val selectedAllergens: Set<String> = emptySet()
)
