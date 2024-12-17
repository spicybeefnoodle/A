package com.restaurant.orderingsystem.data.datasource.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Entity(tableName = "orders")
data class OrderEntity(
    @PrimaryKey
    val id: String,
    val tableId: String,
    @TypeConverters(OrderItemsConverter::class)
    val items: List<OrderItem>,
    val status: String,
    val timestamp: Long,
    val specialInstructions: String?,
    val totalAmount: Double
) {
    data class OrderItem(
        val menuItemId: String,
        val quantity: Int,
        @TypeConverters(StringListConverter::class)
        val customizations: List<String>,
        val specialInstructions: String?
    )
}

class OrderItemsConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromString(value: String): List<OrderEntity.OrderItem> {
        val listType = object : TypeToken<List<OrderEntity.OrderItem>>() {}.type
        return gson.fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<OrderEntity.OrderItem>): String {
        return gson.toJson(list)
    }
}