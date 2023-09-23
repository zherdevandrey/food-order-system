package com.food.order.port.input.service

import com.food.order.dto.create.CreateOrderCommand
import com.food.order.dto.create.CreateOrderResponse
import com.food.order.dto.track.TrackOrderQuery
import com.food.order.dto.track.TrackOrderResponse
import jakarta.validation.Valid

interface OrderApplicationService {

    fun createOrder(@Valid createOrderCommand: CreateOrderCommand): CreateOrderResponse
    fun trackOrder(@Valid trackOrderQuery: TrackOrderQuery): TrackOrderResponse

}