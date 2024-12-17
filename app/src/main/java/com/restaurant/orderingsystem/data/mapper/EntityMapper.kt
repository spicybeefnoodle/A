package com.restaurant.orderingsystem.data.mapper

import com.restaurant.orderingsystem.data.datasource.local.entity.*
import com.restaurant.orderingsystem.data.dto.*

fun MenuItemEntity.toDto(): MenuItemDto {
    return MenuItemDto(
        id = id,
        name = name,
        description = description,
        price = price,
        category = category,
        imageUrl = imageUrl,
        allergens = allergens,
        isAvailable = isAvailable,
        customizationOptions = customizationOptions.map { entityOption ->
            CustomizationOptionDto(
                id = entityOption.id,
                name = entityOption.name,
                options = entityOption.options,
                maxSelections = entityOption.maxSelections
            )
        }
    )
}

fun OrderEntity.toDto(): OrderDto {
    return OrderDto(
        id = id,
        tableId = tableId,
        items = items.map { entityItem ->
            OrderItemDto(
                menuItemId = entityItem.menuItemId,
                quantity = entityItem.quantity,
                customizations = entityItem.customizations,
                specialInstructions = entityItem.specialInstructions
            )
        },
        status = status,
        timestamp = timestamp,
        specialInstructions = specialInstructions,
        totalAmount = totalAmount
    )
}

fun TableEntity.toDto(): TableDto {
    return TableDto(
        id = id,
        number = number,
        status = status,
        currentOrderId = currentOrderId,
        customerRequests = customerRequests.map { entityRequest ->
            CustomerRequestDto(
                id = entityRequest.id,
                tableId = entityRequest.tableId,
                type = entityRequest.type,
                description = entityRequest.description,
                timestamp = entityRequest.timestamp,
                status = entityRequest.status
            )
        }
    )
}

// Add the reverse mappings (toEntity functions) as well
fun MenuItemDto.toEntity(): MenuItemEntity {
    return MenuItemEntity(
        id = id,
        name = name,
        description = description,
        price = price,
        category = category,
        imageUrl = imageUrl,
        allergens = allergens,
        isAvailable = isAvailable,
        customizationOptions = customizationOptions.map { dto ->
            MenuItemEntity.CustomizationOption(
                id = dto.id,
                name = dto.name,
                options = dto.options,
                maxSelections = dto.maxSelections
            )
        }
    )
}

fun OrderDto.toEntity(): OrderEntity {
    return OrderEntity(
        id = id,
        tableId = tableId,
        items = items.map { dto ->
            OrderEntity.OrderItem(
                menuItemId = dto.menuItemId,
                quantity = dto.quantity,
                customizations = dto.customizations,
                specialInstructions = dto.specialInstructions
            )
        },
        status = status,
        timestamp = timestamp,
        specialInstructions = specialInstructions,
        totalAmount = totalAmount
    )
}

fun TableDto.toEntity(): TableEntity {
    return TableEntity(
        id = id,
        number = number,
        status = status,
        currentOrderId = currentOrderId,
        customerRequests = customerRequests.map { dto ->
            TableEntity.CustomerRequest(
                id = dto.id,
                tableId = dto.tableId,
                type = dto.type,
                description = dto.description,
                timestamp = dto.timestamp,
                status = dto.status
            )
        }
    )
}
