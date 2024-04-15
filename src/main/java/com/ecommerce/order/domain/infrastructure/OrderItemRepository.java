package com.ecommerce.order.domain.infrastructure;

import com.ecommerce.order.entity.OrderItem;

import java.util.List;

public interface OrderItemRepository {
    List<OrderItem> saveAll(List<OrderItem> orderItems);
}
