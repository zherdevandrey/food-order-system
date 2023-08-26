package com.food.order.service

import com.food.order.entity.Order
import com.food.order.entity.Restaurant
import com.food.order.event.OrderCancelledEvent
import com.food.order.event.OrderCreatedEvent
import com.food.order.event.OrderPaidEvent

interface OrderDomainService {
    fun validateAndInitiateOrder(order: Order, restaurant: Restaurant): OrderCreatedEvent
    fun payOrder(order: Order): OrderPaidEvent
    fun approve(order: Order)
    fun cancelOrderPayment(order: Order, failureMessages: List<String>): OrderCancelledEvent
    fun cancelOrder(order: Order, failureMessages: List<String>)
}
