package com.food.order.system.data.access.customer.adapter;

import com.food.order.entity.Customer;
import com.food.order.port.output.repository.CustomerRepository;
import com.food.order.system.data.access.customer.mapper.CustomerDataAccessMapper;
import com.food.order.system.data.access.customer.repository.CustomerJPARepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerRepositoryImpl implements CustomerRepository {


    private final CustomerJPARepository customerJpaRepository;
    private final CustomerDataAccessMapper customerDataAccessMapper;


    @Override
    public Customer save(Customer customer) {
        return customerDataAccessMapper.customerEntityToCustomer(
                customerJpaRepository.save(customerDataAccessMapper.customerToCustomerEntity(customer)));
    }

    @Nullable
    @Override
    public Customer findCustomer(@NotNull UUID customerId) {
        Optional<Customer> customer = customerJpaRepository.findById(customerId)
                .map(customerDataAccessMapper::customerEntityToCustomer);
        return customer.get();
    }
}
