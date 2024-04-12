package com.ecommerce.domain.order.infrastructure;

import com.ecommerce.domain.order.entity.Order;

public interface OrderRepository {
    Order save(Order order);
}
