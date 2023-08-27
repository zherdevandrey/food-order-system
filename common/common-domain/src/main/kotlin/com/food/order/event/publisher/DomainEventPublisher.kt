package com.food.order.event.publisher

interface DomainEventPublisher<T> {

    fun publish(event: T)

}