package com.food.order.port

import com.food.order.dto.track.TrackOrderQuery
import com.food.order.dto.track.TrackOrderResponse
import com.food.order.exception.OrderNotFoundException
import com.food.order.mapper.OrderDataMapper
import com.food.order.port.output.repository.OrderRepository
import com.food.order.valueobject.TrackingId
import lombok.extern.slf4j.Slf4j
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.util.Optional

@Component
@Slf4j
internal class OrderTrackCommandHandler(
    val orderDataMapper: OrderDataMapper,
    val orderRepository: OrderRepository) {

    private val log = LoggerFactory.getLogger(OrderCreateCommandHandler::class.java)

    fun trackOrder(trackOrderQuery: TrackOrderQuery): TrackOrderResponse? {
        val order = orderRepository
            .findByTrackingId(TrackingId(trackOrderQuery.orderTrackingId))
            ?: throw OrderNotFoundException("Order not found with tracking id : +  " + trackOrderQuery.orderTrackingId)
        return orderDataMapper.orderToTrackOrderResponse(order)
    }
}