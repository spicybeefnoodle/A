package com.restaurant.orderingsystem.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.restaurant.orderingsystem.data.datasource.local.entity.TableEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TableDao {
    @Query("SELECT * FROM tables WHERE id = :tableId")
    suspend fun getTable(tableId: String): TableEntity?

    @Query("SELECT * FROM tables")
    fun getAllTables(): Flow<List<TableEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTables(tables: List<TableEntity>)

    @Query("UPDATE tables SET status = :status WHERE id = :tableId")
    suspend fun updateTableStatus(tableId: String, status: String)

    @Query("UPDATE tables SET customerRequests = :updatedRequests WHERE id = :tableId")
    suspend fun updateCustomerRequests(tableId: String, updatedRequests: List<TableEntity.CustomerRequest>)
}