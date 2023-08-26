package com.food.order.entity

import com.food.order.valueobject.CustomerId
import java.util.UUID

class Customer(id: CustomerId): AggregateRoot<CustomerId>(id) {
}