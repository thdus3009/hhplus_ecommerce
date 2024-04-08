package com.ecommerce.api.order.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
@Builder
public class OrderResponseDto {
    private Long id;
    private Long userId;
    private Long totalPrice;
    private Long totalCount;
    private String receiverName;
    private String receiverPhone;
    private String receiverAddress;
    private ZonedDateTime orderDate;
}
