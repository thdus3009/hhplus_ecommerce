package com.ecommerce.item.domain.infrastructure.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.ecommerce.item.domain.infrastructure.ItemStockJpaRepository;
import com.ecommerce.item.domain.infrastructure.ItemStockRepository;
import com.ecommerce.item.entity.ItemStock;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class ItemStockRepositoryImpl implements ItemStockRepository {
	private final ItemStockJpaRepository itemStockJpaRepository;

	@Override
	public List<ItemStock> findByItemIds(List<Long> itemIds) {
		return itemStockJpaRepository.findByItemIds(itemIds);
	}

	@Override
	public Optional<ItemStock> findByItemId(Long itemId) {
		return itemStockJpaRepository.findById(itemId);
	}

	@Override
	public List<ItemStock> saveAll(List<ItemStock> itemStocks) {
		return itemStockJpaRepository.saveAll(itemStocks);
	}
}
