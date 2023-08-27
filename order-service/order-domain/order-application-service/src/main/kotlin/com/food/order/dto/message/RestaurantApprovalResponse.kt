package com.food.order.dto.message

import com.food.order.valueobject.OrderApprovalStatus
import java.time.Instant

class RestaurantApprovalResponse (
    val id: String,
    val sagaId: String,
    val orderId: String,
    val restaurantId: String,
    val createdAt: Instant,
    val orderApprovalStatus: OrderApprovalStatus,
    val failureMessages: List<String>
)
