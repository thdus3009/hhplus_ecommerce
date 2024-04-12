package com.ecommerce.order.stub;

import com.ecommerce.domain.order.entity.Order;
import com.ecommerce.domain.order.infrastructure.OrderRepository;

public class OrderRepositoryStub implements OrderRepository {
    @Override
    public Order save(Order order) {
        return null;
    }
}
