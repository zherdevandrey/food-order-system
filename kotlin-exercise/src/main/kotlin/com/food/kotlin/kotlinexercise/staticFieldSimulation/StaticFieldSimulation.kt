package com.food.kotlin.kotlinexercise.staticFieldSimulation

class StaticFieldSimulation {

    companion object {
         @JvmStatic val SOME_STATIC_FIELD:String
            get():String = "SOME STATIC FIELD"
    }

}