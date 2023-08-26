package com.food.order.entity

import com.food.order.exception.OrderDomainException
import com.food.order.valueobject.*
import java.math.BigDecimal
import java.util.*
import java.util.function.BinaryOperator
import java.util.stream.Collectors
import kotlin.collections.ArrayList

class Order(
    id: OrderId,
    val customerId: CustomerId,
    val restaurantId: RestaurantId,
    val streetAddress: StreetAddress,
    val price: Money,
    val items: List<OrderItem>

) : AggregateRoot<OrderId>(id) {
    private lateinit var trackingId: TrackingId
    private lateinit var orderStatus: OrderStatus
    private lateinit var failureMessages: java.util.ArrayList<String>

    fun pay() {
        if (orderStatus != OrderStatus.PENDING) {
            throw OrderDomainException("Order is not in correct state for pay operation")
        }
        orderStatus = OrderStatus.PENDING
    }

    fun approve() {
        if (orderStatus != OrderStatus.PAID) {
            throw OrderDomainException("Order is not in correct state for approve operation")
        }
        orderStatus = OrderStatus.APPROVED
    }

    fun cancel(failureMessages:List<String>) {
        if (orderStatus != OrderStatus.PAID || orderStatus != OrderStatus.PENDING) {
            throw OrderDomainException("Order is not in correct state for cancel operation")
        }
        orderStatus = OrderStatus.CANCELLED
        updateFailureMessages(failureMessages)
    }

    fun initCancel(failureMessages:List<String>) {
        if (orderStatus != OrderStatus.PAID) {
            throw OrderDomainException("Order is not in correct state for initCancel operation")
        }
        orderStatus = OrderStatus.CANCELLING
        updateFailureMessages(failureMessages)
    }

    private fun updateFailureMessages(failureMessages: List<String>) {
        if (this.failureMessages != null && failureMessages != null) {
            this.failureMessages
                .addAll(
                    failureMessages
                        .stream()
                        .filter { value -> !value.isEmpty() }
                        .collect(Collectors.toList())
                )
        }
        if (this.failureMessages == null) {
            this.failureMessages = ArrayList(failureMessages)
        }
    }

    fun validateOrder() {
        if (orderStatus != null || id != null) {
            throw OrderDomainException("Order is not in correct state for initialization")
        }
    }

    private fun validatePrice() {
        if (price == null || !price.isGreaterThenZero()) {
            throw OrderDomainException("Total price must be greater then zero")
        }
    }

    private fun validateItemsPrice() {
        val totalOrderItemsPrice:Money = items.stream()
            .map(OrderItem::subTotal)
            .reduce(Money.ZERO) { money: Money, other: Money -> money.add(other) }

        if (totalOrderItemsPrice != price) {
            throw OrderDomainException("Order price $price is not equals to order items price $totalOrderItemsPrice")
        }
    }

    private fun validateItemPrice(item: OrderItem) {
        if (!item.isPriceValid()) {
            throw OrderDomainException("Order item price ${item.price.amount} is not valid for product ${item.product.id.id}")
        }
    }

    internal fun initializeOrder() {
        id = OrderId(UUID.randomUUID())
        trackingId = TrackingId(UUID.randomUUID())
        orderStatus = OrderStatus.PENDING
        initializeOrderItems()
    }

    private fun initializeOrderItems() {
        for ((id, item) in items.withIndex()) {
            item.initializeOrderItem(super.id, OrderItemId(id.toLong()))
        }
    }

}