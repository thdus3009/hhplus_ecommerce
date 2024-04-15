package com.ecommerce.order.domain.infrastructure.impl;


import com.ecommerce.order.entity.Order;
import com.ecommerce.order.domain.infrastructure.OrderJpaRepository;
import com.ecommerce.order.domain.infrastructure.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OrderRepositoryImpl implements OrderRepository {
    private final OrderJpaRepository orderJpaRepository;

    @Override
    public Order save(Order order) {
        return orderJpaRepository.save(order);
    }
}
