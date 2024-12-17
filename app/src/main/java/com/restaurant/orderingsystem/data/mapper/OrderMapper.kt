package com.restaurant.orderingsystem.data.mapper

import com.restaurant.orderingsystem.data.dto.OrderDto
import com.restaurant.orderingsystem.data.dto.OrderItemDto
import com.restaurant.orderingsystem.domain.model.MenuItem
import com.restaurant.orderingsystem.domain.model.Order
import com.restaurant.orderingsystem.domain.model.OrderItem
import com.restaurant.orderingsystem.domain.model.OrderStatus

suspend fun OrderDto.toDomain(getMenuItem: suspend (String) -> MenuItem?): Order {
    return Order(
        id = id,
        tableId = tableId,
        items = items.mapNotNull { item ->
            val menuItem = getMenuItem(item.menuItemId) ?: return@mapNotNull null
            OrderItem(
                menuItem = menuItem,
                quantity = item.quantity,
                customizations = item.customizations,
                specialInstructions = item.specialInstructions
            )
        },
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
        items = items.map { item ->
            OrderItemDto(
                menuItemId = item.menuItem.id,
                quantity = item.quantity,
                customizations = item.customizations,
                specialInstructions = item.specialInstructions
            )
        },
        status = status.name,
        timestamp = timestamp,
        specialInstructions = specialInstructions,
        totalAmount = totalAmount
    )
}