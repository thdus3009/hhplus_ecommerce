package com.ecommerce.item.domain.service;

import com.ecommerce.common.exception.CustomException;
import com.ecommerce.common.exception.ErrorCode;
import com.ecommerce.item.domain.infrastructure.ItemRepository;
import com.ecommerce.item.entity.Item;
import com.ecommerce.item.entity.ItemStock;
import com.ecommerce.order.api.dto.OrderItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ItemManager {
    private final ItemRepository itemRepository;

    public void updateItemQuantity(List<Item> items, List<OrderItemDto> itemDtos){
        for (OrderItemDto itemDto : itemDtos){
            Item item = items.stream()
                    .filter(i -> i.getId().equals(itemDto.getItemId()))
                    .findFirst()
                    .orElseThrow(() -> new CustomException(ErrorCode.ITEM_NULL));

            item.decreaseQuantity(item.getQuantity());
        }
        itemRepository.saveAll(items);
    }

}
