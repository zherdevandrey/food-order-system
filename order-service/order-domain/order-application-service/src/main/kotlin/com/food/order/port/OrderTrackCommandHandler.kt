package com.food.order.port

import com.food.order.dto.track.TrackOrderQuery
import com.food.order.dto.track.TrackOrderResponse
import lombok.extern.slf4j.Slf4j
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
@Slf4j
internal class OrderTrackCommandHandler {

    private val log = LoggerFactory.getLogger(OrderCreateCommandHandler::class.java)

    fun trackOrder(trackOrderQuery: TrackOrderQuery): TrackOrderResponse? {
        TODO("Not yet implemented")
    }
}