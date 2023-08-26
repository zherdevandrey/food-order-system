package com.food.order.entity

import com.food.order.valueobject.Money
import com.food.order.valueobject.ProductId

class Product(
    id: ProductId,
    var name: String,
    var price: Money

) : BaseEntity<ProductId>(id) {

    fun updateWithConfirmedNameAndPrice(name: String, price: Money) {
        this.name = name
        this.price = price
    }
}