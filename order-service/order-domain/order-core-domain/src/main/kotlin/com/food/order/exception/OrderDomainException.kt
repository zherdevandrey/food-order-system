package com.food.order.exception

class OrderDomainException(message: String, ex:Throwable?): DomainException(message, ex) {
    constructor(message: String) : this(message, null) {

    }
}