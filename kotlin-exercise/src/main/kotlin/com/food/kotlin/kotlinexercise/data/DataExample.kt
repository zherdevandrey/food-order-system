package com.food.kotlin.kotlinexercise.data

// note that for data class for auto generated functions
// used only fields from primary constructor
data class DataExample(var firstName:String) {

    var secondName:String = "$firstName second"

}