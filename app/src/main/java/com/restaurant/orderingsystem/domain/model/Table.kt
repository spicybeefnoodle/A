package com.restaurant.orderingsystem.domain.model

data class Table(
    val id: String,
    val number: Int,
    val status: TableStatus,
    val currentOrderId: String? = null,
    val customerRequests: List<CustomerRequest> = emptyList()
)

enum class TableStatus {
    AVAILABLE,
    OCCUPIED,
    NEEDS_ASSISTANCE,
    PAYMENT_REQUESTED
}