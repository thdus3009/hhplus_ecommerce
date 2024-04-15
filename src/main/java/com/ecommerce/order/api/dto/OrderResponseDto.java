package com.ecommerce.order.api.dto;

import com.ecommerce.order.entity.Order;
import com.ecommerce.order.entity.OrderItem;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
public class OrderResponseDto {
    private OrderDto order;
    private List<OrderItemDetailDto> orderItems;

    public static OrderResponseDto of(Order order, List<OrderItem> orderItems){
        List<OrderItemDetailDto> orderItemDetailDtos = new ArrayList<>();
        for (OrderItem orderItem : orderItems){
            orderItemDetailDtos.add(new OrderItemDetailDto(
                    orderItem.getItemId(),
                    orderItem.getItemName(),
                    orderItem.getItemCount(),
                    orderItem.getItemPrice()
            ));
        }
        return OrderResponseDto.builder()
                .order(new OrderDto(
                        order.getUuid(),
                        order.getUserId(),
                        order.getTotalPrice(),
                        order.getTotalCount(),
                        order.getReceiverName(),
                        order.getReceiverPhone(),
                        order.getReceiverAddress()
                ))
                .orderItems(orderItemDetailDtos)
                .build();
    }
}

class OrderDto{
    private String uuid;
    private Long userId;
    private Long totalPrice;
    private Long totalCount;
    private String receiverName;
    private String receiverPhone;
    private String receiverAddress;

    public OrderDto(String uuid, Long userId, Long totalPrice, Long totalCount, String receiverName, String receiverPhone, String receiverAddress) {
        this.uuid = uuid;
        this.userId = userId;
        this.totalPrice = totalPrice;
        this.totalCount = totalCount;
        this.receiverName = receiverName;
        this.receiverPhone = receiverPhone;
        this.receiverAddress = receiverAddress;
    }
}
class OrderItemDetailDto{
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
