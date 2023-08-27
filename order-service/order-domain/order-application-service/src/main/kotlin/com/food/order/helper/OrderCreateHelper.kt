package com.food.order.helper

import com.food.order.dto.create.CreateOrderCommand
import com.food.order.dto.create.CreateOrderResponse
import com.food.order.entity.Restaurant
import com.food.order.event.OrderCreatedEvent
import com.food.order.exception.OrderDomainException
import com.food.order.mapper.OrderDataMapper
import com.food.order.port.input.service.OrderApplicationService
import com.food.order.port.output.repository.CustomerRepository
import com.food.order.port.output.repository.OrderRepository
import com.food.order.port.output.repository.RestaurantRepository
import com.food.order.service.OrderDomainService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Component
class OrderCreateHelper(
    val orderDomainService: OrderDomainService,
    val orderRepository: OrderRepository,
    val customerRepository: CustomerRepository,
    val restaurantRepository: RestaurantRepository,
    val orderDataMapper: OrderDataMapper
) {

    private val log = LoggerFactory.getLogger(OrderCreateHelper::class.java)

    @Transactional
    fun persistOrder(createOrderCommand: CreateOrderCommand): OrderCreatedEvent {
        log.info("Create order {}", createOrderCommand)
        checkCustomer(createOrderCommand.customerId)
        val restaurant: Restaurant = checkRestaurant(createOrderCommand)
        val order = orderDataMapper.createOrderCommandToOrder(createOrderCommand)
        val orderCreatedEvent = orderDomainService.validateAndInitiateOrder(order, restaurant)
        orderRepository.save(order)
        log.info("Saved order ${order.id}")
        return orderCreatedEvent
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