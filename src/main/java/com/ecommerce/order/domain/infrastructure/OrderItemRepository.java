package com.ecommerce.order.domain.infrastructure;

import java.util.List;

import com.ecommerce.order.entity.OrderItem;

public interface OrderItemRepository {
	List<OrderItem> saveAll(List<OrderItem> orderItems);
}
