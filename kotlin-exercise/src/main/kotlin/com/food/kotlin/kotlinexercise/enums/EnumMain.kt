package com.food.kotlin.kotlinexercise.enums


class KotlinExerciseApplication
fun main() {
    val description = getDescription(Color.BLUE);
    println(description)
}

fun getDescription(color: Color):String {
    return when(color) {
        Color.BLUE -> Color.BLUE.name
        Color.ORANGE -> Color.ORANGE.name
    }
}
