package com.ecommerce.item.domain.service;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.ecommerce.common.exception.CustomException;
import com.ecommerce.common.exception.ErrorCode;
import com.ecommerce.item.api.dto.PopularItemResponseDto;
import com.ecommerce.item.domain.infrastructure.ItemRepository;
import com.ecommerce.item.entity.Item;
import com.ecommerce.item.entity.ItemStock;

@Component
public class ItemManager {
	private final ItemRepository itemRepository;

	public ItemManager(ItemRepository itemRepository) {
		this.itemRepository = itemRepository;
	}

	public Item getItem(Long itemId) {
		Optional<Item> item = itemRepository.findById(itemId);
		return item.orElseThrow(() -> new CustomException(ErrorCode.ITEM_NULL + " -id : " + itemId));
	}

	public List<Item> getItemsFromOrder(List<Long> itemIds) {
		return itemRepository.findByIds(itemIds);
	}

	public List<PopularItemResponseDto> findItems(Long date, Long count) {
		ZonedDateTime startDate = ZonedDateTime.now().minus(date, ChronoUnit.DAYS);
		return itemRepository.findItems(startDate, count);
	}

	public void updateItemQuantity(List<Item> items, List<ItemStock> updateItemStocks) {
		for (ItemStock itemStock : updateItemStocks) {
			Item item = items.stream()
				.filter(i -> i.getId().equals(itemStock.getItemId()))
				.findFirst()
				.orElseThrow(() -> new CustomException(ErrorCode.ITEM_NULL));

			item.updateQuantity(itemStock.getQuantity());
		}
		itemRepository.saveAll(items);
	}

	public List<Item> save(Item item, Long quantity) {
		item.updateQuantity(quantity);
		return itemRepository.saveAll(List.of(item));
	}

}
