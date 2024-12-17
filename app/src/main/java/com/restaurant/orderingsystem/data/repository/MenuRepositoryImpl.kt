package com.restaurant.orderingsystem.data.repository

import com.restaurant.orderingsystem.data.datasource.local.LocalMenuDataSource
import com.restaurant.orderingsystem.data.datasource.remote.RemoteMenuDataSource
import com.restaurant.orderingsystem.data.mapper.toDomain
import com.restaurant.orderingsystem.data.mapper.toDto
import com.restaurant.orderingsystem.domain.model.MenuItem
import com.restaurant.orderingsystem.domain.repository.MenuRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MenuRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteMenuDataSource,
    private val localDataSource: LocalMenuDataSource
) : MenuRepository {

    override suspend fun getAllMenuItems(): Flow<List<MenuItem>> {
        return remoteDataSource.getAllMenuItems().map { dtos ->
            dtos.map { it.toDomain() }
        }
    }

    override suspend fun getMenuItem(id: String): MenuItem? {
        return remoteDataSource.getMenuItem(id)?.toDomain()
    }

    override suspend fun updateMenuItemAvailability(id: String, isAvailable: Boolean) {
        remoteDataSource.updateMenuItemAvailability(id, isAvailable)
        localDataSource.updateMenuItemAvailability(id, isAvailable)
    }
}