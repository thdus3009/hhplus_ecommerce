package com.ecommerce.order.entity;

import com.ecommerce.item.entity.OrderItemStatus;
import com.ecommerce.userPoint.entity.PointStatus;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Entity
@Table(name="OrderItem")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id = 0L;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name = "item_id", nullable = false)
    private Long itemId;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderItemStatus status = OrderItemStatus.READY;

    @Column(name = "item_name", nullable = true)
    private String description = null;

    @Column(name = "item_name", nullable = false)
    private String itemName;

    @Column(name = "item_count", nullable = false)
    private Long itemCount;

    @Column(name = "item_price", nullable = false)
    private Long itemPrice;

}
