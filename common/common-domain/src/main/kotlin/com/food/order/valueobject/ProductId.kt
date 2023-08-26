package com.food.order.valueobject

import java.util.*

data class ProductId(val id: UUID) : BaseId<UUID>(id) {

}