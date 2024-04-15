package com.ecommerce.order.api.dto;

import com.ecommerce.order.entity.Order;
import com.ecommerce.order.entity.OrderItem;
import lombok.Getter;

import java.util.List;

@Getter
public class OrderAndOrderItems {
    Order order;
    List<OrderItem> orderItems;

    public OrderAndOrderItems(Order order, List<OrderItem> orderItems) {
        this.order = order;
        this.orderItems = orderItems;
    }
}
