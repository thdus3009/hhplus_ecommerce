package com.ecommerce.item.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.item.entity.ItemStock;
import com.ecommerce.order.api.dto.OrderItemDto;

@Service
public class ItemStockService {

	private final ItemStockManager itemStockManager;

	public ItemStockService(ItemStockManager itemStockManager) {
		this.itemStockManager = itemStockManager;
	}

	/**
	 * 상품 주문 전 제품재고 품절여부 확인
	 * @param itemIds 구매할 상품아이디
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public List<ItemStock> checkByIds(List<Long> itemIds) {
		return itemStockManager.checkByIds(itemIds);
	}

	public ItemStock getItemStock(Long itemId) {
		return itemStockManager.getItemStock(itemId);
	}

	public List<ItemStock> decreaseQuantity(List<ItemStock> itemStocks, List<OrderItemDto> itemDtos) {
		return itemStockManager.updateStock(itemStocks, itemDtos);
	}

	public List<ItemStock> save(ItemStock itemStock, Long quantity) {
		return itemStockManager.save(itemStock, quantity);
	}
}
