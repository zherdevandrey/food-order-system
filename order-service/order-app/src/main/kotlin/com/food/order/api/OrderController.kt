package com.food.order.api

import com.food.order.dto.create.CreateOrderCommand
import com.food.order.dto.create.CreateOrderResponse
import com.food.order.dto.track.TrackOrderQuery
import com.food.order.dto.track.TrackOrderResponse
import com.food.order.port.input.service.OrderApplicationService
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController(value = "/api/orders")
class OrderController(var orderApplicationService: OrderApplicationService) {

    private val log = LoggerFactory.getLogger(OrderController::class.java)

    @PostMapping
    fun createOrder(createOrderCommand: CreateOrderCommand): ResponseEntity<CreateOrderResponse> {
        log.info("Creating order with command: {}", createOrderCommand)
        val createOrderResponse = orderApplicationService.createOrder(createOrderCommand)
        log.info("Created order with tracking id: {}", createOrderResponse.orderTrackingId)
        return ResponseEntity.ok(createOrderResponse)
    }

    @GetMapping("/{orderTrackingId}")
    fun trackOrder(@PathVariable("orderTrackingId") orderTrackingId: UUID): ResponseEntity<TrackOrderResponse> {
        val trackOrderResponse: TrackOrderResponse = orderApplicationService
            .trackOrder(TrackOrderQuery(orderTrackingId))
        log.info("Tracked order with tracking id: {}", orderTrackingId)
        return ResponseEntity.ok(trackOrderResponse)
    }
}