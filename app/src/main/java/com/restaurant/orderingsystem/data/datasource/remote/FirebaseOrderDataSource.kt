package com.restaurant.orderingsystem.data.datasource.remote

import com.google.firebase.firestore.FirebaseFirestore
import com.restaurant.orderingsystem.data.dto.OrderDto
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseOrderDataSource @Inject constructor(
    private val firestore: FirebaseFirestore
) : RemoteOrderDataSource {

    override suspend fun createOrder(order: OrderDto) {
        try {
            firestore.collection("orders").document(order.id)
                .set(order)
                .await()
        } catch (e: Exception) {
            // Handle error
        }
    }

    override suspend fun getOrder(orderId: String): OrderDto? {
        return try {
            val doc = firestore.collection("orders").document(orderId).get().await()
            doc.toObject(OrderDto::class.java)?.copy(id = doc.id)
        } catch (e: Exception) {
            null
        }
    }

    override fun getOrdersForTable(tableId: String): Flow<List<OrderDto>> = callbackFlow {
        val subscription = firestore.collection("orders")
            .whereEqualTo("tableId", tableId)
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    // Handle error
                    return@addSnapshotListener
                }

                val orders = snapshot?.documents?.mapNotNull { doc ->
                    doc.toObject(OrderDto::class.java)?.copy(id = doc.id)
                } ?: emptyList()

                trySend(orders)
            }

        awaitClose { subscription.remove() }
    }

    override suspend fun updateOrderStatus(orderId: String, status: String) {
        try {
            firestore.collection("orders").document(orderId)
                .update("status", status)
                .await()
        } catch (e: Exception) {
            // Handle error
        }
    }
}