package com.restaurant.orderingsystem.domain.repository

import com.restaurant.orderingsystem.domain.model.Order
import com.restaurant.orderingsystem.domain.model.OrderStatus
import kotlinx.coroutines.flow.Flow

interface OrderRepository {
    suspend fun createOrder(order: Order)
    suspend fun getOrder(orderId: String): Order?
    suspend fun getOrdersForTable(tableId: String): Flow<List<Order>>
    suspend fun updateOrderStatus(orderId: String, status: OrderStatus)
}