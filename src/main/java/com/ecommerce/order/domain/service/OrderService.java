package com.ecommerce.order.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.item.entity.Item;
import com.ecommerce.order.api.dto.OrderAndOrderItems;
import com.ecommerce.order.api.dto.OrderRequestDto;
import com.ecommerce.order.domain.validator.OrderValidator;
import com.ecommerce.order.entity.Order;
import com.ecommerce.order.entity.OrderItem;

@Service
public class OrderService {
	private final OrderManager orderManager;
	private final OrderItemManager orderItemManager;
	private final OrderValidator orderValidator;

	public OrderService(OrderManager orderManager, OrderItemManager orderItemManager, OrderValidator orderValidator) {
		this.orderManager = orderManager;
		this.orderItemManager = orderItemManager;
		this.orderValidator = orderValidator;
	}

	@Transactional
	public OrderAndOrderItems order(OrderRequestDto orderRequestDto, List<Item> items) {
		Order order = orderManager.save(orderRequestDto);
		List<OrderItem> orderItems = orderItemManager.save(order, items, orderRequestDto.getItems());
		return new OrderAndOrderItems(order, orderItems);
	}
}
