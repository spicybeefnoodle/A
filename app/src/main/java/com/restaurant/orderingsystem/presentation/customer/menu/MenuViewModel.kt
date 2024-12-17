// presentation/customer/menu/MenuViewModel.kt
package com.restaurant.orderingsystem.presentation.customer.menu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.restaurant.orderingsystem.domain.model.MenuItem
import com.restaurant.orderingsystem.domain.usecase.GetAllMenuItemsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    private val getAllMenuItemsUseCase: GetAllMenuItemsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(MenuScreenState())
    val state: StateFlow<MenuScreenState> = _state

    init {
        loadMenuItems()
    }

    private fun loadMenuItems() {
        viewModelScope.launch {
            _state.update { currentState -> currentState.copy(isLoading = true) }
            try {
                getAllMenuItemsUseCase().collect { menuItems: List<MenuItem> ->
                    val categories = menuItems.map { it.category }.distinct().sorted()
                    _state.update { currentState ->
                        currentState.copy(
                            menuItems = menuItems,
                            categories = categories,
                            selectedCategory = categories.firstOrNull(),
                            isLoading = false
                        )
                    }
                }
            } catch (e: Exception) {
                _state.update { currentState ->
                    currentState.copy(
                        error = "Failed to load menu items: ${e.message}",
                        isLoading = false
                    )
                }
            }
        }
    }

    fun selectCategory(category: String) {
        _state.update { it.copy(selectedCategory = category) }
    }

    fun updateSearchQuery(query: String) {
        _state.update { it.copy(searchQuery = query) }
    }

    fun updateFilterOptions(options: FilterOptions) {
        _state.update { it.copy(filterOptions = options) }
    }

    fun getFilteredMenuItems(): List<MenuItem> {
        val currentState = state.value
        return currentState.menuItems
            .filter { item ->
                val matchesCategory = currentState.selectedCategory == null ||
                        item.category == currentState.selectedCategory
                val matchesSearch = item.name.contains(currentState.searchQuery, ignoreCase = true) ||
                        item.description.contains(currentState.searchQuery, ignoreCase = true)
                val matchesFilters = true // Implement filter logic based on your requirements

                matchesCategory && matchesSearch && matchesFilters
            }
    }
}