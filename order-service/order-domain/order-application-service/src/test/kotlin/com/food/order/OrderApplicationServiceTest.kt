package com.food.order

import com.food.order.dto.create.CreateOrderCommand
import com.food.order.dto.create.CreateOrderResponse
import com.food.order.dto.create.OrderAddress
import com.food.order.dto.create.OrderItem
import com.food.order.entity.Customer
import com.food.order.entity.Order
import com.food.order.entity.Product
import com.food.order.entity.Restaurant
import com.food.order.exception.OrderDomainException
import com.food.order.mapper.OrderDataMapper
import com.food.order.port.input.service.OrderApplicationService
import com.food.order.port.output.repository.CustomerRepository
import com.food.order.port.output.repository.OrderRepository
import com.food.order.port.output.repository.RestaurantRepository
import com.food.order.valueobject.*
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.function.Executable
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.math.BigDecimal
import java.util.*
import java.util.List

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(classes = [OrderTestConfiguration::class])
class OrderApplicationServiceTest {

    @Autowired
    private lateinit var orderApplicationService: OrderApplicationService

    @Autowired
    private lateinit var orderDataMapper: OrderDataMapper

    @Autowired
    private lateinit var orderRepository: OrderRepository

    @Autowired
    private lateinit var customerRepository: CustomerRepository

    @Autowired
    private lateinit var restaurantRepository: RestaurantRepository

    private lateinit var createOrderCommand: CreateOrderCommand
    private lateinit var createOrderCommandWrongPrice: CreateOrderCommand
    private lateinit var createOrderCommandWrongProductPrice: CreateOrderCommand
    private val CUSTOMER_ID = UUID.fromString("d215b5f8-0249-4dc5-89a3-51fd148cfb41")
    private val RESTAURANT_ID = UUID.fromString("d215b5f8-0249-4dc5-89a3-51fd148cfb45")
    private val PRODUCT_ID = UUID.fromString("d215b5f8-0249-4dc5-89a3-51fd148cfb48")
    private val ORDER_ID = UUID.fromString("15a497c1-0f4b-4eff-b9f4-c402c8c07afb")
    private val SAGA_ID = UUID.fromString("15a497c1-0f4b-4eff-b9f4-c402c8c07afa")
    private val PRICE = BigDecimal("200.00")


    @BeforeAll
    fun init() {

        val orderItems = List.of(
            OrderItem(PRODUCT_ID, 1, BigDecimal("50.00"), BigDecimal("50.00")),
            OrderItem(PRODUCT_ID, 3, BigDecimal("50.00"), BigDecimal("150.00"))
        )

        createOrderCommand = CreateOrderCommand(
            CUSTOMER_ID, RESTAURANT_ID, PRICE, orderItems, OrderAddress("street_1", "1000AB", "Paris")
        )

        createOrderCommandWrongPrice = CreateOrderCommand(
            CUSTOMER_ID, RESTAURANT_ID, BigDecimal("250.00"), orderItems, OrderAddress("street_1", "1000AB", "Paris")
        )

        createOrderCommandWrongProductPrice = CreateOrderCommand(
            CUSTOMER_ID, RESTAURANT_ID, BigDecimal("210.00"), List.of(
                OrderItem(PRODUCT_ID, 1, BigDecimal("60.00"), BigDecimal("60.00")),
                OrderItem(PRODUCT_ID, 3, BigDecimal("50.00"), BigDecimal("150.00"))
            ), OrderAddress("street_1", "1000AB", "Paris")
        )


        val customer = Customer(CustomerId(CUSTOMER_ID))
        val restaurantResponse = Restaurant(
            RestaurantId(createOrderCommand.restaurantId), List.of(
                Product(ProductId(PRODUCT_ID), "product-1", Money(BigDecimal("50.00"))),
                Product(ProductId(PRODUCT_ID), "product-2", Money(BigDecimal("50.00")))
            ), true
        )

        val order: Order = orderDataMapper.createOrderCommandToOrder(createOrderCommand)
        order.id = OrderId(ORDER_ID)
        Mockito.`when`(customerRepository.findCustomer(CUSTOMER_ID)).thenReturn(customer)
        Mockito.`when`(
            restaurantRepository.findRestaurantInformation(
                orderDataMapper.createOrderCommandToRestaurant(
                    createOrderCommand
                )
            )
        ).thenReturn(restaurantResponse)

        Mockito.`when`(orderRepository.save(ArgumentMatchers.any(Order::class.java) ?: order)).thenReturn(order)
    }


    @Test
    fun testCreateOrder() {
        val createOrderResponse: CreateOrderResponse = orderApplicationService.createOrder(createOrderCommand)
        assertEquals(OrderStatus.PENDING, createOrderResponse.orderStatus)
        assertEquals("Order created successfully", createOrderResponse.message)
        Assertions.assertNotNull(createOrderResponse.orderTrackingId)
    }

    @Test
    fun testCreateOrderWithWrongTotalPrice() {
        Assertions.assertThrows(OrderDomainException::class.java)
        {orderApplicationService.createOrder(createOrderCommandWrongPrice) }
    }

    @Test
    fun testCreateOrderWithWrongProductPrice() {
        Assertions.assertThrows(OrderDomainException::class.java) {
            orderApplicationService.createOrder(createOrderCommandWrongProductPrice)
        }
    }

}