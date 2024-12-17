package com.restaurant.orderingsystem.domain.repository

import com.restaurant.orderingsystem.domain.model.CustomerRequest
import com.restaurant.orderingsystem.domain.model.Table
import com.restaurant.orderingsystem.domain.model.TableStatus
import kotlinx.coroutines.flow.Flow

interface TableRepository {
    suspend fun getTable(tableId: String): Table?
    suspend fun getAllTables(): Flow<List<Table>>
    suspend fun updateTableStatus(tableId: String, status: TableStatus)
    suspend fun addCustomerRequest(tableId: String, request: CustomerRequest)
}