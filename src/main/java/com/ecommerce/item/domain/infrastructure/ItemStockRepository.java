package com.ecommerce.item.domain.infrastructure;

import java.util.List;

import com.ecommerce.item.entity.ItemStock;

public interface ItemStockRepository {
	List<ItemStock> findByItemIds(List<Long> itemIds);

	List<ItemStock> saveAll(List<ItemStock> itemStocks);
}
