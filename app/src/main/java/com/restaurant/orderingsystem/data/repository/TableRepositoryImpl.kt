package com.restaurant.orderingsystem.data.repository

import com.restaurant.orderingsystem.data.datasource.local.LocalTableDataSource
import com.restaurant.orderingsystem.data.datasource.remote.RemoteTableDataSource
import com.restaurant.orderingsystem.data.mapper.toDomain
import com.restaurant.orderingsystem.data.mapper.toDto
import com.restaurant.orderingsystem.domain.model.CustomerRequest
import com.restaurant.orderingsystem.domain.model.Table
import com.restaurant.orderingsystem.domain.model.TableStatus
import com.restaurant.orderingsystem.domain.repository.TableRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TableRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteTableDataSource,
    private val localDataSource: LocalTableDataSource
) : TableRepository {

    override suspend fun getTable(tableId: String): Table? {
        return remoteDataSource.getTable(tableId)?.toDomain()
    }

    override suspend fun getAllTables(): Flow<List<Table>> {
        return remoteDataSource.getAllTables().map { dtos ->
            dtos.map { it.toDomain() }
        }
    }

    override suspend fun updateTableStatus(tableId: String, status: TableStatus) {
        remoteDataSource.updateTableStatus(tableId, status.name)
        localDataSource.updateTableStatus(tableId, status.name)
    }

    override suspend fun addCustomerRequest(tableId: String, request: CustomerRequest) {
        remoteDataSource.addCustomerRequest(tableId, request.toDto())
        localDataSource.addCustomerRequest(tableId, request.toDto())
    }
}