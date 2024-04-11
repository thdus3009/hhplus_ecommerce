package com.ecommerce.domain.order.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@IdClass(OrderItemPK.class)
@Entity
@Table(name="OrderItem")
public class OrderItem {
    @Column(name = "order_id", nullable = false)
    private Long orderId;

    @Column(name = "item_id", nullable = false)
    private Long itemId;

    @Column(name = "item_name", nullable = false)
    private String itemName;

    @Column(name = "item_count", nullable = false)
    private Integer itemCount;

    @Column(name = "item_price", nullable = false)
    private Long itemPrice;
}
