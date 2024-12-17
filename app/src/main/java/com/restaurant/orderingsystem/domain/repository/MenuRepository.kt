package com.restaurant.orderingsystem.domain.repository

import com.restaurant.orderingsystem.domain.model.MenuItem
import kotlinx.coroutines.flow.Flow

interface MenuRepository {
    suspend fun getAllMenuItems(): Flow<List<MenuItem>>
    suspend fun getMenuItem(id: String): MenuItem?
    suspend fun updateMenuItemAvailability(id: String, isAvailable: Boolean)
}