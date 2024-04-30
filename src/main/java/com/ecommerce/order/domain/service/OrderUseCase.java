package com.ecommerce.order.domain.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.common.exception.CustomException;
import com.ecommerce.common.exception.ErrorCode;
import com.ecommerce.item.domain.service.ItemService;
import com.ecommerce.item.domain.service.ItemStockService;
import com.ecommerce.item.entity.Item;
import com.ecommerce.item.entity.ItemStock;
import com.ecommerce.order.api.dto.OrderAndOrderItems;
import com.ecommerce.order.api.dto.OrderItemDto;
import com.ecommerce.order.api.dto.OrderRequestDto;
import com.ecommerce.order.api.dto.OrderResponseDto;
import com.ecommerce.userPoint.domain.service.PointService;
import com.ecommerce.userPoint.entity.UserPoint;

@Component
public class OrderUseCase {
	private final PointService pointService;
	private final OrderService orderService;
	private final ItemService itemService;
	private final ItemStockService itemStockService;
	private final ApplicationEventPublisher applicationEventPublisher;

	public OrderUseCase(PointService pointService, OrderService orderService, ItemService itemService,
		ItemStockService itemStockService, ApplicationEventPublisher applicationEventPublisher) {
		this.pointService = pointService;
		this.orderService = orderService;
		this.itemService = itemService;
		this.itemStockService = itemStockService;
		this.applicationEventPublisher = applicationEventPublisher;
	}

	@Transactional
	public OrderResponseDto order(OrderRequestDto orderRequestDto) {
		// requestDto에 items 있는지 확인
		List<Long> itemIds = new ArrayList<>();
		if (orderRequestDto.isItemsNotEmpty()) {
			itemIds = (List<Long>)orderRequestDto.getItems()
				.stream()
				.map(OrderItemDto::getItemId)
				.toList();
		} else {
			throw new CustomException(ErrorCode.ITEM_NULL);
		}

		// 품절 확인 & 재고 조회
		List<ItemStock> itemStocks = itemStockService.checkByIds(itemIds);
		List<Item> items = itemService.getItemsFromOrder(itemIds);
		// 결제
		UserPoint userPoint = pointService.usePoint(
			orderRequestDto.getUserId(),
			orderRequestDto.getTotalPrice()
		);
		// 재고 차감
		List<ItemStock> updateItemStocks = itemStockService.decreaseQuantity(itemStocks, orderRequestDto.getItems());
		// 상품 재고 차감
		itemService.updateQuantity(items, updateItemStocks);
		// 주문
		OrderAndOrderItems orderAndOrderItems = orderService.order(orderRequestDto, items);
		// 이벤트 핸들러 > 통계자료 전송
		applicationEventPublisher.publishEvent(
			new OrderAndOrderItems(
				orderAndOrderItems.getOrder(),
				orderAndOrderItems.getOrderItems()
			)
		);

		return OrderResponseDto.of(
			orderAndOrderItems.getOrder(),
			orderAndOrderItems.getOrderItems()
		);
	}
}
