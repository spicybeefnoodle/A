package com.restaurant.orderingsystem.data.repository

import com.restaurant.orderingsystem.data.datasource.local.LocalOrderDataSource
import com.restaurant.orderingsystem.data.datasource.remote.RemoteOrderDataSource
import com.restaurant.orderingsystem.data.mapper.toDomain
import com.restaurant.orderingsystem.data.mapper.toDto
import com.restaurant.orderingsystem.domain.model.Order
import com.restaurant.orderingsystem.domain.model.OrderStatus
import com.restaurant.orderingsystem.domain.repository.MenuRepository
import com.restaurant.orderingsystem.domain.repository.OrderRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class OrderRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteOrderDataSource,
    private val localDataSource: LocalOrderDataSource,
    private val menuRepository: MenuRepository
) : OrderRepository {

    override suspend fun createOrder(order: Order) {
        remoteDataSource.createOrder(order.toDto())
        localDataSource.createOrder(order.toDto())
    }

    override suspend fun getOrder(orderId: String): Order? {
        return remoteDataSource.getOrder(orderId)?.toDomain { id ->
            menuRepository.getMenuItem(id)
        }
    }

    override suspend fun getOrdersForTable(tableId: String): Flow<List<Order>> {
        return remoteDataSource.getOrdersForTable(tableId).map { dtos ->
            dtos.mapNotNull { dto ->
                dto.toDomain { id ->
                    menuRepository.getMenuItem(id)
                }
            }
        }
    }

    override suspend fun updateOrderStatus(orderId: String, status: OrderStatus) {
        remoteDataSource.updateOrderStatus(orderId, status.name)
        localDataSource.updateOrderStatus(orderId, status.name)
    }
}
