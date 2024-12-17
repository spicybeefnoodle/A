package com.restaurant.orderingsystem.di

import com.restaurant.orderingsystem.data.repository.MenuRepositoryImpl
import com.restaurant.orderingsystem.data.repository.OrderRepositoryImpl
import com.restaurant.orderingsystem.data.repository.TableRepositoryImpl
import com.restaurant.orderingsystem.domain.repository.MenuRepository
import com.restaurant.orderingsystem.domain.repository.OrderRepository
import com.restaurant.orderingsystem.domain.repository.TableRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindMenuRepository(
        repository: MenuRepositoryImpl
    ): MenuRepository

    @Binds
    abstract fun bindOrderRepository(
        repository: OrderRepositoryImpl
    ): OrderRepository

    @Binds
    abstract fun bindTableRepository(
        repository: TableRepositoryImpl
    ): TableRepository
}
