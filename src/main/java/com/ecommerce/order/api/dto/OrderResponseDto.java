package com.ecommerce.order.api.dto;

import java.util.ArrayList;
import java.util.List;

import com.ecommerce.order.entity.Order;
import com.ecommerce.order.entity.OrderItem;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrderResponseDto {
	private OrderDto order;
	private List<OrderItemDetailDto> orderItems;

	public static OrderResponseDto of(Order order, List<OrderItem> orderItems) {
		List<OrderItemDetailDto> orderItemDetailDtos = new ArrayList<>();
		for (OrderItem orderItem : orderItems) {
			orderItemDetailDtos.add(new OrderItemDetailDto(
				orderItem.getItem().getId(),
				orderItem.getItemName(),
				orderItem.getItemCount(),
				orderItem.getItemPrice()
			));
		}
		return OrderResponseDto.builder()
			.order(new OrderDto(
				order.getUuid(),
				order.getUserId(),
				order.getTotalPrice(),
				order.getTotalCount(),
				order.getReceiverName(),
				order.getReceiverPhone(),
				order.getReceiverAddress()
			))
			.orderItems(orderItemDetailDtos)
			.build();
	}
}
