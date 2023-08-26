package com.food.order.entity

import com.food.order.valueobject.Money
import com.food.order.valueobject.OrderId
import com.food.order.valueobject.OrderItemId

class OrderItem(
    id: OrderItemId,
    var orderId: OrderId,
    var product: Product,
    var quantity: Int,
    var price: Money,
    var subTotal: Money

) : BaseEntity<OrderItemId>(id) {
    fun initializeOrderItem(orderId: OrderId, orderItemId: OrderItemId) {
        this.orderId = orderId
        this.id = orderItemId
    }

    fun isPriceValid(): Boolean {
        return price.isGreaterThenZero()
                && price.equals(product.price)
                && price.multiply(quantity).equals(subTotal)
    }
}