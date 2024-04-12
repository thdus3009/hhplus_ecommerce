package com.ecommerce.domain.item.infrastructure;

import com.ecommerce.domain.item.entity.ItemStock;

import java.util.List;

public interface ItemStockRepository {
    List<ItemStock> findByItemIds(List<Long> itemIds);
    List<ItemStock> saveAll(List<ItemStock> itemStocks);
}
