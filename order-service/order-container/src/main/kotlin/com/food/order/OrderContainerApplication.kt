package com.food.order

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class OrderContainerApplication

fun main(args: Array<String>) {
    runApplication<OrderContainerApplication>(*args)
}
