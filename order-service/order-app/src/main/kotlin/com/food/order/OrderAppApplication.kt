package com.food.order

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class OrderAppApplication

fun main(args: Array<String>) {
    runApplication<OrderAppApplication>(*args)
}
