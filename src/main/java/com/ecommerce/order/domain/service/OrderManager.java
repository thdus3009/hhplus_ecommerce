package com.ecommerce.order.domain.service;

import com.ecommerce.order.api.dto.OrderRequestDto;
import com.ecommerce.order.domain.infrastructure.OrderRepository;
import com.ecommerce.order.entity.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderManager {
    private final OrderRepository orderRepository;

    public Order save(OrderRequestDto orderRequestDto){
        Order order = Order.builder()
                .userId(orderRequestDto.getUserId())
                .totalPrice(orderRequestDto.getTotalPrice())
                .totalCount(orderRequestDto.calculateTotalItemCnt())
                .receiverName(orderRequestDto.getReceiverName())
                .receiverPhone(orderRequestDto.getReceiverPhone())
                .receiverAddress(orderRequestDto.getReceiverAddress())
                .build();
        return orderRepository.save(order);

    }
}
