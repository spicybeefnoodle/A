package com.restaurant.orderingsystem.data.datasource.local

import com.restaurant.orderingsystem.data.dto.MenuItemDto
import kotlinx.coroutines.flow.Flow

interface LocalMenuDataSource {
    fun getAllMenuItems(): Flow<List<MenuItemDto>>
    suspend fun getMenuItem(id: String): MenuItemDto?
    suspend fun insertMenuItems(items: List<MenuItemDto>)
    suspend fun updateMenuItemAvailability(id: String, isAvailable: Boolean)
}