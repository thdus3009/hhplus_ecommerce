package com.ecommerce.order.api.dto;

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

    private Long totalPrice;

    private String receiverName;

    private String receiverPhone;

    private String receiverAddress;

    public Long calculateTotalItemCnt(){
        return items.stream()
                .mapToLong(OrderItemDto::getItemCount)
                .sum();
    }
}
