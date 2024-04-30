package com.ecommerce.order.domain.infrastructure.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ecommerce.order.domain.infrastructure.OrderItemJpaRepository;
import com.ecommerce.order.domain.infrastructure.OrderItemRepository;
import com.ecommerce.order.entity.OrderItem;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class OrderItemRepositoryImpl implements OrderItemRepository {
	private final OrderItemJpaRepository orderItemJpaRepository;

	@Override
	public List<OrderItem> saveAll(List<OrderItem> orderItems) {
		return orderItemJpaRepository.saveAll(orderItems);
	}
}
