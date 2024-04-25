package com.ecommerce.order.api.dto;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderItemDto {
    @Positive
    private Long itemId;
    @PositiveOrZero
    private Long itemPrice;
    @Positive
    private Long itemCount;

    public OrderItemDto(Long itemId, Long itemPrice, Long itemCount){
        this.itemId = itemId;
        this.itemPrice = itemPrice;
        this.itemCount = itemCount;
    }
}
