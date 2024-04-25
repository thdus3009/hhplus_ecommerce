package com.ecommerce.item.domain.infrastructure.impl;

import com.ecommerce.item.entity.ItemStock;
import com.ecommerce.item.domain.infrastructure.ItemStockJpaRepository;
import com.ecommerce.item.domain.infrastructure.ItemStockRepository;
import jakarta.persistence.LockModeType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class ItemStockRepositoryImpl implements ItemStockRepository {
    private final ItemStockJpaRepository itemStockJpaRepository;
    @Override
    public List<ItemStock> findByItemIds(List<Long> itemIds) {
        return itemStockJpaRepository.findByItemIds(itemIds);
    }

    @Override
    public List<ItemStock> saveAll(List<ItemStock> itemStocks) {
        return itemStockJpaRepository.saveAll(itemStocks);
    }
}
