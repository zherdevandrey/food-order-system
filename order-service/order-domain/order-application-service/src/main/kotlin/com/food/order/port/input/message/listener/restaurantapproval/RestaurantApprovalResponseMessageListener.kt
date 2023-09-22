package com.food.order.port.input.message.listener.restaurantapproval

import com.food.order.dto.message.RestaurantApprovalResponse

interface RestaurantApprovalResponseMessageListener {
    fun orderApproved(restaurantApprovalResponse: RestaurantApprovalResponse)
    fun orderRejected(restaurantApprovalResponse: RestaurantApprovalResponse)
}