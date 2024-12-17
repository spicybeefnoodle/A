package com.restaurant.orderingsystem.presentation.customer

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.restaurant.orderingsystem.presentation.customer.menu.MenuScreen
import com.restaurant.orderingsystem.presentation.customer.shared.NavigationItem

@Composable
fun CustomerMainScreen(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()

    Row(modifier = modifier.fillMaxSize()) {
        CustomerNavigationRail(
            navController = navController,
            modifier = Modifier.width(NavigationConstants.NAVIGATION_RAIL_WIDTH)
        )

        CustomerNavigationContent(
            navController = navController,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
private fun CustomerNavigationRail(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val currentRoute = navController.currentBackStackEntry?.destination?.route
    val navigationItems = remember { getNavigationItems() }

    NavigationRail(modifier = modifier) {
        Column {
            navigationItems.forEach { item ->
                NavigationRailItem(
                    selected = currentRoute == item.route,
                    onClick = {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.startDestinationId)
                            launchSingleTop = true
                        }
                    },
                    icon = { Icon(item.icon!!, contentDescription = item.title) },
                    label = { Text(item.title) }
                )
            }
        }
    }
}

@Composable
private fun CustomerNavigationContent(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = NavigationItem.Menu.route,
        modifier = modifier
    ) {
        composable(NavigationItem.Menu.route) {
            MenuScreen(
                onNavigate = { route -> navController.navigate(route) }
            )
        }
        // Additional screen composables will be added here
    }
}

private object NavigationConstants {
    val NAVIGATION_RAIL_WIDTH = 280.dp
}

private fun getNavigationItems() = listOf(
    NavigationItem.Menu,
    NavigationItem.Requests,
    NavigationItem.ViewOrder,
    NavigationItem.Payment
)