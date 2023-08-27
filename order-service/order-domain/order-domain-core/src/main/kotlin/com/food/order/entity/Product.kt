package com.food.order.entity

import com.food.order.valueobject.Money
import com.food.order.valueobject.ProductId

class Product(
    id: ProductId
) : BaseEntity<ProductId>(id) {

    lateinit var name: String
    lateinit var price: Money

    constructor(id: ProductId, name: String, price: Money) : this(id) {
        this.id = id
        this.name = name
        this.price = price
    }

    fun updateWithConfirmedNameAndPrice(name: String, price: Money) {
        this.name = name
        this.price = price
    }
}