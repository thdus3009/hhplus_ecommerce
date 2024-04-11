package com.ecommerce.domain.order.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.ZonedDateTime;

@Entity
@Table(name="Order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    Long id = 0L;

    @Column(name = "user_id", nullable = false)
    Long userId;

    @Column(name = "total_price", nullable = false)
    Long totalPrice;

    @Column(name = "total_count", nullable = false)
    Integer totalCount;

    @Column(name = "receiver_name", nullable = false)
    String receiverName;

    @Column(name = "receiver_name", nullable = false)
    String receiverPhone;

    @Column(name = "receiver_address", nullable = false)
    String receiverAddress;

    // 배송 상태 추가하기

    @CreationTimestamp
    @Column(name="created_at", nullable = false, insertable = true, updatable = false)
    ZonedDateTime createdAt;

    @UpdateTimestamp
    @Column(name="updated_at", nullable = true, insertable = false, updatable = true)
    ZonedDateTime updatedAt;
}
