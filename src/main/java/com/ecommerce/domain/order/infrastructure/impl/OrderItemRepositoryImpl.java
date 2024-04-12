package com.ecommerce.domain.order.infrastructure.impl;

import com.ecommerce.domain.order.entity.OrderItem;
import com.ecommerce.domain.order.infrastructure.OrderItemJpaRepository;
import com.ecommerce.domain.order.infrastructure.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class OrderItemRepositoryImpl implements OrderItemRepository {
    private final OrderItemJpaRepository orderItemJpaRepository;
    @Override
    public List<OrderItem> saveAll(List<OrderItem> orderItems) {
        return orderItemJpaRepository.saveAll(orderItems);
    }
}
