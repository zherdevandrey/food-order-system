package com.food.order.valueobject

import java.util.*

data class CustomerId(val id: UUID) : BaseId<UUID>(id) {

}