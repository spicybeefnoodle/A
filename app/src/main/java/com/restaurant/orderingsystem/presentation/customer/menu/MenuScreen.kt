package com.restaurant.orderingsystem.presentation.customer.menu

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.restaurant.orderingsystem.domain.model.MenuItem
import com.restaurant.orderingsystem.presentation.customer.menu.components.CategoryItem
import com.restaurant.orderingsystem.presentation.customer.menu.components.MenuItemCard

@Composable
fun MenuScreen(
    viewModel: MenuViewModel = hiltViewModel(),
    onNavigate: (String) -> Unit
) {
    val state by viewModel.state.collectAsState()

    Scaffold { paddingValues ->
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            NavigationRail(
                modifier = Modifier.width(280.dp)
            ) {
                CategoryList(
                    categories = state.categories,
                    selectedCategory = state.selectedCategory,
                    onCategorySelected = viewModel::selectCategory
                )
            }

            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
            ) {
                MenuContent(
                    menuItems = viewModel.getFilteredMenuItems(),
                    onAddToOrder = { /* Implement order handling */ }
                )
            }
        }
    }
}

@Composable
private fun CategoryList(
    categories: List<String>,
    selectedCategory: String?,
    onCategorySelected: (String) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxHeight(),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(
            items = categories,
            key = { it }
        ) { category ->
            CategoryItem(
                category = category,
                isSelected = category == selectedCategory,
                onClick = { onCategorySelected(category) }
            )
        }
    }
}

@Composable
private fun MenuContent(
    menuItems: List<MenuItem>,
    onAddToOrder: (MenuItem) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 300.dp),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(
            items = menuItems,
            key = { it.id }
        ) { menuItem ->
            MenuItemCard(
                menuItem = menuItem,
                onAddToOrder = { onAddToOrder(menuItem) }
            )
        }
    }
}