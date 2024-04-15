package com.ecommerce.order.stub;

import com.ecommerce.order.entity.Order;
import com.ecommerce.order.domain.infrastructure.OrderRepository;

public class OrderRepositoryStub implements OrderRepository {
    @Override
    public Order save(Order order) {
        return null;
    }
}
