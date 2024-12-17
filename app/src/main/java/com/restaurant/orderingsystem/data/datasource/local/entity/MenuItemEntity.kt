package com.restaurant.orderingsystem.data.datasource.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Entity(tableName = "menu_items")
data class MenuItemEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val description: String,
    val price: Double,
    val category: String,
    val imageUrl: String?,
    @TypeConverters(StringListConverter::class)
    val allergens: List<String>,
    val isAvailable: Boolean,
    @TypeConverters(CustomizationOptionsConverter::class)
    val customizationOptions: List<CustomizationOption>
) {
    data class CustomizationOption(
        val id: String,
        val name: String,
        val options: List<String>,
        val maxSelections: Int
    )
}

class StringListConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromString(value: String): List<String> {
        val listType = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<String>): String {
        return gson.toJson(list)
    }
}

class CustomizationOptionsConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromString(value: String): List<MenuItemEntity.CustomizationOption> {
        val listType = object : TypeToken<List<MenuItemEntity.CustomizationOption>>() {}.type
        return gson.fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<MenuItemEntity.CustomizationOption>): String {
        return gson.toJson(list)
    }
}