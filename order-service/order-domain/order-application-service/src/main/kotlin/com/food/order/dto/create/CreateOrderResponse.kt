package com.food.order.dto.create

import com.food.order.valueobject.OrderStatus
import java.util.*

data class CreateOrderResponse(val orderTrackingId: UUID,
                               val orderStatus: OrderStatus,
                               val message: String)
