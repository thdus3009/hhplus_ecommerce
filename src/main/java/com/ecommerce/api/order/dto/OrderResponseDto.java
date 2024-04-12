package com.ecommerce.api.order.dto;

import com.ecommerce.domain.order.entity.Order;
import com.ecommerce.domain.order.entity.OrderItem;
import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;
import java.util.List;

@Getter
@Builder
public class OrderResponseDto {
    private OrderDto order;
    private List<OrderItemDetailDto> orderItems;
}

class OrderDto{
    private String uuid;
    private Long userId;
    private Long totalPrice;
    private Long totalCount;
    private String receiverName;
    private String receiverPhone;
    private String receiverAddress;
}
class OrderItemDetailDto{
    private Long itemId;
    private String itemName;
    private Long itemCount;
    private Long itemPrice;
}
