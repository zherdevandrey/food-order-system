package com.food.order.dto.message

import com.food.order.valueobject.PaymentStatus
import java.math.BigDecimal
import java.time.Instant

data class PaymentResponse(
    val id: String,
    val sagaId: String,
    val orderId: String,
    val paymentId: String,
    val customerId: String,
    val price: BigDecimal,
    val createdAt: Instant,
    val paymentStatus: PaymentStatus,
    val failureMessages: List<String>
)