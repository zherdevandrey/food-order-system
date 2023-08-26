package com.food.kotlin.kotlinexercise.sealed


class KotlinExerciseApplication

fun main() {

}

fun getDescription(example: SealedExample): String {
    return when (example) {
        is SealedImpl1 -> "SealedImpl1"
        is SealedImpl2 -> "SealedImpl2"
    }
}
