package com.food.order.entity

import com.food.order.valueobject.CustomerId

class Customer(id: CustomerId): AggregateRoot<CustomerId>(id) {
    private var username: String? = null
    private var firstName: String? = null
    private var lastName: String? = null
}