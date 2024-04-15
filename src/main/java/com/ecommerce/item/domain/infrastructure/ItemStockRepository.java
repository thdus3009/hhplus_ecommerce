package com.ecommerce.item.domain.infrastructure;

import com.ecommerce.item.entity.ItemStock;

import java.util.List;

public interface ItemStockRepository {
    List<ItemStock> findByItemIds(List<Long> itemIds);
    List<ItemStock> saveAll(List<ItemStock> itemStocks);
}
