package com.food.order.valueobject

import java.math.BigDecimal
import java.math.RoundingMode

data class Money(val amount: BigDecimal) {

    companion object {
        var ZERO = Money(BigDecimal.ZERO)
    }

    fun isGreaterThenZero(): Boolean {
        return this.amount > BigDecimal.ZERO
    }

    fun isGreaterThenZero(value: BigDecimal): Boolean {
        return this.amount > value
    }

    fun add(value: Money): Money {
        return Money(scale(this.amount.add(value.amount)))
    }

    fun subtract(value: BigDecimal): Money {
        return Money(scale(this.amount.subtract(value)))
    }

    fun multiply(value: Int): Money {
        return Money(scale(this.amount.multiply(BigDecimal(value))))
    }

    private fun scale(value: BigDecimal): BigDecimal {
        return value.setScale(2, RoundingMode.HALF_EVEN)
    }
}