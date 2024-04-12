package com.ecommerce.domain.item.infrastructure.impl;

import com.ecommerce.domain.item.entity.ItemStock;
import com.ecommerce.domain.item.infrastructure.ItemStockJpaRepository;
import com.ecommerce.domain.item.infrastructure.ItemStockRepository;
import jakarta.persistence.LockModeType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class ItemStockRepositoryImpl implements ItemStockRepository {
    //private final ItemStockQuerydslRepository itemStockQuerydslRepository;
    private final ItemStockJpaRepository itemStockJpaRepository;
    @Override
    public List<ItemStock> findByItemIds(List<Long> itemIds) {
        return itemStockJpaRepository.findByItemIds(itemIds);
    }

    @Override
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    public List<ItemStock> saveAll(List<ItemStock> itemStocks) {
        return itemStockJpaRepository.saveAll(itemStocks);
    }
}
