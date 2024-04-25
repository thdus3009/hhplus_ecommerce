package com.ecommerce.order.api.dto;

import jakarta.validation.constraints.*;
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
    @NotNull
    private Long userId;
    private List<OrderItemDto> items;
    @PositiveOrZero
    private Long totalPrice;
    @NotBlank
    private String receiverName;
    @NotBlank
    private String receiverPhone;
    @NotBlank
    private String receiverAddress;

    public Long calculateTotalItemCnt(){
        return items.stream()
                .mapToLong(OrderItemDto::getItemCount)
                .sum();
    }

    public Boolean isItemsNotEmpty() {
        return items != null && !items.isEmpty();
    }
}
