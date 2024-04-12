package com.ecommerce.order.stub;

import com.ecommerce.domain.order.entity.OrderItem;
import com.ecommerce.domain.order.infrastructure.OrderItemRepository;

import java.util.List;

public class OrderItemRepositoryStub implements OrderItemRepository {
    @Override
    public List<OrderItem> saveAll(List<OrderItem> orderItems) {
        return null;
    }
}
