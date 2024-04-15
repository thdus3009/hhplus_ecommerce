package com.ecommerce.item.domain.service;

import com.ecommerce.item.entity.ItemStock;
import com.ecommerce.item.domain.infrastructure.ItemStockRepository;
import com.ecommerce.item.domain.validator.ItemValidator;
import com.ecommerce.order.api.dto.OrderItemDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class ItemStockService {
    private final ItemValidator itemValidator;
    private final ItemStockRepository itemStockRepository;
    private final ItemStockManager itemStockManager;

    @Transactional(propagation = Propagation.REQUIRED)
    public List<ItemStock> checkByIds(List<Long> itemIds) {
        List<ItemStock> itemStocks = itemStockRepository.findByItemIds(itemIds);
        itemValidator.quantityZeroCheck(itemStocks);
        return itemStocks;
    }

    public List<ItemStock> decreaseQuantity(List<ItemStock> itemStocks, List<OrderItemDto> itemDtos) {
        return itemStockManager.updateStock(itemStocks, itemDtos);
    }
}
