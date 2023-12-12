package com.food.order.event

import com.food.order.entity.Order
import java.time.ZonedDateTime

class OrderPaidEvent(order: Order,
                     createdAt: ZonedDateTime
): OrderEvent(order, createdAt)