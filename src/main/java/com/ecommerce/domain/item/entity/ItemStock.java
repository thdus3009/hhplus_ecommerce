package com.ecommerce.domain.item.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="ItemStock")
public class ItemStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    Long id = 0L;

    @Column(name = "item_id", nullable = false)
    Long itemId;

    @Column(name="quantity", nullable = false)
    Long quantity;
}
