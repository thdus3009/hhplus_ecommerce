package com.ecommerce.order.api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderItemDetailDto{
    private Long itemId;
    private String itemName;
    private Long itemCount;
    private Long itemPrice;

    public OrderItemDetailDto(Long itemId, String itemName, Long itemCount, Long itemPrice) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemCount = itemCount;
        this.itemPrice = itemPrice;
    }
}