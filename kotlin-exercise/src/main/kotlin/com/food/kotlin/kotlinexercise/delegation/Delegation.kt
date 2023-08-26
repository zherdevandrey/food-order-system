package com.food.kotlin.kotlinexercise.delegation

class Delegation(repository: Repository): Repository by repository{
}