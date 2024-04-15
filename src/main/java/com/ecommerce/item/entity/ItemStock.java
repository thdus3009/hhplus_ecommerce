package com.ecommerce.item.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="ItemStock")
public class ItemStock {
    @Id
    Long itemId;

    @Column(name="quantity", nullable = false)
    Long quantity;

    public void decreaseStock(Long quantity){
        this.quantity = this.quantity - quantity;
    }
    public void checkEnoughItemStockQuantity(Long quantity){
        if(this.quantity<quantity){
            throw new IllegalArgumentException("["+this.itemId+"] 상품 재고가 부족합니다.");
        }
    }
}
