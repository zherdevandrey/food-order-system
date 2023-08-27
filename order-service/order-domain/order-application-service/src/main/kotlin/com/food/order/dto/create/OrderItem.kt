package com.food.order.dto.create

import java.math.BigDecimal
import java.util.*

data class OrderItem(val productId: UUID,
                     val quantity:Int,
                     val price: BigDecimal,
                     val subTotal:BigDecimal)
