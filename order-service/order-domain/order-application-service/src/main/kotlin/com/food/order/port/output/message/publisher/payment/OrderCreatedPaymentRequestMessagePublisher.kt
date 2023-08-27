package com.food.order.port.output.message.publisher.payment

import com.food.order.event.OrderCreatedEvent
import com.food.order.event.publisher.DomainEventPublisher

interface OrderCreatedPaymentRequestMessagePublisher: DomainEventPublisher<OrderCreatedEvent> {

}