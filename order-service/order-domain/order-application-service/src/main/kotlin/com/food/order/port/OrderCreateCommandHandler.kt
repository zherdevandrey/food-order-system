package com.food.order.port

import com.food.order.dto.create.CreateOrderCommand
import com.food.order.dto.create.CreateOrderResponse
import com.food.order.helper.OrderCreateHelper
import com.food.order.mapper.OrderDataMapper
import com.food.order.port.output.message.publisher.payment.OrderCreatedPaymentRequestMessagePublisher
import lombok.extern.slf4j.Slf4j
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Slf4j
internal class OrderCreateCommandHandler(
    val orderCreateHelper: OrderCreateHelper,
    val orderCreatedPaymentRequestMessagePublisher: OrderCreatedPaymentRequestMessagePublisher,
    val orderDataMapper: OrderDataMapper

) {
    private val log = LoggerFactory.getLogger(OrderCreateCommandHandler::class.java)

    @Transactional
    fun createOrder(createOrderCommand: CreateOrderCommand): CreateOrderResponse {
        log.info("Create order {}", createOrderCommand)
        val orderCreatedEvent = orderCreateHelper.persistOrder(createOrderCommand)
        orderCreatedPaymentRequestMessagePublisher.publish(orderCreatedEvent)
        return orderDataMapper.orderToCreateOrderResponse(orderCreatedEvent.order, "Order created successfully")
    }
}