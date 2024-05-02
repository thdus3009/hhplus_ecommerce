package com.ecommerce.item.domain.infrastructure;

import java.util.List;
import java.util.Optional;

import com.ecommerce.item.entity.ItemStock;

public interface ItemStockRepository {
	List<ItemStock> findByItemIds(List<Long> itemIds);

	Optional<ItemStock> findByItemId(Long itemId);

	List<ItemStock> saveAll(List<ItemStock> itemStocks);
}
