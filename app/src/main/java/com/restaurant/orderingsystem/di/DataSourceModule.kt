package com.restaurant.orderingsystem.di

import com.restaurant.orderingsystem.data.datasource.local.LocalMenuDataSource
import com.restaurant.orderingsystem.data.datasource.local.LocalOrderDataSource
import com.restaurant.orderingsystem.data.datasource.local.LocalTableDataSource
import com.restaurant.orderingsystem.data.datasource.local.RoomMenuDataSource
import com.restaurant.orderingsystem.data.datasource.local.RoomOrderDataSource
import com.restaurant.orderingsystem.data.datasource.local.RoomTableDataSource
import com.restaurant.orderingsystem.data.datasource.remote.FirebaseMenuDataSource
import com.restaurant.orderingsystem.data.datasource.remote.FirebaseOrderDataSource
import com.restaurant.orderingsystem.data.datasource.remote.FirebaseTableDataSource
import com.restaurant.orderingsystem.data.datasource.remote.RemoteMenuDataSource
import com.restaurant.orderingsystem.data.datasource.remote.RemoteOrderDataSource
import com.restaurant.orderingsystem.data.datasource.remote.RemoteTableDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun bindRemoteMenuDataSource(
        dataSource: FirebaseMenuDataSource
    ): RemoteMenuDataSource

    @Binds
    abstract fun bindLocalMenuDataSource(
        dataSource: RoomMenuDataSource
    ): LocalMenuDataSource

    @Binds
    abstract fun bindRemoteOrderDataSource(
        dataSource: FirebaseOrderDataSource
    ): RemoteOrderDataSource

    @Binds
    abstract fun bindLocalOrderDataSource(
        dataSource: RoomOrderDataSource
    ): LocalOrderDataSource

    @Binds
    abstract fun bindRemoteTableDataSource(
        dataSource: FirebaseTableDataSource
    ): RemoteTableDataSource

    @Binds
    abstract fun bindLocalTableDataSource(
        dataSource: RoomTableDataSource
    ): LocalTableDataSource
}
