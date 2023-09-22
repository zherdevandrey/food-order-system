package com.food.order

import com.food.order.dto.message.RestaurantApprovalResponse
import com.food.order.port.input.message.listener.restaurantapproval.RestaurantApprovalResponseMessageListener
import org.springframework.stereotype.Service

@Service
class RestaurantApprovalResponseMessageListenerImpl: RestaurantApprovalResponseMessageListener {
    override fun orderApproved(restaurantApprovalResponse: RestaurantApprovalResponse) {
        TODO("Not yet implemented")
    }

    override fun orderRejected(restaurantApprovalResponse: RestaurantApprovalResponse) {
        TODO("Not yet implemented")
    }
}