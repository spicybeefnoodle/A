package com.restaurant.orderingsystem.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.restaurant.orderingsystem.data.datasource.local.dao.MenuDao
import com.restaurant.orderingsystem.data.datasource.local.dao.OrderDao
import com.restaurant.orderingsystem.data.datasource.local.dao.TableDao
import com.restaurant.orderingsystem.data.datasource.local.entity.MenuItemEntity
import com.restaurant.orderingsystem.data.datasource.local.entity.OrderEntity
import com.restaurant.orderingsystem.data.datasource.local.entity.TableEntity
import com.restaurant.orderingsystem.data.datasource.local.entity.StringListConverter
import com.restaurant.orderingsystem.data.datasource.local.entity.CustomizationOptionsConverter
import com.restaurant.orderingsystem.data.datasource.local.entity.OrderItemsConverter
import com.restaurant.orderingsystem.data.datasource.local.entity.CustomerRequestsConverter

@Database(
    entities = [
        MenuItemEntity::class,
        OrderEntity::class,
        TableEntity::class
    ],
    version = 1
)
@TypeConverters(
    StringListConverter::class,
    CustomizationOptionsConverter::class,
    OrderItemsConverter::class,
    CustomerRequestsConverter::class
)
abstract class RestaurantDatabase : RoomDatabase() {
    abstract fun menuDao(): MenuDao
    abstract fun orderDao(): OrderDao
    abstract fun tableDao(): TableDao
}
