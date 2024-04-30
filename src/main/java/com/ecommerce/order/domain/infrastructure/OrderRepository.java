package com.ecommerce.order.domain.infrastructure;

import com.ecommerce.order.entity.Order;

public interface OrderRepository {
	Order save(Order order);
}
