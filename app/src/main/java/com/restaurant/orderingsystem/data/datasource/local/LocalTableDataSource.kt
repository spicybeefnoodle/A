package com.restaurant.orderingsystem.data.datasource.local

import com.restaurant.orderingsystem.data.dto.CustomerRequestDto
import com.restaurant.orderingsystem.data.dto.TableDto
import kotlinx.coroutines.flow.Flow

interface LocalTableDataSource {
    suspend fun getTable(tableId: String): TableDto?
    fun getAllTables(): Flow<List<TableDto>>
    suspend fun insertTables(tables: List<TableDto>)
    suspend fun updateTableStatus(tableId: String, status: String)
    suspend fun addCustomerRequest(tableId: String, request: CustomerRequestDto)
}