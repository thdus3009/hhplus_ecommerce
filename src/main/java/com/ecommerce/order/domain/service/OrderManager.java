package com.ecommerce.order.domain.service;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.ecommerce.order.api.dto.OrderRequestDto;
import com.ecommerce.order.domain.infrastructure.OrderRepository;
import com.ecommerce.order.entity.Order;

@Component
public class OrderManager {
	private final OrderRepository orderRepository;

	public OrderManager(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	public Order save(OrderRequestDto orderRequestDto) {
		Order order = new Order(
			UUID.randomUUID().toString(),
			orderRequestDto.getUserId(),
			orderRequestDto.getTotalPrice(),
			orderRequestDto.calculateTotalItemCnt(),
			orderRequestDto.getReceiverName(),
			orderRequestDto.getReceiverPhone(),
			orderRequestDto.getReceiverAddress()
		);
		return orderRepository.save(order);

	}
}
