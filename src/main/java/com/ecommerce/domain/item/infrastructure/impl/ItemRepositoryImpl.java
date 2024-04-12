package com.ecommerce.domain.item.infrastructure.impl;

import com.ecommerce.domain.item.entity.Item;
import com.ecommerce.domain.item.infrastructure.ItemJpaRepository;
import com.ecommerce.domain.item.infrastructure.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class ItemRepositoryImpl implements ItemRepository {
    private final ItemJpaRepository itemJpaRepository;

    @Override
    public Optional<Item> findById(Long itemId) {
        return itemJpaRepository.findById(itemId);
    }

    @Override
    public List<Item> findByIds(List<Long> itemIds) {
        return itemJpaRepository.findByIds(itemIds);
    }

    @Override
    public List<Item> saveAll(List<Item> items) {
        return itemJpaRepository.saveAll(items);
    }
}
