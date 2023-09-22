package com.food.order.port.input.message.listener.payment

import com.food.order.dto.message.PaymentResponse

interface PaymentResponseMessageListener {
    fun paymentCompleted(paymentResponse: PaymentResponse?)
    fun paymentCancelled(paymentResponse: PaymentResponse?)
}