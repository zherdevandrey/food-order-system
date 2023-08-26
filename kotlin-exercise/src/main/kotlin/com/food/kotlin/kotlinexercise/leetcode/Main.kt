package com.food.kotlin.kotlinexercise.leetcode

import java.util.*

class Main

fun main() {



}


//[0,0,1,1,1,2,2,3,3,4]
//[0 1 2 3 4 ]
fun removeDuplicates(inNums: IntArray): Int {
    var nums = inNums

    var prevVal = inNums[0]
    var currentIndex = 1
    for (i in 1 until nums.size) {
        if (prevVal != nums[i]) {
            inNums[currentIndex] = nums[i]
            prevVal = nums[i]
            currentIndex++
        }
    }
    return currentIndex
}