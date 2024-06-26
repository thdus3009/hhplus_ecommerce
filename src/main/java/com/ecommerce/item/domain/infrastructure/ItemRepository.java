package com.ecommerce.item.domain.infrastructure;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import com.ecommerce.item.api.dto.PopularItemResponseDto;
import com.ecommerce.item.entity.Item;

public interface ItemRepository {
	Optional<Item> findById(Long itemId);

	List<Item> findByIds(List<Long> itemIds);

	List<PopularItemResponseDto> findItems(ZonedDateTime date, Long count);

	List<Item> saveAll(List<Item> items);
}
