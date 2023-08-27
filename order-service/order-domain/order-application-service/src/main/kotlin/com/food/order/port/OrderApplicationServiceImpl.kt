package com.food.order.port

import com.food.order.dto.create.CreateOrderCommand
import com.food.order.dto.create.CreateOrderResponse
import com.food.order.dto.track.TrackOrderQuery
import com.food.order.dto.track.TrackOrderResponse
import com.food.order.port.input.service.OrderApplicationService
import com.food.order.service.impl.OrderDomainServiceImpl
import lombok.RequiredArgsConstructor
import lombok.extern.slf4j.Slf4j
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.validation.annotation.Validated

@Component
@Validated
@Slf4j
@RequiredArgsConstructor
internal class OrderApplicationServiceImpl(
    private val orderCreateCommandHandler: OrderCreateCommandHandler,
    private val orderTrackCommandHandler: OrderTrackCommandHandler
):OrderApplicationService {

    private val log = LoggerFactory.getLogger(OrderApplicationService::class.java)

    override fun createOrder(createOrderCommand: CreateOrderCommand): CreateOrderResponse? {
        return orderCreateCommandHandler.createOrder(createOrderCommand)
    }

    override fun trackOrder(trackOrderQuery: TrackOrderQuery): TrackOrderResponse? {
        return orderTrackCommandHandler.trackOrder(trackOrderQuery)
    }
}