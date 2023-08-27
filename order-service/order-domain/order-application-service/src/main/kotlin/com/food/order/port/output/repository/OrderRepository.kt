package com.food.order.port.output.repository

import com.food.order.entity.Order
import com.food.order.valueobject.OrderId
import com.food.order.valueobject.TrackingId
import java.util.*

interface OrderRepository {

    fun save(order: Order): Order
    fun findById(trackingId: OrderId): Order?
    fun findByTrackingId(trackingId: TrackingId): Order?

}