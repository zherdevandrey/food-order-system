package com.food.kotlin.kotlinexercise.companionObject

class CompanionObjectExample {

    companion object:CompanionInterface {
        fun printName(name:String) {
            println(name)
        }

        override fun companionInterface() {
            println("companionInterface")
        }
    }

}