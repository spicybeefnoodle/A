package com.restaurant.orderingsystem.data.datasource.local

import com.restaurant.orderingsystem.data.datasource.local.dao.OrderDao
import com.restaurant.orderingsystem.data.dto.OrderDto
import com.restaurant.orderingsystem.data.mapper.toEntity
import com.restaurant.orderingsystem.data.mapper.toDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RoomOrderDataSource @Inject constructor(
    private val orderDao: OrderDao
) : LocalOrderDataSource {

    override suspend fun createOrder(order: OrderDto) {
        orderDao.createOrder(order.toEntity())
    }

    override suspend fun getOrder(orderId: String): OrderDto? {
        return orderDao.getOrder(orderId)?.toDto()
    }

    override fun getOrdersForTable(tableId: String): Flow<List<OrderDto>> {
        return orderDao.getOrdersForTable(tableId).map { entities ->
            entities.map { it.toDto() }
        }
    }

    override suspend fun updateOrderStatus(orderId: String, status: String) {
        orderDao.updateOrderStatus(orderId, status)
    }
}