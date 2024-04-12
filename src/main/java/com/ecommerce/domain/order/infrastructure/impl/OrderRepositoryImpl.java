package com.ecommerce.domain.order.infrastructure.impl;


import com.ecommerce.domain.order.entity.Order;
import com.ecommerce.domain.order.infrastructure.OrderJpaRepository;
import com.ecommerce.domain.order.infrastructure.OrderRepository;
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
