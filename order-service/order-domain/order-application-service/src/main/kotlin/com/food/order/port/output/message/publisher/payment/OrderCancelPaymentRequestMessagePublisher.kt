package com.food.order.port.output.message.publisher.payment

import com.food.order.event.OrderCancelledEvent
import com.food.order.event.publisher.DomainEventPublisher

interface OrderCancelPaymentRequestMessagePublisher : DomainEventPublisher<OrderCancelledEvent> {

}