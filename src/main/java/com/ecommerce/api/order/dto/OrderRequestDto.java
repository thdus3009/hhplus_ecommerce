package com.ecommerce.api.order.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRequestDto {
    private Long userId;
    private List<OrderItemDto> items;

    public Long calculateTotalItemCnt(){
        return items.stream()
                .mapToLong(OrderItemDto::getItemCount)
                .sum();
    }
}
