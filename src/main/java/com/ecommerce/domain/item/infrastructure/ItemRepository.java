package com.ecommerce.domain.item.infrastructure;

import com.ecommerce.domain.item.entity.Item;

import java.util.List;
import java.util.Optional;

public interface ItemRepository {
    Optional<Item> findById(Long itemId);
    List<Item> findByIds(List<Long> itemIds);
    List<Item> saveAll(List<Item> items);
}
