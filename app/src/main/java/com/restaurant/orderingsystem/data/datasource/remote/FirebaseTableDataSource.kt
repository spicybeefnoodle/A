package com.restaurant.orderingsystem.data.datasource.remote

import com.google.firebase.firestore.FirebaseFirestore
import com.restaurant.orderingsystem.data.dto.CustomerRequestDto
import com.restaurant.orderingsystem.data.dto.TableDto
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseTableDataSource @Inject constructor(
    private val firestore: FirebaseFirestore
) : RemoteTableDataSource {

    override suspend fun getTable(tableId: String): TableDto? {
        return try {
            val doc = firestore.collection("tables").document(tableId).get().await()
            doc.toObject(TableDto::class.java)?.copy(id = doc.id)
        } catch (e: Exception) {
            null
        }
    }

    override fun getAllTables(): Flow<List<TableDto>> = callbackFlow {
        val subscription = firestore.collection("tables")
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    // Handle error
                    return@addSnapshotListener
                }

                val tables = snapshot?.documents?.mapNotNull { doc ->
                    doc.toObject(TableDto::class.java)?.copy(id = doc.id)
                } ?: emptyList()

                trySend(tables)
            }

        awaitClose { subscription.remove() }
    }

    override suspend fun updateTableStatus(tableId: String, status: String) {
        try {
            firestore.collection("tables").document(tableId)
                .update("status", status)
                .await()
        } catch (e: Exception) {
            // Handle error
        }
    }

    override suspend fun addCustomerRequest(tableId: String, request: CustomerRequestDto) {
        try {
            firestore.collection("tables").document(tableId)
                .collection("requests").document(request.id)
                .set(request)
                .await()
        } catch (e: Exception) {
            // Handle error
        }
    }
}