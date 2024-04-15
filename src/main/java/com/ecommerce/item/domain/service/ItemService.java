package com.ecommerce.item.domain.service;

import com.ecommerce.item.api.dto.ItemRequestDto;
import com.ecommerce.item.api.dto.ItemResponseDto;
import com.ecommerce.item.api.dto.PopularItemResponseDto;
import com.ecommerce.order.api.dto.OrderItemDto;
import com.ecommerce.order.api.dto.OrderRequestDto;
import com.ecommerce.common.exception.CustomException;
import com.ecommerce.common.exception.ErrorCode;
import com.ecommerce.item.entity.Item;
import com.ecommerce.item.domain.infrastructure.ItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ItemService {
    private final ItemRepository itemRepository;
    private final ItemManager itemManager;

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public ItemResponseDto getItem(Long itemId){
        Optional<Item> item = itemRepository.findById(itemId);
        Item newItem = item.orElseThrow(() -> new CustomException(ErrorCode.ITEM_NULL));
        return toDto(newItem);
    }
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<Item> getItemsFromOrder(List<Long> itemIds){
        return itemRepository.findByIds(itemIds);
    }
    public List<PopularItemResponseDto> findItems(ItemRequestDto itemRequestDto){
        ZonedDateTime startDate = ZonedDateTime.now().minus(itemRequestDto.getDate(), ChronoUnit.DAYS);
        return itemRepository.findItems(startDate, itemRequestDto.getCount());
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void updateQuantity(List<Item> items, List<OrderItemDto> itemDtos){
        itemManager.updateItemQuantity(items, itemDtos);
    }

    public ItemResponseDto toDto(Item newItem){
        return ItemResponseDto.builder()
                .id(newItem.getId())
                .name(newItem.getName())
                .price(newItem.getPrice())
                .quantity(newItem.getQuantity())
                .createdAt(newItem.getCreatedAt())
                .updatedAt(newItem.getUpdatedAt())
                .build();
    }
}
