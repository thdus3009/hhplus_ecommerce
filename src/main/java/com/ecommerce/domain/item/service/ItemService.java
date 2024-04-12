package com.ecommerce.domain.item.service;

import com.ecommerce.api.item.dto.ItemResponseDto;
import com.ecommerce.api.order.dto.OrderRequestDto;
import com.ecommerce.api.point.dto.PointResponseDto;
import com.ecommerce.common.exception.CustomException;
import com.ecommerce.common.exception.ErrorCode;
import com.ecommerce.domain.item.entity.Item;
import com.ecommerce.domain.item.infrastructure.ItemRepository;
import com.ecommerce.domain.point.entity.UserPoint;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@AllArgsConstructor
@Service
public class ItemService {
    private final ItemRepository itemRepository;

    @Transactional(readOnly = true)
    public ItemResponseDto getItem(Long itemId, OrderRequestDto orderRequestDto){
        Optional<Item> item = itemRepository.findById(itemId);
        Item newItem = item.orElseThrow(() -> new CustomException(ErrorCode.ITEM_NULL));
        return toDto(newItem);
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
