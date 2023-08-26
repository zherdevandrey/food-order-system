package com.food.order.valueobject

import java.util.*

data class OrderId(val id: UUID) : BaseId<UUID>(id) {

}