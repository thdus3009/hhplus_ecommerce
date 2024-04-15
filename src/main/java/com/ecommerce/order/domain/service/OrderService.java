package com.ecommerce.order.domain.service;

import com.ecommerce.item.entity.Item;
import com.ecommerce.order.api.dto.OrderAndOrderItems;
import com.ecommerce.order.api.dto.OrderRequestDto;
import com.ecommerce.order.api.dto.OrderResponseDto;
import com.ecommerce.order.domain.validator.OrderValidator;
import com.ecommerce.order.entity.Order;
import com.ecommerce.order.entity.OrderItem;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class OrderService {
    private final OrderManager orderManager;
    private final OrderItemManager orderItemManager;
    private final OrderValidator orderValidator;

    @Transactional
    public OrderAndOrderItems order(OrderRequestDto orderRequestDto, List<Item> items){
        Order order = orderManager.save(orderRequestDto);
        List<OrderItem> orderItems = orderItemManager.save(order, items, orderRequestDto.getItems());
        return new OrderAndOrderItems(order, orderItems);
    }
}
