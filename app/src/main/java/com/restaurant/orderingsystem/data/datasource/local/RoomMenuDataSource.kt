package com.restaurant.orderingsystem.data.datasource.local

import com.restaurant.orderingsystem.data.datasource.local.dao.MenuDao
import com.restaurant.orderingsystem.data.dto.MenuItemDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RoomMenuDataSource @Inject constructor(
    private val menuDao: MenuDao
) : LocalMenuDataSource {

    override fun getAllMenuItems(): Flow<List<MenuItemDto>> {
        return menuDao.getAllMenuItems().map { entities ->
            entities.map { it.toDto() }
        }
    }

    override suspend fun getMenuItem(id: String): MenuItemDto? {
        return menuDao.getMenuItem(id)?.toDto()
    }

    override suspend fun insertMenuItems(items: List<MenuItemDto>) {
        menuDao.insertMenuItems(items.map { it.toEntity() })
    }

    override suspend fun updateMenuItemAvailability(id: String, isAvailable: Boolean) {
        menuDao.updateMenuItemAvailability(id, isAvailable)
    }
}