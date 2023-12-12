package com.food.order.valueobject

import java.util.*

data class StreetAddress(
    val id: UUID,
    val street: String,
    val postalCode: String,
    val city: String)