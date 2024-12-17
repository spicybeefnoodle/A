package com.restaurant.orderingsystem.data.mapper

import com.restaurant.orderingsystem.data.dto.MenuItemDto
import com.restaurant.orderingsystem.data.dto.CustomizationOptionDto
import com.restaurant.orderingsystem.domain.model.MenuItem
import com.restaurant.orderingsystem.domain.model.CustomizationOption

fun MenuItemDto.toDomain(): MenuItem {
    return MenuItem(
        id = id,
        name = name,
        description = description,
        price = price,
        category = category,
        imageUrl = imageUrl,
        allergens = allergens,
        isAvailable = isAvailable,
        customizationOptions = customizationOptions.map { option ->
            CustomizationOption(
                id = option.id,
                name = option.name,
                options = option.options,
                maxSelections = option.maxSelections
            )
        }
    )
}

fun MenuItem.toDto(): MenuItemDto {
    return MenuItemDto(
        id = id,
        name = name,
        description = description,
        price = price,
        category = category,
        imageUrl = imageUrl,
        allergens = allergens,
        isAvailable = isAvailable,
        customizationOptions = customizationOptions.map { option ->
            CustomizationOptionDto(
                id = option.id,
                name = option.name,
                options = option.options,
                maxSelections = option.maxSelections
            )
        }
    )
}