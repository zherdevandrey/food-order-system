package com.food.order.system.data.access.order.adapter;

import com.food.order.system.data.access.order.mapper.OrderDataAccessMapper;
import com.food.order.system.data.access.order.repository.OrderJpaRepository;
import com.food.order.entity.Order;
import com.food.order.valueobject.TrackingId;
import com.food.order.port.output.repository.OrderRepository;
import com.food.order.valueobject.OrderId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderRepositoryImpl implements OrderRepository {

    private final OrderJpaRepository orderJpaRepository;
    private final OrderDataAccessMapper orderDataAccessMapper;


    @Override
    public Order save(Order order) {
        return orderDataAccessMapper.orderEntityToOrder(orderJpaRepository
                .save(orderDataAccessMapper.orderToOrderEntity(order)));
    }

    @Override
    public Order findById(OrderId orderId) {
        return orderJpaRepository.findById(orderId.getId())
                .map(orderDataAccessMapper::orderEntityToOrder);
    }

    @Override
    public Order findByTrackingId(TrackingId trackingId) {
        return orderJpaRepository.findByTrackingId(trackingId.getValue())
                .map(orderDataAccessMapper::orderEntityToOrder);
    }
}
