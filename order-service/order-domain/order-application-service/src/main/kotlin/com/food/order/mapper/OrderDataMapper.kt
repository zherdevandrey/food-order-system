package com.food.order.mapper

import com.food.order.dto.create.CreateOrderCommand
import com.food.order.dto.create.CreateOrderResponse
import com.food.order.dto.create.OrderAddress
import com.food.order.dto.create.OrderItem
import com.food.order.dto.track.TrackOrderResponse
import com.food.order.entity.Order
import com.food.order.entity.Product
import com.food.order.entity.Restaurant
import com.food.order.valueobject.*
import org.springframework.stereotype.Component
import java.util.*
import java.util.stream.Collectors

@Component
class OrderDataMapper {
    fun createOrderCommandToRestaurant(createOrderCommand: CreateOrderCommand): Restaurant {
        val orderItems = createOrderCommand.orderItems
        val productIds =
            orderItems.stream().map { ProductId(it.productId) }.map { Product(it) }.collect(Collectors.toList())

        return Restaurant(RestaurantId(createOrderCommand.restaurantId), productIds)
    }

    fun createOrderCommandToOrder(createOrderCommand: CreateOrderCommand): Order {
        return Order(
            CustomerId(createOrderCommand.customerId),
            RestaurantId(createOrderCommand.restaurantId),
            orderAddressToStreetAddress(createOrderCommand.orderAddress),
            Money(createOrderCommand.price),
            orderItemsToOrderItemEntities(createOrderCommand.orderItems)
        )
    }

    private fun orderItemsToOrderItemEntities(orderItems: List<OrderItem>): List<com.food.order.entity.OrderItem> {
        return orderItems.stream().map { orderItem ->
                com.food.order.entity.OrderItem(
                    Product(ProductId(orderItem.productId)),
                    orderItem.quantity,
                    Money(orderItem.price),
                    Money(orderItem.subTotal)
                )
            }.collect(Collectors.toList())
    }

    private fun orderAddressToStreetAddress(orderAddress: OrderAddress): StreetAddress {
        return StreetAddress(UUID.randomUUID(), orderAddress.street, orderAddress.postalCode, orderAddress.city)
    }

    fun orderToCreateOrderResponse(order: Order, message: String): CreateOrderResponse {
        return CreateOrderResponse(
            order.getNotNullOrderId().id, order.orderStatus, message
        )
    }

    fun orderToTrackOrderResponse(order: Order): TrackOrderResponse {
        return TrackOrderResponse(
            order.trackingId.id, order.orderStatus, order.failureMessages
        )
    }


}