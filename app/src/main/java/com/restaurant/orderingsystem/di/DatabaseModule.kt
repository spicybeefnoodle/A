package com.restaurant.orderingsystem.di

import android.content.Context
import androidx.room.Room
import com.restaurant.orderingsystem.data.datasource.local.RestaurantDatabase
import com.restaurant.orderingsystem.data.datasource.local.dao.MenuDao
import com.restaurant.orderingsystem.data.datasource.local.dao.OrderDao
import com.restaurant.orderingsystem.data.datasource.local.dao.TableDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): RestaurantDatabase {
        return Room.databaseBuilder(
            context,
            RestaurantDatabase::class.java,
            "restaurant_database"
        ).build()
    }

    @Provides
    fun provideMenuDao(database: RestaurantDatabase): MenuDao = database.menuDao()

    @Provides
    fun provideOrderDao(database: RestaurantDatabase): OrderDao = database.orderDao()

    @Provides
    fun provideTableDao(database: RestaurantDatabase): TableDao = database.tableDao()
}
