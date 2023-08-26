package com.food.order.exception

open class DomainException(message: String, ex:Throwable?) : RuntimeException(message, ex) {

    constructor(message: String) : this(message, null)

}