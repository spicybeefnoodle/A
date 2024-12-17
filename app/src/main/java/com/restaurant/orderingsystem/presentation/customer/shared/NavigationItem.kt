package com.restaurant.orderingsystem.presentation.customer.shared

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Phone
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavigationItem(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Menu : NavigationItem(
        route = "menu",
        title = "Menu",
        icon = Icons.Default.Menu
    )

    object Requests : NavigationItem(
        route = "requests",
        title = "Request Assistance",
        icon = Icons.Default.Phone
    )

    object ViewOrder : NavigationItem(
        route = "view_order",
        title = "View Order",
        icon = Icons.Default.ShoppingCart
    )

    object Payment : NavigationItem(
        route = "payment",
        title = "Request Bill",
        icon = Icons.Default.Person
    )
}