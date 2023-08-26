package com.food.kotlin.kotlinexercise.companionObject
class Main


//important that kotlin doesn't have static
fun main() {

    CompanionObjectExample.printName("123")
    CompanionObjectExample.companionInterface()
    CompanionObjectExample.extensionFun("json")

}

fun CompanionObjectExample.Companion.extensionFun(json:String) {
    println("json $json")
}
