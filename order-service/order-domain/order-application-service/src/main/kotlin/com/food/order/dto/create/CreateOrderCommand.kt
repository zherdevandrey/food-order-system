package com.food.order.dto.create

import jakarta.validation.constraints.NotNull
import java.math.BigDecimal
import java.util.*

data class CreateOrderCommand(
    @field:NotNull
    val customerId: UUID,
    @field:NotNull
    val restaurantId: UUID,
    @field:NotNull
    val price: BigDecimal,
    @field:NotNull
    val orderItems: List<OrderItem>,
    @field:NotNull
    val orderAddress: OrderAddress
)