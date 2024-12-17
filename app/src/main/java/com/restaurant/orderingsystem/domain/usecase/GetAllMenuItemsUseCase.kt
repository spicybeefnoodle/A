package com.restaurant.orderingsystem.domain.usecase

import com.restaurant.orderingsystem.domain.model.MenuItem
import com.restaurant.orderingsystem.domain.repository.MenuRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllMenuItemsUseCase @Inject constructor(
    private val menuRepository: MenuRepository
) {
    suspend operator fun invoke(): Flow<List<MenuItem>> {
        return menuRepository.getAllMenuItems()
    }
}