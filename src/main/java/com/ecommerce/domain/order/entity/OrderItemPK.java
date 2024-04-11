package com.ecommerce.domain.order.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemPK implements Serializable {
    private Long orderId;
    private Long itemId;
}
