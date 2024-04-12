package com.ecommerce.domain.order.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.CreationTimestamp;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
@Entity
@Table(name="Order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id = 0L;

    // uuid(유효아이디)
    @Column(name="uuid", nullable = false, unique = true)
    private String uuid = UUID.randomUUID().toString();

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "total_price", nullable = false)
    private Long totalPrice;

    @Column(name = "total_count", nullable = false)
    private Long totalCount;

    @Column(name = "receiver_name", nullable = false)
    private String receiverName;

    @Column(name = "receiver_name", nullable = false)
    private String receiverPhone;

    @Column(name = "receiver_address", nullable = false)
    private String receiverAddress;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems;

    @CreationTimestamp
    @Column(name="order_date", nullable = false, insertable = true, updatable = false)
    private ZonedDateTime orderDate;
}
