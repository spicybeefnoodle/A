package com.restaurant.orderingsystem.data.datasource.local

import com.restaurant.orderingsystem.data.datasource.local.dao.TableDao
import com.restaurant.orderingsystem.data.datasource.local.entity.TableEntity
import com.restaurant.orderingsystem.data.dto.CustomerRequestDto
import com.restaurant.orderingsystem.data.dto.TableDto
import com.restaurant.orderingsystem.data.mapper.toEntity
import com.restaurant.orderingsystem.data.mapper.toDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class RoomTableDataSource @Inject constructor(
    private val tableDao: TableDao
) : LocalTableDataSource {

    override suspend fun getTable(tableId: String): TableDto? {
        return tableDao.getTable(tableId)?.toDto()
    }

    override fun getAllTables(): Flow<List<TableDto>> {
        return tableDao.getAllTables().map { entities ->
            entities.map { it.toDto() }
        }
    }

    override suspend fun insertTables(tables: List<TableDto>) {
        tableDao.insertTables(tables.map { it.toEntity() })
    }

    override suspend fun updateTableStatus(tableId: String, status: String) {
        tableDao.updateTableStatus(tableId, status)
    }

    override suspend fun addCustomerRequest(tableId: String, request: CustomerRequestDto) {
        val table = tableDao.getTable(tableId)
        table?.let { currentTable ->
            val updatedRequests = currentTable.customerRequests + TableEntity.CustomerRequest(
                id = request.id,
                tableId = request.tableId,
                type = request.type,
                description = request.description,
                timestamp = request.timestamp,
                status = request.status
            )
            tableDao.updateCustomerRequests(tableId, updatedRequests)
        }
    }
}