package com.restaurant.orderingsystem.data.datasource.remote

import com.google.firebase.firestore.FirebaseFirestore
import com.restaurant.orderingsystem.data.dto.MenuItemDto
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseMenuDataSource @Inject constructor(
    private val firestore: FirebaseFirestore
) : RemoteMenuDataSource {

    override fun getAllMenuItems(): Flow<List<MenuItemDto>> = callbackFlow {
        val subscription = firestore.collection("menu")
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    // Handle error
                    return@addSnapshotListener
                }

                val menuItems = snapshot?.documents?.mapNotNull { doc ->
                    doc.toObject(MenuItemDto::class.java)?.copy(id = doc.id)
                } ?: emptyList()

                trySend(menuItems)
            }

        awaitClose { subscription.remove() }
    }

    override suspend fun getMenuItem(id: String): MenuItemDto? {
        return try {
            val doc = firestore.collection("menu").document(id).get().await()
            doc.toObject(MenuItemDto::class.java)?.copy(id = doc.id)
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun updateMenuItemAvailability(id: String, isAvailable: Boolean) {
        try {
            firestore.collection("menu").document(id)
                .update("isAvailable", isAvailable)
                .await()
        } catch (e: Exception) {
            // Handle error
        }
    }
}