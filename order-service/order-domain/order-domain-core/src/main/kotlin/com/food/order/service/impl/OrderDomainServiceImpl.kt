package com.food.order.service.impl

import com.food.order.entity.Order
import com.food.order.entity.Restaurant
import com.food.order.event.OrderCancelledEvent
import com.food.order.event.OrderCreatedEvent
import com.food.order.event.OrderPaidEvent
import com.food.order.exception.OrderDomainException
import com.food.order.service.OrderDomainService
import lombok.extern.slf4j.Slf4j
import org.slf4j.LoggerFactory
import java.lang.Boolean
import java.time.ZoneId
import java.time.ZonedDateTime
import kotlin.String

@Slf4j
class OrderDomainServiceImpl : OrderDomainService {

    private val log = LoggerFactory.getLogger(OrderDomainServiceImpl::class.java)
    private val utc = "UTC"

    override fun validateAndInitiateOrder(order: Order, restaurant: Restaurant): OrderCreatedEvent {
        validateRestaurant(restaurant)
        setOrderProductInformation(order, restaurant)
        order.validateOrder()
        order.initializeOrder()
        log.info("Order with id {} initialize successfully", order.id)
        return OrderCreatedEvent(order, ZonedDateTime.now(ZoneId.of(utc)))
    }

    override fun payOrder(order: Order): OrderPaidEvent {
        order.pay()
        log.info("Order with id {} paid successfully", order.id)
        return OrderPaidEvent(order, ZonedDateTime.now(ZoneId.of(utc)))
    }

    override fun approve(order: Order) {
        order.approve()
        log.info("Order with id {} approved successfully", order.id)
    }

    override fun cancelOrderPayment(order: Order, failureMessages: List<String>): OrderCancelledEvent {
        order.initCancel(failureMessages)
        log.info("Order with id {} cancelled successfully", order.id)
        return OrderCancelledEvent(order, ZonedDateTime.now(ZoneId.of(utc)))
    }

    override fun cancelOrder(order: Order, failureMessages: List<String>) {
        order.cancel(failureMessages)
        log.info("Order with id {} cancelled successfully", order.id)
    }

    private fun setOrderProductInformation(order:Order, restaurant:Restaurant) {
        order.items
            .forEach { orderItem ->
                restaurant.products.forEach { restaurantProduct ->
                    val currentProduct = orderItem.product
                    if (currentProduct == restaurantProduct) {
                        currentProduct.updateWithConfirmedNameAndPrice(
                            restaurantProduct.name,
                            restaurantProduct.price
                        )
                    }
                }
            }
    }

    private fun validateRestaurant(restaurant: Restaurant) {
        if (Boolean.FALSE == restaurant.isActive) {
            throw OrderDomainException(
                "Restaurant is not active, please try again later. Restaurant id: $restaurant.id()"
            )
        }
    }
}