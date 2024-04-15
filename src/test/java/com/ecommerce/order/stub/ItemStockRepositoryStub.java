package com.ecommerce.order.stub;

import com.ecommerce.item.entity.ItemStock;
import com.ecommerce.item.domain.infrastructure.ItemStockRepository;

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
