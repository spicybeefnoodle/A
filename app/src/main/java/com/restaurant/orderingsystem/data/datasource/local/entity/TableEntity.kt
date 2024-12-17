package com.restaurant.orderingsystem.data.datasource.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Entity(tableName = "tables")
data class TableEntity(
    @PrimaryKey
    val id: String,
    val number: Int,
    val status: String,
    val currentOrderId: String?,
    @TypeConverters(CustomerRequestsConverter::class)
    val customerRequests: List<CustomerRequest>
) {
    data class CustomerRequest(
        val id: String,
        val tableId: String,
        val type: String,
        val description: String?,
        val timestamp: Long,
        val status: String
    )
}

class CustomerRequestsConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromString(value: String): List<TableEntity.CustomerRequest> {
        val listType = object : TypeToken<List<TableEntity.CustomerRequest>>() {}.type
        return gson.fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<TableEntity.CustomerRequest>): String {
        return gson.toJson(list)
    }
}