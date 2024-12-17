package com.restaurant.orderingsystem.domain.model

data class CustomerRequest(
    val id: String,
    val tableId: String,
    val type: RequestType,
    val description: String?,
    val timestamp: Long,
    val status: RequestStatus
)

enum class RequestType {
    CALL_SERVER,
    REFILL_DRINKS,
    GENERAL_ASSISTANCE,
    PAYMENT_REQUEST
}

enum class RequestStatus {
    PENDING,
    IN_PROGRESS,
    COMPLETED,
    CANCELLED
}