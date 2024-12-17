package com.restaurant.orderingsystem.data.mapper

import com.restaurant.orderingsystem.data.dto.MenuItemDto
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
        customizationOptions = customizationOptions.map { it.toDomain() }
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
        customizationOptions = customizationOptions.map { it.toDto() }
    )
}

private fun MenuItemDto.CustomizationOptionDto.toDomain(): CustomizationOption {
    return CustomizationOption(
        id = id,
        name = name,
        options = options,
        maxSelections = maxSelections
    )
}

private fun CustomizationOption.toDto(): MenuItemDto.CustomizationOptionDto {
    return MenuItemDto.CustomizationOptionDto(
        id = id,
        name = name,
        options = options,
        maxSelections = maxSelections
    )
}