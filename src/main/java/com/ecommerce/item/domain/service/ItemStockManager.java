package com.ecommerce.item.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.ecommerce.common.exception.CustomException;
import com.ecommerce.common.exception.ErrorCode;
import com.ecommerce.item.domain.infrastructure.ItemStockRepository;
import com.ecommerce.item.domain.validator.ItemValidator;
import com.ecommerce.item.entity.ItemStock;
import com.ecommerce.order.api.dto.OrderItemDto;

@Component
public class ItemStockManager {
	private final ItemValidator itemValidator;
	private final ItemStockRepository itemStockRepository;

	public ItemStockManager(ItemStockRepository itemStockRepository, ItemValidator itemValidator) {
		this.itemStockRepository = itemStockRepository;
		this.itemValidator = itemValidator;
	}

	public List<ItemStock> checkByIds(List<Long> itemIds) {
		List<ItemStock> itemStocks = itemStockRepository.findByItemIds(itemIds);
		itemValidator.quantityZeroCheck(itemStocks);
		return itemStocks;
	}

	public ItemStock getItemStock(Long itemId) {
		Optional<ItemStock> itemStock = itemStockRepository.findByItemId(itemId);
		return itemStock.orElseThrow(() -> new CustomException(ErrorCode.ITEM_STOCK_NULL + " -id : " + itemId));
	}

	public List<ItemStock> updateStock(List<ItemStock> itemStocks, List<OrderItemDto> itemDtos) {
		for (OrderItemDto itemDto : itemDtos) {
			ItemStock stock = itemStocks.stream()
				.filter(itemStock -> itemStock.getItemId().equals(itemDto.getItemId()))
				.findFirst()
				.orElseThrow(() -> new CustomException(ErrorCode.ITEM_STOCK_NULL));

			stock.checkEnoughItemStockQuantity(itemDto.getItemCount());
			stock.decreaseStock(itemDto.getItemCount());
		}
		itemStockRepository.saveAll(itemStocks);
		return itemStocks;
	}

	public List<ItemStock> save(ItemStock itemStock, Long quantity) {
		itemStock.updateQuantity(quantity);
		return itemStockRepository.saveAll(List.of(itemStock));
	}
}
