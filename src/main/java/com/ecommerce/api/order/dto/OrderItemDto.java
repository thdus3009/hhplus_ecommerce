package com.ecommerce.api.order.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDto {
    private Long itemId;
    private Long itemCount;
}
