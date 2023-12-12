package com.food.order.event

import com.food.order.entity.Order
import java.time.ZonedDateTime

abstract class OrderEvent(
    var order: Order,
    var createdAt: ZonedDateTime
) : DomainEvent<Order>