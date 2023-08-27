package com.food.order.port.input.message.listener.customer

import com.food.order.dto.message.CustomerModel

interface CustomerMessageListener {
    fun customerCreated(customerModel: CustomerModel)
}