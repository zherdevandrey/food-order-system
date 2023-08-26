package com.food.order.valueobject

import java.util.*

data class RestaurantId(val id: UUID) : BaseId<UUID>(id) {

}