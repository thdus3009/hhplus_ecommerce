package com.ecommerce.domain.order.infrastructure;

import com.ecommerce.domain.order.entity.OrderItem;

import java.util.List;

public interface OrderItemRepository {
    List<OrderItem> saveAll(List<OrderItem> orderItems);
}
