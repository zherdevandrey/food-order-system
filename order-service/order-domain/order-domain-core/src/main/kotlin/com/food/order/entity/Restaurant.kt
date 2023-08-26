package com.food.order.entity

import com.food.order.valueobject.RestaurantId

class Restaurant(id: RestaurantId,
    var products: List<Product>,
    var isActive: Boolean

) : AggregateRoot<RestaurantId>(id) {


}