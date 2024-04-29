package com.ecommerce.order.entity;

import com.ecommerce.item.entity.Item;
import com.ecommerce.item.entity.OrderItemStatus;
import com.ecommerce.userPoint.entity.PointStatus;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@Entity
@Table(name="OrderItem")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Builder.Default
    @Column(name = "id", nullable = false, updatable = false)
    private Long id = 0L;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    @Column(name = "status", nullable = false)
    private OrderItemStatus status = OrderItemStatus.READY;

    @Builder.Default
    @Column(name = "description", nullable = true)
    private String description = null;

    @Column(name = "item_name", nullable = false)
    private String itemName;

    @Column(name = "item_count", nullable = false)
    private Long itemCount;

    @Column(name = "item_price", nullable = false)
    private Long itemPrice;

    public OrderItem() {

    }
}
