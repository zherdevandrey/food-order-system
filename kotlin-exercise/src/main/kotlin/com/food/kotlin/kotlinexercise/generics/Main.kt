package com.food.kotlin.kotlinexercise.generics

class Main

fun main() {

    val something = GenericExample("TEST").something("TEST")
    println(something)
}

class GenericExample<T>(private var value: T) {

    fun something(value: T): List<T> {
        return listOf(value)
    }
}