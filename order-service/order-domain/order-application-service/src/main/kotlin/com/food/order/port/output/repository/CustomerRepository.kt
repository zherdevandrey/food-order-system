package com.food.order.port.output.repository

import com.food.order.entity.Customer
import java.util.*

interface CustomerRepository {

    fun save(customer: Customer): Customer
    fun findCustomer(customerId: UUID): Customer?

}