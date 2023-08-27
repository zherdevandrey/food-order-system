package com.food.order.dto.track

import com.food.order.valueobject.OrderStatus
import java.util.*

data class TrackOrderResponse(
    val orderTrackingId: UUID,
    val orderStatus: OrderStatus,
    val failureMessages: List<String>
)