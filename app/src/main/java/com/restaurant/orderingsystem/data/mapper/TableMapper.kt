package com.restaurant.orderingsystem.data.mapper

import com.restaurant.orderingsystem.data.dto.CustomerRequestDto
import com.restaurant.orderingsystem.data.dto.TableDto
import com.restaurant.orderingsystem.domain.model.CustomerRequest
import com.restaurant.orderingsystem.domain.model.RequestStatus
import com.restaurant.orderingsystem.domain.model.RequestType
import com.restaurant.orderingsystem.domain.model.Table
import com.restaurant.orderingsystem.domain.model.TableStatus

fun TableDto.toDomain(): Table {
    return Table(
        id = id,
        number = number,
        status = TableStatus.valueOf(status),
        currentOrderId = currentOrderId,
        customerRequests = customerRequests.map { it.toDomain() }
    )
}

fun Table.toDto(): TableDto {
    return TableDto(
        id = id,
        number = number,
        status = status.name,
        currentOrderId = currentOrderId,
        customerRequests = customerRequests.map { it.toDto() }
    )
}

fun CustomerRequestDto.toDomain(): CustomerRequest {
    return CustomerRequest(
        id = id,
        tableId = tableId,
        type = RequestType.valueOf(type),
        description = description,
        timestamp = timestamp,
        status = RequestStatus.valueOf(status)
    )
}

fun CustomerRequest.toDto(): CustomerRequestDto {
    return CustomerRequestDto(
        id = id,
        tableId = tableId,
        type = type.name,
        description = description,
        timestamp = timestamp,
        status = status.name
    )
}