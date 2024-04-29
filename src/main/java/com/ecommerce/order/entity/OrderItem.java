package com.ecommerce.order.entity;

import com.ecommerce.item.entity.Item;
import com.ecommerce.item.entity.OrderItemStatus;
import com.ecommerce.userPoint.entity.PointStatus;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private OrderItemStatus status = OrderItemStatus.READY;

    @Column(name = "description", nullable = true)
    private String description = null;

    @Column(name = "item_name", nullable = false)
    private String itemName;

    @Column(name = "item_count", nullable = false)
    private Long itemCount;

    @Column(name = "item_price", nullable = false)
    private Long itemPrice;

    public OrderItem(Order order, Item item, OrderItemStatus status, String description, String itemName, Long itemCount, Long itemPrice) {
        this.order = order;
        this.item = item;
        this.status = status;
        this.description = description;
        this.itemName = itemName;
        this.itemCount = itemCount;
        this.itemPrice = itemPrice;
    }
}
