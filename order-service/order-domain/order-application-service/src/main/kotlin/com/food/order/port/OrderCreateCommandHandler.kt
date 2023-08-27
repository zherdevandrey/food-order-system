package com.food.order.port

import com.food.order.dto.create.CreateOrderCommand
import com.food.order.dto.create.CreateOrderResponse
import com.food.order.entity.Restaurant
import com.food.order.exception.OrderDomainException
import com.food.order.mapper.OrderDataMapper
import com.food.order.port.output.repository.CustomerRepository
import com.food.order.port.output.repository.OrderRepository
import com.food.order.port.output.repository.RestaurantRepository
import com.food.order.service.OrderDomainService
import lombok.extern.slf4j.Slf4j
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Component
@Slf4j
internal class OrderCreateCommandHandler(
    val orderDomainService: OrderDomainService,
    val orderRepository: OrderRepository,
    val customerRepository: CustomerRepository,
    val restaurantRepository: RestaurantRepository,
    val orderDataMapper: OrderDataMapper

) {
    private val log = LoggerFactory.getLogger(OrderCreateCommandHandler::class.java)

    @Transactional
    fun createOrder(createOrderCommand: CreateOrderCommand): CreateOrderResponse {
        log.info("Create order {}", createOrderCommand)
        checkCustomer(createOrderCommand.customerId)
        val restaurant: Restaurant = checkRestaurant(createOrderCommand)
        val order = orderDataMapper.createOrderCommandToOrder(createOrderCommand)
        val orderCreatedEvent = orderDomainService.validateAndInitiateOrder(order, restaurant)
        val savedOrder = orderRepository.save(order)
        log.info("Saved order ${order.id}")
        return orderDataMapper.orderToCreateOrderResponse(savedOrder, "Order created successfully")
    }

    private fun checkRestaurant(createOrderCommand: CreateOrderCommand): Restaurant {
        return restaurantRepository
            .findRestaurantInformation(orderDataMapper.createOrderCommandToRestaurant(createOrderCommand))
            ?: throw OrderDomainException("Restaurant not found. " +
                    "Please check restaurant id: ${createOrderCommand.restaurantId}")
    }

    private fun checkCustomer(customerId: UUID) {
        customerRepository.findCustomer(customerId)
            ?: throw OrderDomainException("Customer not found. CustomerId: $customerId")
    }

}