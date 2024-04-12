package com.ecommerce.domain.item.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="Item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    Long id = 0L;

    @Column(name="name", nullable = false)
    String name;

    @Column(name="price", nullable = false)
    Long price;

    @Column(name="quantity", nullable = false)
    Long quantity;

    @CreationTimestamp
    @Column(name="created_at", nullable = false, insertable = true, updatable = false)
    ZonedDateTime createdAt;

    @UpdateTimestamp
    @Column(name="updated_at", nullable = true, insertable = false, updatable = true)
    ZonedDateTime updatedAt;
}
