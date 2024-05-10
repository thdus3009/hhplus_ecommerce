package com.ecommerce.item.domain.service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.item.api.dto.ItemResponseDto;
import com.ecommerce.item.api.dto.PopularItemResponseDto;
import com.ecommerce.item.entity.Item;
import com.ecommerce.item.entity.ItemStock;
import com.ecommerce.item.entity.PopularItems;

@Service
public class ItemService {
	private final ItemManager itemManager;
	private final RedisTemplate<String, PopularItems> redisTemplate;

	public ItemService(ItemManager itemManager, RedisTemplate<String, PopularItems> redisTemplate) {
		this.itemManager = itemManager;
		this.redisTemplate = redisTemplate;
	}

	/**
	 * - 특정 상품 조회
	 * @param itemId 조회할 상품 아이디
	 * @return
	 */
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public ItemResponseDto getItem(Long itemId) {
		return toDto(itemManager.getItem(itemId));
	}

	/**
	 * - OrderUseCase에서 사용하는 주문할 상품정보들 조회
	 * @param itemIds 조회할 상품의 아이디들
	 * @return
	 */
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<Item> getItemsFromOrder(List<Long> itemIds) {
		return itemManager.getItemsFromOrder(itemIds);
	}

	/**
	 * - 인기 상품 조회
	 * @param date
	 * @param count
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<PopularItemResponseDto> findItems(Long date, Long count) {
		// 먼저 redis에 값을 조회후 있으면 반환 없으면 저장
		PopularItems o = redisTemplate.opsForValue().get("popularItems:" + date.toString());
		if (o == null) {
			List<PopularItemResponseDto> popularItemsResponse = itemManager.findItems(date, count);
			PopularItems value = PopularItems.builder()
				.items(popularItemsResponse)
				.createdAt(ZonedDateTime.now().toString())
				.build();
			redisTemplate.opsForValue().set("popularItems:" + String.valueOf(date), value);
			return popularItemsResponse;
		} else {
			return o.getItems();
		}
	}

	@Scheduled(cron = "0 0 * * * *", zone = "Asia/Seoul") // 1시간에 한번씩 실행 (*시 0분 0초 기준)
	public void cacheDelete() {
		Set<String> keysToDelete = redisTemplate.keys("popularItems:*");
		if (keysToDelete != null && !keysToDelete.isEmpty()) {
			redisTemplate.delete(keysToDelete);
		}
	}

	/**
	 * - 상품 재고(ItemStock) 차감
	 * @param items
	 * @param updateItemStocks
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateQuantity(List<Item> items, List<ItemStock> updateItemStocks) {
		itemManager.updateItemQuantity(items, updateItemStocks);
	}

	public ItemResponseDto toDto(Item newItem) {
		return ItemResponseDto.builder()
			.id(newItem.getId())
			.name(newItem.getName())
			.price(newItem.getPrice())
			.quantity(newItem.getQuantity())
			.createdAt(newItem.getCreatedAt())
			.updatedAt(newItem.getUpdatedAt())
			.build();
	}
}
