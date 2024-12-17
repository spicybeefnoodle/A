package com.restaurant.orderingsystem.data.mapper

import com.restaurant.orderingsystem.data.dto.OrderDto
import com.restaurant.orderingsystem.domain.model.MenuItem
import com.restaurant.orderingsystem.domain.model.Order
import com.restaurant.orderingsystem.domain.model.OrderItem
import com.restaurant.orderingsystem.domain.model.OrderStatus

suspend fun OrderDto.toDomain(getMenuItem: suspend (String) -> MenuItem?): Order {
    return Order(
        id = id,
        tableId = tableId,
        items = items.mapNotNull { it.toDomain(getMenuItem) },
        status = OrderStatus.valueOf(status),
        timestamp = timestamp,
        specialInstructions = specialInstructions,
        totalAmount = totalAmount
    )
}

fun Order.toDto(): OrderDto {
    return OrderDto(
        id = id,
        tableId = tableId,
        items = items.map { it.toDto() },
        status = status.name,
        timestamp = timestamp,
        specialInstructions = specialInstructions,
        totalAmount = totalAmount
    )
}

private suspend fun OrderDto.OrderItemDto.toDomain(getMenuItem: suspend (String) -> MenuItem?): OrderItem? {
    val menuItem = getMenuItem(menuItemId) ?: return null
    return OrderItem(
        menuItem = menuItem,
        quantity = quantity,
        customizations = customizations,
        specialInstructions = specialInstructions
    )
}

private fun OrderItem.toDto(): OrderDto.OrderItemDto {
    return OrderDto.OrderItemDto(
        menuItemId = menuItem.id,
        quantity = quantity,
        customizations = customizations,
        specialInstructions = specialInstructions
    )
}