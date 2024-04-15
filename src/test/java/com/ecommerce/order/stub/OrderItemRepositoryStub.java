package com.ecommerce.order.stub;

import com.ecommerce.order.entity.OrderItem;
import com.ecommerce.order.domain.infrastructure.OrderItemRepository;

import java.util.List;

public class OrderItemRepositoryStub implements OrderItemRepository {
    @Override
    public List<OrderItem> saveAll(List<OrderItem> orderItems) {
        return null;
    }
}
