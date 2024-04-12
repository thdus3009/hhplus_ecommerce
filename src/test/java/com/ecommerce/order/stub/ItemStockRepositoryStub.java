package com.ecommerce.order.stub;

import com.ecommerce.domain.item.entity.ItemStock;
import com.ecommerce.domain.item.infrastructure.ItemStockRepository;

import java.util.List;

public class ItemStockRepositoryStub implements ItemStockRepository {
    @Override
    public List<ItemStock> findByItemIds(List<Long> itemIds) {
        return null;
    }

    @Override
    public List<ItemStock> saveAll(List<ItemStock> itemStocks) {
        return null;
    }
}
