package com.food.order.port.output.message.publisher.restaurantapproval

import com.food.order.event.OrderPaidEvent
import com.food.order.event.publisher.DomainEventPublisher

interface OrderPayedRestaurantApprovalRequestMessagePublisher : DomainEventPublisher<OrderPaidEvent> {}