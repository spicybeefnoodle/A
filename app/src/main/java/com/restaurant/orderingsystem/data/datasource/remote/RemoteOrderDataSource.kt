package com.restaurant.orderingsystem.data.datasource.remote

import com.restaurant.orderingsystem.data.dto.OrderDto
import kotlinx.coroutines.flow.Flow

interface RemoteOrderDataSource {
    suspend fun createOrder(order: OrderDto)
    suspend fun getOrder(orderId: String): OrderDto?
    fun getOrdersForTable(tableId: String): Flow<List<OrderDto>>
    suspend fun updateOrderStatus(orderId: String, status: String)
}