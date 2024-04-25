package com.ecommerce.order.domain.service;

import com.ecommerce.common.exception.CustomException;
import com.ecommerce.common.exception.ErrorCode;
import com.ecommerce.item.entity.Item;
import com.ecommerce.item.entity.OrderItemStatus;
import com.ecommerce.order.api.dto.OrderItemDto;
import com.ecommerce.order.domain.infrastructure.OrderItemRepository;
import com.ecommerce.order.entity.Order;
import com.ecommerce.order.entity.OrderItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderItemManager {
    private final OrderItemRepository orderItemRepository;
    public OrderItemManager(OrderItemRepository orderItemRepository){
        this.orderItemRepository = orderItemRepository;
    }

    public List<OrderItem> save(Order order, List<Item> items, List<OrderItemDto> orderItemDtos){
        List<OrderItem> orderItems = new ArrayList<>();
        for (OrderItemDto orderItemDto : orderItemDtos){
            Item item = items.stream()
                    .filter(i -> i.getId().equals(orderItemDto.getItemId()))
                    .findFirst()
                    .orElseThrow(() -> new CustomException(ErrorCode.ORDER_ITEM_NOT_FOUND)
                );
            orderItems.add(
                    OrderItem.builder()
                            .order(order)
                            .item(item)
                            .status(OrderItemStatus.READY)
                            .itemName(item.getName())
                            .itemCount(orderItemDto.getItemCount())
                            .itemPrice(item.getPrice())
                            .build()
            );
        }
        orderItemRepository.saveAll(orderItems);
        return orderItems;
    }
}
