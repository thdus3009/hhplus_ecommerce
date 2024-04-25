package com.ecommerce.item.domain.service;

import com.ecommerce.item.api.dto.ItemRequestDto;
import com.ecommerce.item.api.dto.ItemResponseDto;
import com.ecommerce.item.api.dto.PopularItemResponseDto;
import com.ecommerce.item.domain.infrastructure.ItemRepository;
import com.ecommerce.item.entity.Item;
import com.ecommerce.item.entity.ItemStock;
import com.ecommerce.order.api.dto.OrderItemDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ItemService {
    private final ItemManager itemManager;

    public ItemService(ItemManager itemManager){
        this.itemManager = itemManager;
    }

    /**
     * - 특정 상품 조회
     * @param itemId 조회할 상품 아이디
     * @return
     */
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public ItemResponseDto getItem(Long itemId){
        return toDto(itemManager.getItem(itemId));
    }

    /**
     * - OrderUseCase에서 사용하는 주문할 상품정보들 조회
     * @param itemIds 조회할 상품의 아이디들
     * @return
     */
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<Item> getItemsFromOrder(List<Long> itemIds){
        return itemManager.getItemsFromOrder(itemIds);
    }

    /**
     * - 인기 상품 조회
     * @param itemRequestDto > date, count 정보가 있는 Dto
     * @return
     */
    @Transactional(readOnly = true)
    public List<PopularItemResponseDto> findItems(ItemRequestDto itemRequestDto){
        return itemManager.findItems(itemRequestDto);
    }

    /**
     * - 상품 재고(ItemStock) 차감
     * @param items
     * @param updateItemStocks
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateQuantity(List<Item> items, List<ItemStock> updateItemStocks){
        itemManager.updateItemQuantity(items, updateItemStocks);
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
