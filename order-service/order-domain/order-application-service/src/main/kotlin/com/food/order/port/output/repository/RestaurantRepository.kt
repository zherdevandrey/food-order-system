package com.food.order.port.output.repository

import com.food.order.entity.Restaurant
import java.util.*

interface RestaurantRepository {

    fun findRestaurantInformation(restaurant: Restaurant): Restaurant?
}