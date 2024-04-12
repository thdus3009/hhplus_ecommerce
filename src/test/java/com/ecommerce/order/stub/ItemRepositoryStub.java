package com.ecommerce.order.stub;

import com.ecommerce.domain.item.entity.Item;
import com.ecommerce.domain.item.infrastructure.ItemRepository;

import java.util.List;
import java.util.Optional;

public class ItemRepositoryStub implements ItemRepository {
    @Override
    public Optional<Item> findById(Long itemId) {
        return Optional.empty();
    }

    @Override
    public List<Item> findByIds(List<Long> itemIds) {
        return null;
    }

    @Override
    public List<Item> saveAll(List<Item> items) {
        return null;
    }
}
