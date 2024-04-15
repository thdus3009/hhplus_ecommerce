package com.ecommerce.item.domain.infrastructure.impl;

import com.ecommerce.item.api.dto.PopularItemResponseDto;
import com.ecommerce.item.domain.infrastructure.ItemRepository;
import com.ecommerce.item.entity.Item;
import com.ecommerce.item.domain.infrastructure.ItemJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
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
    public List<PopularItemResponseDto> findItems(ZonedDateTime date, Long count) {
        return itemJpaRepository.findItemsWithDateAndCount(date, count);
    }

    @Override
    public List<Item> saveAll(List<Item> items) {
        return itemJpaRepository.saveAll(items);
    }
}
