package com.food.order

import com.food.order.port.output.message.publisher.payment.OrderCancelPaymentRequestMessagePublisher
import com.food.order.port.output.message.publisher.payment.OrderCreatedPaymentRequestMessagePublisher
import com.food.order.port.output.message.publisher.restaurantapproval.OrderPayedRestaurantApprovalRequestMessagePublisher
import com.food.order.port.output.repository.CustomerRepository
import com.food.order.port.output.repository.OrderRepository
import com.food.order.port.output.repository.RestaurantRepository
import com.food.order.service.OrderDomainService
import com.food.order.service.impl.OrderDomainServiceImpl
import org.mockito.Mockito
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication(scanBasePackages = ["com.food.order"])
internal class OrderTestConfiguration {

    @Bean
    fun orderCreatedPaymentRequestMessagePublisher(): OrderCreatedPaymentRequestMessagePublisher {
        return Mockito.mock(OrderCreatedPaymentRequestMessagePublisher::class.java)
    }

    @Bean
    fun orderCancelPaymentRequestMessagePublisher(): OrderCancelPaymentRequestMessagePublisher {
        return Mockito.mock(OrderCancelPaymentRequestMessagePublisher::class.java)
    }

    @Bean
    fun orderPayedRestaurantApprovalRequestMessagePublisher(): OrderPayedRestaurantApprovalRequestMessagePublisher {
        return Mockito.mock(OrderPayedRestaurantApprovalRequestMessagePublisher::class.java)
    }

    @Bean
    fun orderRepository(): OrderRepository{
        return Mockito.mock(OrderRepository::class.java)
    }

    @Bean
    fun customerRepository(): CustomerRepository {
        return Mockito.mock(CustomerRepository::class.java)
    }

    @Bean
    fun restaurantRepository(): RestaurantRepository {
        return Mockito.mock(RestaurantRepository::class.java)
    }

    @Bean
    fun orderDomainService():OrderDomainService{
        return OrderDomainServiceImpl()
    }

}