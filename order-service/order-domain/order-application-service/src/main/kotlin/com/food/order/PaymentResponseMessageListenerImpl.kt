package com.food.order

import com.food.order.dto.message.PaymentResponse
import com.food.order.port.input.message.listener.payment.PaymentResponseMessageListener
import org.springframework.stereotype.Service

@Service
class PaymentResponseMessageListenerImpl: PaymentResponseMessageListener {
    override fun paymentCompleted(paymentResponse: PaymentResponse?) {
        TODO("Not yet implemented")
    }

    override fun paymentCancelled(paymentResponse: PaymentResponse?) {
        TODO("Not yet implemented")
    }
}