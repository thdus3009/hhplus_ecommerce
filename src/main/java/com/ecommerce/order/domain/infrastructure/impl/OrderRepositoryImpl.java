package com.ecommerce.order.domain.infrastructure.impl;

import org.springframework.stereotype.Component;

import com.ecommerce.order.domain.infrastructure.OrderJpaRepository;
import com.ecommerce.order.domain.infrastructure.OrderRepository;
import com.ecommerce.order.entity.Order;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class OrderRepositoryImpl implements OrderRepository {
	private final OrderJpaRepository orderJpaRepository;

	@Override
	public Order save(Order order) {
		return orderJpaRepository.save(order);
	}
}
