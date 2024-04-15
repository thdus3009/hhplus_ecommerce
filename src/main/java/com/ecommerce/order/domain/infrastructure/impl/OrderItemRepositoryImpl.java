package com.ecommerce.order.domain.infrastructure.impl;

import com.ecommerce.order.domain.infrastructure.OrderItemJpaRepository;
import com.ecommerce.order.entity.OrderItem;
import com.ecommerce.order.domain.infrastructure.OrderItemRepository;
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
