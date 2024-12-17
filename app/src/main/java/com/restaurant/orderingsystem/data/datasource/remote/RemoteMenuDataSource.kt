package com.restaurant.orderingsystem.data.datasource.remote

import com.restaurant.orderingsystem.data.dto.MenuItemDto
import kotlinx.coroutines.flow.Flow

interface RemoteMenuDataSource {
    fun getAllMenuItems(): Flow<List<MenuItemDto>>
    suspend fun getMenuItem(id: String): MenuItemDto?
    suspend fun updateMenuItemAvailability(id: String, isAvailable: Boolean)
}